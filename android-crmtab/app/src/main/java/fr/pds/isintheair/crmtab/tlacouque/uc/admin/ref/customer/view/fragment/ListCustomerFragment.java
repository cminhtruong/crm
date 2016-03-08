package fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.view.fragment;


import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Constants;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.dto.ResponseRestCustomer;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.Customer;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.HealthCenter;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.Independant;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.rest.CheckInternetConnexion;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.rest.InitValue;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.rest.RESTCustomerHandlerSingleton;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.view.adapter.ListCustomerAdapter;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A fragment representing a list of Customers.
 */
public class ListCustomerFragment extends Fragment implements CreateCustomerAlertDialog.AlertPositiveListener {

    public String idUser = Constants.getInstance().getCurrentUser().getId();
    public List<Customer> customers;
    @Bind(R.id.list_customer_recycler_view)
    RecyclerView recyclerView;
    int position = 0;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ListCustomerFragment() {
    }


    @SuppressWarnings("unused")
    public static ListCustomerFragment newInstance(int columnCount) {
        ListCustomerFragment fragment = new ListCustomerFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        //healthCenters = InitValue.initHealthCenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_customer_list, container, false);
        ButterKnife.bind(this, v);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        initCustomers();
        return v;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        inflater.inflate(R.menu.list_customer_menu, menu);
        // super.onCreateOptionsMenu(menu, inflater);

    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Called after the user click on the button validate in CreateCustomerAlertDialog
     *
     * @param position represent the position of the radiobutton in the list
     */
    @Override
    public void onPositiveClick(int position) {
        switch (position) {
            // Health center is selected
            case 0:
                CreateHCFragment createHCFragment = new CreateHCFragment();

                getActivity().getFragmentManager().beginTransaction().addToBackStack("createHc")
                             .replace(R.id.container, createHCFragment)
                             .commit();
                break;
            //Independant is selected
            case 1:

                CreateIndepFragment createIndepFragment = new CreateIndepFragment();

                getActivity().getFragmentManager().beginTransaction().addToBackStack("createIndep")
                             .replace(R.id.container, createIndepFragment)
                             .commit();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_list_customer_add) {


            /** Instantiating the DialogFragment class */
            CreateCustomerAlertDialog alert = new CreateCustomerAlertDialog();
            alert.setAlertPositiveListener(this);

            /** Creating a bundle object to store the selected item's index */
            Bundle b = new Bundle();

            /** Storing the selected item's index in the bundle object */
            b.putInt("position", position);

            /** Setting the bundle object to the dialog fragment object */
            alert.setArguments(b);
            FragmentManager manager = getFragmentManager();
            /** Creating the dialog fragment object, which will in turn open the alert dialog window */
            alert.show(manager, "CreateCustomerDialog");

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        InitValue.doInit();
    }

    /**
     * Initialise the list of customer. if the user have an internet connexion
     * a rest call is send to have customer added by other commercials.
     */
    public void initCustomers() {

        final List<Customer> customers = (List<Customer>) (List<?>) new Select().from(HealthCenter.class).queryList();
        final List<Customer> customersindeps    = (List<Customer>) (List<?>) new Select().from(Independant.class).queryList();
        final List<HealthCenter> healthCenterList = new Select().from(HealthCenter.class).queryList();
        final List<Independant> independantList = new Select().from(Independant.class).queryList();
        customers.addAll(customersindeps);
        //Check if there is a network available
        if (CheckInternetConnexion.isNetworkAvailable(this.getActivity().getApplicationContext())) {
            callRest(customers,healthCenterList,independantList);
        } else {
            initAdapter(customers);
        }
    }

    /**
     * Called to do a rest call to have customer added by other commercials.
     *
     * @param customers
     */
    private void callRest(final List<Customer> customers,final List<HealthCenter> healthCenterList,
                          final List<Independant> independantList) {

        Call<ResponseRestCustomer> call = RESTCustomerHandlerSingleton.getInstance()
                                                                      .getCustomerService().getCustomers(this.idUser);

        call.enqueue(new Callback<ResponseRestCustomer>() {
            @Override
            public void onResponse(Response<ResponseRestCustomer> response, Retrofit retrofit) {

                if(response.errorBody() == null) {
                    List<HealthCenter> healthCenters = response.body().getHealthCenters();
                    List<Independant> independants = response.body().getIndependants();
                        if(healthCenters != null) {
                            for (HealthCenter healthCenter : response.body().getHealthCenters()) {
                                boolean alreadyOnTablet = false;
                                    for(HealthCenter healthCenterTab : healthCenterList) {
                                        if(healthCenterTab.getSiretNumber() == healthCenter.getSiretNumber()) {
                                            alreadyOnTablet = true;
                                        }
                                    }
                                if(!alreadyOnTablet) {
                                   customers.add(healthCenter);
                                }

                            }
                        }
                        if(independants != null) {
                            for (Independant independant : response.body().getIndependants()) {
                                customers.add(independant);
                            }
                        }
                }
                initAdapter(customers);
            }

            @Override
            public void onFailure(Throwable t) {
                initAdapter(customers);
            }
        });
    }

    /**
     * Method called to initialiase the list of Customer
     *
     * @param customers
     */
    private void initAdapter(List<Customer> customers) {

        ListCustomerFragment.this.customers = customers;
        recyclerView.setAdapter(new ListCustomerAdapter(customers, this.getActivity()));
    }

    public interface OnListFragmentInteractionListener {
        // void onListFragmentInteraction(DummyItem item);
    }
}
