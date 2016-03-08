package fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.view.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
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
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.Company;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.Company_Table;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.Independant;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.Specialty;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.Specialty_Table;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.enumeration.IndependantType;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.dto.MessageRestCustomer;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.dto.ResponseRestCustomer;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.rest.RESTCustomerHandlerSingleton;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by tlacouque on 27/12/2015.
 * Controller which is used to create an independant. He used to display the view, to control values,
 * and to call rest interface.
 */
public class CreateIndepFragment extends Fragment implements Validator.ValidationListener {


    @Bind(R.id.create_indep_fragment_name)
    @Order(1)
    @NotEmpty(messageResId = R.string.create_he_fragment_error_name)
    EditText name;


    @Bind(R.id.create_indep_fragment_siret_number)
    @Order(2)
    @Length(min = 14, max = 14, messageResId = R.string.create_he_fragment_error_siret_empty)
    EditText siretNumber;

    @Bind(R.id.create_indep_fragment_finess_number)
    @Order(3)
    @Length(min = 9, max = 9, messageResId = R.string.create_he_fragment_error_finess)
    EditText finesstNumber;

    @Bind(R.id.create_indep_fragment_street_number)
    @Order(4)
    @NotEmpty(messageResId = R.string.create_he_fragment_error_street_number)
    EditText streetNumber;

    @Bind(R.id.create_indep_fragment_street_name)
    @Order(5)
    @NotEmpty(messageResId = R.string.create_he_fragment_error_street_name)
    EditText streetName;

    @Bind(R.id.create_indep_fragment_town_name)
    @Order(6)
    @NotEmpty(messageResId = R.string.create_he_fragment_error_town)
    EditText town;


    @Bind(R.id.create_indep_fragment_zip_code)
    @Order(7)
    @Length(min = 5, max = 5, messageResId = R.string.create_he_fragment_error_zip_code)
    EditText zipCode;


    @Bind(R.id.create_indep_fragment_web_site)
    EditText   webSite;
    @Bind(R.id.create_indep_fragment_independant_type)
    Spinner    independantType;
    @Bind(R.id.create_indep_fragment_company)
    Spinner    company;
    @Bind(R.id.create_indep_fragment_specialty)
    Spinner    specialty;
    @Bind(R.id.create_indep_fragment_long_term_fidelity)
    RadioGroup longTermFidelity;


    List<Specialty> specialties;
    List<Company>   companies;
    Validator       validator;
    View            view;
    boolean         createCalledisOk;
    boolean         createCalledisNOk;
    boolean         errorServRest;


    private OnFragmentInteractionListener mListener;

    public CreateIndepFragment() {
        // Required empty public constructor
    }

    /**
     * Can be called when a new CreteHCFragment is needed
     *
     * @return CreateIndepFragment
     */

    public static CreateIndepFragment newInstance() {
        CreateIndepFragment fragment = new CreateIndepFragment();
        Bundle              args     = new Bundle();
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
        View v = inflater.inflate(R.layout.fragment_create_indep, container, false);
        ButterKnife.bind(this, v);
        initSpinner();


        validator.put(siretNumber, new QuickRule<EditText>() {
            /**
             * Checks if the rule is valid.T he rule verify if the Siret syntax is valid.
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

        //Used to short the company spinner by zipcode.
        zipCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Do nothing
            }

            /**
             * This method is called to notify you that the text has been changed.
             * Restrict possible values in the company spinner.
             * @param s
             */
            @Override
            public void afterTextChanged(Editable s) {
                List<Company> companies = new Select().from(Company.class)
                                                      .where(Company_Table.zipCode.like(zipCode.getText()
                                                                                               .toString()
                                                                                               .concat("%"))).or(Company_Table.id.is(1)).queryList();
                company.setAdapter(new ArrayAdapter<Company>
                                           (getActivity().getApplicationContext(), R.layout.create_customer_spinner_view, companies));
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
    @OnClick(R.id.create_indep_fragment_validate_button)
    public void insertIndep(final View view) {
        this.view = view;

        validator.validate(true);

    }

    /**
     * Take a radiogroup which radiobutton text are numbers and return the number which is selected.
     *
     * @param radioGroup
     * @return int
     */
    private int getIntFromRadiogroup(RadioGroup radioGroup) {
        return Integer.decode(((RadioButton) getActivity().findViewById(radioGroup.getCheckedRadioButtonId()))
                                      .getText().toString());
    }

    /**
     * Method called to initialise a new Independant with values from the view
     *
     * @return HealthCenter
     */
    public Independant initIndep() {

        Independant independant = new Independant();
        independant.setName(name.getText().toString());
        independant.setSiretNumber(Long.decode(siretNumber.getText().toString()));
        independant.setFinessNumber(Long.decode(finesstNumber.getText().toString()));
        independant.setStreetNumber(Integer.decode(streetNumber.getText().toString()));
        independant.setStreetName(streetName.getText().toString());
        independant.setTown(town.getText().toString());
        independant.setZipCode(Integer.decode(zipCode.getText().toString()));
        independant.setWebSite(webSite.getText().toString());
        independant.setOrigin("Prospection");
        independant.setIndependantType(independantType.getSelectedItem().toString());
        independant.setLongTermFidelity(getIntFromRadiogroup(longTermFidelity));
        independant.setIdUser(Constants.getInstance().getCurrentUser().getId());
        if(specialty.getSelectedItem() != null) {
            Specialty specialtyInit = new Select().from(Specialty.class)
                    .where(Specialty_Table.name.is(specialty.getSelectedItem().toString())).querySingle();
            independant.setSpecialtyId(specialtyInit.getId());

        }
        if(company.getSelectedItem() != null) {
            Company companyInit = new Select().from(Company.class)
                    .where(Company_Table.name.is(company.getSelectedItem().toString())).querySingle();
            independant.setCompanyId(companyInit.getId());
        }


        return independant;
    }

    /**
     * Initialise spinner before displaying the view
     */
    private void initSpinner() {
        companies = new Select(Company_Table.name).from(Company.class).queryList();

        company.setAdapter(new ArrayAdapter<Company>
                                   (getActivity().getApplicationContext(), R.layout.create_customer_spinner_view, companies));
        specialties = new Select(Specialty_Table.name)
                .from(Specialty.class).queryList();
        specialty.setAdapter(new ArrayAdapter<Specialty>
                                     (getActivity().getApplicationContext(), R.layout.create_customer_spinner_view, specialties));
        independantType.setAdapter(new ArrayAdapter<IndependantType>
                                           (getActivity().getApplicationContext(), R.layout.create_customer_spinner_view, IndependantType.values()));

    }

    /**
     * Is called when all Rules pass from the validator.
     * Called after  validator.validate(true); from insertIndep method
     */
    @Override
    public void onValidationSucceeded() {
        final Independant independant = initIndep();

        MessageRestCustomer messageRestCustomer = new MessageRestCustomer(1, independant);
        Call<ResponseRestCustomer> call = RESTCustomerHandlerSingleton.getInstance().getCustomerService()
                                                                      .createIndependant(messageRestCustomer);
        independant.save();
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
                    if (response.body().getIsInserted()) {
                        createCalledisOk = true;
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
