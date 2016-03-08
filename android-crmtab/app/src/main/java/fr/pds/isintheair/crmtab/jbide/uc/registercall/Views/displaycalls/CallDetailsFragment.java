package fr.pds.isintheair.crmtab.jbide.uc.registercall.Views.displaycalls;

/**
 * Created by j-d on 28/12/2015.
 */
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Rest.Model.Cra;


/**
 * Created by j-d on 28/12/2015.
 */
public class CallDetailsFragment extends Fragment {

    @Bind(R.id.formtitle)
    TextView formtitle;
    @Bind(R.id.edittextcontactname) EditText contactname;
    @Bind(R.id.edittextclientname) EditText clientname;
    @Bind(R.id.edittextcontatctnumber) EditText contactnumber;
    @Bind(R.id.edittextduration) EditText duration;
    @Bind(R.id.edittextcomments) EditText comments;
    @Bind(R.id.edittextsubject) EditText subject;
    @Bind(R.id.edittextdate) EditText date;
    @Bind(R.id.edittextcalltype) EditText calltype;
    @Bind(R.id.buttonregistercra)
    Button validation;
    @Bind(R.id.vocalcomment)  Button mic;


    public static CallDetailsFragment newInstance(Cra cra) {
        CallDetailsFragment f = new CallDetailsFragment();
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString("contactname", cra.getContactname());
        args.putString("idcontact", String.valueOf(cra.getIdcontact()));
        args.putString("clientname", cra.getClientname());
        args.putString("subject", cra.getSubject());
        args.putString("comments", cra.getComments());
        args.putString("duration", String.valueOf(cra.getDuration()));
        args.putString("date", cra.getDate());
        args.putString("calltype", cra.getCalltype());
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

        formtitle.setText("Détails sur le compte-rendu");
        //contactnumber.setText(getArguments().getString("idcontact"));
        date.setText(getArguments().getString("date"));
        duration.setText(getArguments().getString("duration"));
        calltype.setText(getArguments().getString("calltype"));
        contactnumber.setText(getArguments().getString("idcontact"));

        contactname.setText(getArguments().getString("contactname"));
        clientname.setText(getArguments().getString("clientname"));


        //Disable subjects and comments fields
        subject.setEnabled(false);
        comments.setEnabled(false);
        subject.setText(getArguments().getString("subject"));
        comments.setText(getArguments().getString("comments"));
        mic.setVisibility(View.GONE);
        validation.setVisibility(View.GONE);

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


    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
