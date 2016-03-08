package fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.view.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.view.fragment.CreateHCFragment;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.view.fragment.CreateIndepFragment;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.view.fragment.CreateCustomerAlertDialog;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.view.fragment.ListCustomerFragment;


public class CRUDCustomerActivity extends AppCompatActivity implements CreateCustomerAlertDialog.AlertPositiveListener,
        ListCustomerFragment.OnListFragmentInteractionListener {

    Toolbar          toolbar;
    CreateHCFragment createHCFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crudcustomer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.create_customer_fragment_container, new ListCustomerFragment());
        fragmentTransaction.addToBackStack("list");

        fragmentTransaction.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle(R.string.customer_list_title);
    }

    @Override
    public void onPositiveClick(int position) {
        switch (position) {
            case 0:
                createHCFragment = new CreateHCFragment();
                toolbar.setTitle(R.string.create_he_fragment_title_action_bar);
                getFragmentManager().beginTransaction()
                                    .replace(R.id.create_customer_fragment_container, createHCFragment).commit();
                break;
            case 1:

                CreateIndepFragment createIndepFragment = new CreateIndepFragment();
                toolbar.setTitle(R.string.create_indep_fragment_title_action_bar);
                getFragmentManager().beginTransaction()
                                    .replace(R.id.create_customer_fragment_container, createIndepFragment)
                                    .commit();
                break;

        }
    }

    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getFragmentManager().popBackStack();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
    }
}
