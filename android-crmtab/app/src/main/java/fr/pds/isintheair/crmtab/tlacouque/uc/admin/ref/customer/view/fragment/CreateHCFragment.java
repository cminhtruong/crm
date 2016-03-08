package fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.view.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.QuickRule;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Constants;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.FormatValidator;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.dto.MessageRestCustomer;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.dto.ResponseRestCustomer;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.asynctask.TileDownloader;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.HealthCenter;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.Holding;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.Holding_Table;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.PurchasingCentral;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.PurchasingCentral_Table;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.enumeration.EtablishmentType;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.rest.RESTCustomerHandlerSingleton;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Controller which is used to create an health center. He used to display the view, to control values,
 * and to call rest interface.
 */

public class CreateHCFragment extends Fragment implements ValidationListener {

    MessageRestCustomer messageRestCustomer;

    @Bind(R.id.create_he_fragment_name)
    @Order(1)
    @NotEmpty(messageResId = R.string.create_he_fragment_error_name)
    EditText name;


    @Bind(R.id.create_he_fragment_is_public)
    RadioGroup isPublic;

    @Bind(R.id.create_he_fragment_siret_number)
    @Order(2)
    @Length(min = 14, max = 14, messageResId = R.string.create_he_fragment_error_siret_empty)
    EditText siretNumber;

    @Bind(R.id.create_he_fragment_finess_number)
    @Order(3)
    @Length(min = 9, max = 9, messageResId = R.string.create_he_fragment_error_finess)
    EditText finesstNumber;

    @Bind(R.id.create_he_fragment_street_number)
    @Order(4)
    @NotEmpty(messageResId = R.string.create_he_fragment_error_street_number)
    EditText streetNumber;

    @Bind(R.id.create_he_fragment_street_name)
    @Order(5)
    @NotEmpty(messageResId = R.string.create_he_fragment_error_street_name)
    EditText streetName;

    @Bind(R.id.create_he_fragment_town_name)
    @Order(6)
    @NotEmpty(messageResId = R.string.create_he_fragment_error_town)
    EditText town;


    @Bind(R.id.create_he_fragment_zip_code)
    @Order(7)
    @Length(min = 5, max = 5, messageResId = R.string.create_he_fragment_error_zip_code)
    EditText zipCode;

    @Bind(R.id.create_he_fragment_web_site)
    EditText   webSite;
    @Bind(R.id.create_he_fragment_bed_number)
    EditText   bedNumber;
    @Bind(R.id.create_he_fragment_etablishment_type)
    Spinner    etablishmentType;
    @Bind(R.id.create_he_fragment_purshasing_central)
    Spinner    purshasingCentral;
    @Bind(R.id.create_he_fragment_holding)
    Spinner    holding;
    @Bind(R.id.create_he_fragment_difficulty_having_contact)
    RadioGroup difficultyHavingContact;
    @Bind(R.id.create_he_fragment_service_building)
    RadioGroup serviceBuilding;

    List<Holding>           holdings;
    List<PurchasingCentral> purchasingCentrals;
    Validator               validator;
    View                    view;
    boolean                 createCalledisOk;
    boolean                 createCalledisNOk;
    boolean                 errorServRest;


    private OnFragmentInteractionListener mListener;

    public CreateHCFragment() {
        // Required empty public constructor
    }


    /**
     * Can be called when a new CreteHCFragment is needed
     *
     * @return CreateHCFragment
     */

    public static CreateHCFragment newInstance() {
        CreateHCFragment fragment = new CreateHCFragment();
        Bundle           args     = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_create_he, container, false);
        ButterKnife.bind(this, v);
        initSpinner();


        validator.put(siretNumber, new QuickRule<EditText>() {

            /**
             * Checks if the rule is valid. The rule verify if the Siret syntax is valid.
             * @param view
             * @return boolean
             */
            @Override
            public boolean isValid(EditText view) {
                return FormatValidator.isSiretSyntaxValide(view.getText().toString());
            }

            /**
             * Called automaticly if the rule is not valid
             * @param context
             * @return
             */
            @Override
            public String getMessage(Context context) {
                return getString(R.string.create_he_fragment_error_siret);
            }
        });

        return v;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Method called when a user click "valider" button on the view
     *
     * @param view
     */
    @OnClick(R.id.create_he_fragment_validate_button)
    public void insertHE(final View view) {
        this.view = view;

        validator.validate(true);

    }

    /**
     * Take a radiogroup which radiobutton text are numbers and return the number which is selected.
     *
     * @param radioGroup
     * @return int
     */
    public int getIntFromRadiogroup(RadioGroup radioGroup) {
        return Integer.decode(((RadioButton) getActivity().findViewById(radioGroup.getCheckedRadioButtonId()))
                                      .getText().toString());
    }

    /**
     * Method called to initialise a new Health Center with values from the view
     *
     * @return HealthCenter
     */
    public HealthCenter initHC() {
        HealthCenter healthCenter = new HealthCenter();
        healthCenter.setName(name.getText().toString());
        healthCenter.setSiretNumber(Long.decode(siretNumber.getText().toString()));
        healthCenter.setFinessNumber(Long.decode(finesstNumber.getText().toString()));
        healthCenter.setStreetNumber(Integer.decode(streetNumber.getText().toString()));
        healthCenter.setStreetName(streetName.getText().toString());
        healthCenter.setTown(town.getText().toString());
        healthCenter.setZipCode(Integer.decode(zipCode.getText().toString()));
        if(bedNumber.getText().toString().equals("")) {
            healthCenter.setBedNumber(0);
        } else {
            healthCenter.setBedNumber(Integer.decode(bedNumber.getText().toString()));
        }
        healthCenter.setWebSite(webSite.getText().toString());
        healthCenter.setOrigin("Prospection");
        healthCenter.setIdUser(Constants.getInstance().getCurrentUser().getId());
        healthCenter.setEtablishmentType(etablishmentType.getSelectedItem().toString());
        if (((RadioButton) getActivity().findViewById(isPublic.getCheckedRadioButtonId())).getText().toString().equals("Oui"))
            healthCenter.setIsPublic(true);
        else healthCenter.setIsPublic(false);
        healthCenter.setDifficultyHavingContact(getIntFromRadiogroup(difficultyHavingContact));
        healthCenter.setServiceBuildingImage(getIntFromRadiogroup(serviceBuilding));
        if(holding.getSelectedItem() != null) {
            Holding holdingInit = new Select().from(Holding.class)
                    .where(Holding_Table.name.is(holding.getSelectedItem().toString())).querySingle();
            healthCenter.setHoldingId(holdingInit.getId());

        }
        if(holding.getSelectedItem() != null) {
            PurchasingCentral pcInit = new Select().from(PurchasingCentral.class)
                    .where(PurchasingCentral_Table.name.is(purshasingCentral.getSelectedItem().toString())).querySingle();
            healthCenter.setPurchasingCentralId(pcInit.getId());
        }


        return healthCenter;
    }

    /**
     * Initialise spinner before displaying the view
     */
    public void initSpinner() {
        holdings = new Select(Holding_Table.name).from(Holding.class).queryList();
        holding.setAdapter(new ArrayAdapter<Holding>
                                   (getActivity().getApplicationContext(), R.layout.create_customer_spinner_view, holdings));
        purchasingCentrals = new Select(PurchasingCentral_Table.name)
                .from(PurchasingCentral.class).queryList();
        purshasingCentral.setAdapter(new ArrayAdapter<PurchasingCentral>
                (getActivity().getApplicationContext(), R.layout.create_customer_spinner_view, purchasingCentrals));
        etablishmentType.setAdapter(new ArrayAdapter<EtablishmentType>
                (getActivity().getApplicationContext(), R.layout.create_customer_spinner_view, EtablishmentType.values()));
    }

    /**
     * Is called when all Rules pass from the validator.
     * Called after  validator.validate(true); from insertHE method
     */
    @Override
    public void onValidationSucceeded() {
        final HealthCenter healthCenter = initHC();

        messageRestCustomer = new MessageRestCustomer(1, healthCenter);
        Call<ResponseRestCustomer> call = RESTCustomerHandlerSingleton.getInstance().getCustomerService()
                                                                      .createHealthCenter(messageRestCustomer);
        healthCenter.save();

        call.enqueue(new Callback<ResponseRestCustomer>() {
            /**
             * Called when a good HTTP response is return
             * @param response
             * @param retrofit
             */
            @Override
            public void onResponse(Response<ResponseRestCustomer> response, Retrofit retrofit) {
                if (response.errorBody() != null) {
                    createCalledisNOk = true;
                } else {
                    if (response.body().getIsInserted() && response.body().getLat() != 0
                            && response.body().getLng() != 0) {
                        createCalledisOk = true;
                        response.body().getMapInfo().save();
                        healthCenter.setLattitude(response.body().getLat());
                        healthCenter.setLongitude(response.body().getLng());
                        healthCenter.save();
                        new TileDownloader().execute(response.body().getMapInfo());
                    } else {
                        errorServRest = true;
                    }
                }
                returnToListCustomer();
            }

            /**
             * Called when a bad HTTP response is return
             * @param t
             */
            @Override
            public void onFailure(Throwable t) {
                errorServRest = true;
                returnToListCustomer();
            }
        });
    }

    /**
     * Called when one or several Rules fail from the validator.
     * Called after  validator.validate(true); from insertHE method
     *
     * @param errors
     */
    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        String errorString = "";

        // Concat error strings
        for (ValidationError error : errors) {
            errorString = errorString + error.getCollatedErrorMessage
                    (getActivity().getApplicationContext()) + ".\n";
        }

        AlertDialog alertDialog =
                new AlertDialog.Builder(this.getActivity()).create();
        alertDialog.setTitle(R.string.create_he_fragment_dialog_error_title);
        alertDialog.setMessage(errorString);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

    }

    /**
     * Called when the Fragment is no longer started.
     * This is generally tied to Activity.onStop of the containing Activity's lifecycle.
     * If the fragment is stop after the creation of a health center, it display a snackbar
     */
    @Override
    public void onStop() {
        super.onStop();
        if (createCalledisOk) {
            Snackbar.make(view, R.string.create_he_fragment_toast_validation, Snackbar.LENGTH_LONG).show();
        } else if(createCalledisNOk || errorServRest) {
            Snackbar snackbar = Snackbar.make(view, R.string.create_he_fragment_message_customer_not_created_first_part,
                    Snackbar.LENGTH_LONG);
            ((TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text)).setMaxLines(2);

            snackbar.show();
        }

    }

    public MessageRestCustomer getMessageRestCustomer() {
        return messageRestCustomer;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }

    private void returnToListCustomer() {
        ListCustomerFragment listCustomerFragment = new ListCustomerFragment();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.customer_list_title);
        getFragmentManager().beginTransaction()
                .replace(R.id.container, listCustomerFragment).commit();
    }
}
