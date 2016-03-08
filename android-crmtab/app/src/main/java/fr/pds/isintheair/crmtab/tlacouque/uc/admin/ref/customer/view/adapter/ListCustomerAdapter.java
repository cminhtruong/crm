package fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.Customer;

/**
 * Created by tlacouque on 29/12/2015.
 * Adapter to display a list of customer
 */
public class ListCustomerAdapter extends RecyclerView.Adapter<CustomerListViewHolder> {

    List<Customer> customers;
    Context        context;

    /**
     * Constructor, take a context and a customer list to pass them to customerListViewHolders
     *
     * @param customers
     * @param context
     */
    public ListCustomerAdapter(List<Customer> customers, Context context) {
        this.customers = customers;
        this.context = context;
    }


    /**
     * Method called automaticly with a creation of a new view holder
     *
     * @param parent
     * @param viewType
     * @return CustomerListViewHolder
     */
    @Override
    public CustomerListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_customer_adapter, parent, false);
        return new CustomerListViewHolder(view, this);

    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(CustomerListViewHolder holder, int position) {
        Customer myObject = customers.get(position);
        holder.bind(myObject);
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }


    public List<Customer> getCustomers() {
        return customers;
    }

    public Context getContext() {
        return context;
    }
}
