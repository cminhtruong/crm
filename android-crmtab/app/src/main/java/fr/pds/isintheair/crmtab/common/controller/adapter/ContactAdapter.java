package fr.pds.isintheair.crmtab.common.controller.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.common.model.database.entity.Contact;
import fr.pds.isintheair.crmtab.common.view.activity.ContactDetailActivity2;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.model.Client;

/******************************************
 * Created by        : jdatour            *
 * Creation date     : 01/23/16           *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private List<Contact> contacts;
    private Context       context;
    private Client        client;

    public ContactAdapter(Context context, List<Contact> contacts, Client client) {
        this.contacts = contacts;
        this.context = context;
        this.client = client;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactViewHolder(LayoutInflater.from(context).inflate(R.layout.cell_contact, parent, false));
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        final Contact contact = contacts.get(position);

        holder.firstNameTextView.setText(contact.getFirstName());
        holder.lastNameTextView.setText(contact.getLastName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ContactDetailActivity2.class);

                intent.putExtra("contact", contact);
                intent.putExtra("client", client);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.last_name)
        TextView lastNameTextView;

        @Bind(R.id.first_name)
        TextView firstNameTextView;

        public ContactViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
