package fr.pds.isintheair.crmtab.jbide.uc.registercall.Views.registeracall;

import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.common.model.database.entity.Contact;
import fr.pds.isintheair.crmtab.common.view.activity.MainActivity;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Constants;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Rest.ControllerCra;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Rest.Model.Cra;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.Customer;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.HealthCenter;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.Independant;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.view.fragment.DetailHCFragment;


public class AddLogFragment extends Fragment {

    @Bind(R.id.formtitle) TextView formtitle;
    @Bind(R.id.edittextcontactname) EditText contactname;
    @Bind(R.id.edittextclientname) EditText clientname;
    @Bind(R.id.edittextcontatctnumber) EditText contactnumber;
    @Bind(R.id.edittextduration) EditText duration;
    @Bind(R.id.edittextcomments) EditText comments;
    @Bind(R.id.edittextsubject) EditText subject;
    @Bind(R.id.edittextdate) EditText date;
    @Bind(R.id.edittextcalltype) EditText calltype;
    @Bind(R.id.buttonregistercra)  Button validation;
    @Bind(R.id.vocalcomment)  Button mic;


    public static AddLogFragment newInstance(String idcontact,String date,String duration,String calltype,boolean showmic) {
        AddLogFragment f = new AddLogFragment();
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString("idcontact", idcontact);
        args.putString("duration", duration);
        args.putString("date", date);
        args.putString("calltype", calltype);
        args.putBoolean("showmic", showmic);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide  keyboard
        //getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.log_informations_fragment, container, false);
        ButterKnife.bind(this, view);

        if(getArguments().getBoolean("showmic")) mic.setBackground(getResources().getDrawable(R.drawable.mic1));


        formtitle.setText("Ajout d'un compte-rendu");
        contactnumber.setText(getArguments().getString("idcontact"));
        date.setText(getArguments().getString("date"));
        duration.setText(getArguments().getString("duration"));
        calltype.setText(getArguments().getString("calltype"));
        //should be taken from a request
        Contact co = (Contact) Contact.getNameFromNumber(getArguments().getString("idcontact"));
        if(co!=null)
        contactname.setText(co.getLastName()+" "+co.getFirstName());

        clientname.setText("Centre hospitalier Leon Binet");

        clientname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                HealthCenter he = new HealthCenter();
                he.setName("Centre hospitalier Leon Binet");
                he.setEtablishmentType("CHU");
                he.setZipCode(77160);
                he.setWebSite("htt://www.ch-provins.fr/");
                he.setBedNumber(272);
                he.setTown("Provins");
                Bundle bundle = new Bundle();
                bundle.putParcelable(DetailHCFragment.KEY_HC_ARGS, he);
                DetailHCFragment detailHCFragment = new DetailHCFragment();
                detailHCFragment.setArguments(bundle);
                (getActivity()).getFragmentManager().beginTransaction().addToBackStack("detailHc")
                        .replace(R.id.container, detailHCFragment).commit();
            }
        });
clientname.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        HealthCenter he = new HealthCenter();
        he.setName("Centre hospitalier Leon Binet");
        he.setEtablishmentType("CHU");
        he.setZipCode(77160);
        he.setWebSite("htt://www.ch-provins.fr/");
        he.setBedNumber(272);
        he.setTown("Provins");
        Bundle bundle = new Bundle();
        bundle.putParcelable(DetailHCFragment.KEY_HC_ARGS, he);
        DetailHCFragment detailHCFragment = new DetailHCFragment();
        detailHCFragment.setArguments(bundle);
        (getActivity()).getFragmentManager().beginTransaction().addToBackStack("detailHc")
                .replace(R.id.container, detailHCFragment).commit();
    }
});



        mic.setBackground(getResources().getDrawable(R.drawable.mic1));
        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchvocalcomment(new View(getActivity()));
            }
        });

        validation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cra newCra = new Cra();
                newCra.setCalltype(calltype.getText().toString());
                newCra.setClientname(clientname.getText().toString());
                newCra.setComments(comments.getText().toString());
                newCra.setContactname(contactname.getText().toString());
                newCra.setDate(date.getText().toString());
                newCra.setDuration(Long.parseLong(String.valueOf(duration.getText())));
                newCra.setIdcontact(contactnumber.getText().toString());
                newCra.setSubject(subject.getText().toString());
                newCra.setIduser(Constants.getInstance().getCurrentUser().getId());
                //local save
                //newCra.setId(0);
                //newCra.save();
                //save on server
                ControllerCra.registerCra(newCra, getActivity());
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.addlogmenu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.enter_anim, R.animator.exit_anim);

        switch(item.getItemId())
        {
            case R.id.item1:

                return true;

            case R.id.item2:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void launchvocalcomment(View v){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Parlez ...");
        try {
            startActivityForResult(intent, 100);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getActivity(),
                    "Speech not supported",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     * */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 100: {
                if (resultCode == -1 && null != data) {

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    comments.append(result.get(0));
                }
                break;
            }

        }
    }


    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
