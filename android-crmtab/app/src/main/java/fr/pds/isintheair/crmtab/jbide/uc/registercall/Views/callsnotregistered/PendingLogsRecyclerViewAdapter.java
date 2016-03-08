package fr.pds.isintheair.crmtab.jbide.uc.registercall.Views.callsnotregistered;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.common.model.database.entity.Contact;
import fr.pds.isintheair.crmtab.common.view.activity.MainActivity;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Constants;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.database.dao.CallEndedDAO;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.database.entity.CallEndedEvent;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Events.DisplayAddLogFragmentEvent;


public class PendingLogsRecyclerViewAdapter extends RecyclerView.Adapter<PendingLogsRecyclerViewAdapter.ViewHolder> {

    private final List<CallEndedEvent> mValues;
    private final PendingLogsFragment.OnListFragmentInteractionListener mListener;
    private final MainActivity context;

    public PendingLogsRecyclerViewAdapter(List<CallEndedEvent> items, PendingLogsFragment.OnListFragmentInteractionListener listener,MainActivity con) {
        mValues = items;
        mListener = listener;
        context = con;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_a_pending_call, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(String.valueOf(position+1));
        holder.mDate.setText(mValues.get(position).getDate());
        Contact co = (Contact) Contact.getNameFromNumber(mValues.get(position).getIdcontact());
        if(co!=null)
        holder.mContact.setText(co.getLastName()+" "+co.getFirstName());
        holder.mClient.setText("client name"/*mValues.get(position).getClientname()*/);

        holder.yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               Constants.getInstance().getCurrentBusInstance().post(new DisplayAddLogFragmentEvent(new CallEndedEvent(
                       mValues.get(position).getCalltype(),
                       mValues.get(position).getDate(),
                       mValues.get(position).getDuration(),
                       mValues.get(position).getIdcontact())));
            }
        });

        holder.no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(context).setTitle("Suppression de compte-rendu").setMessage("Confirmer la suppression ?")
                            .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    CallEndedDAO.delete(mValues.get(position).getId());
                                    context.showNotificationListFrag();
                                    //Intent resultIntent = new Intent(context, MainActivity.class);
                                    //resultIntent.putExtra("msg", "notification");
                                    //notifyDataSetChanged();

                                }
                            }).setNegativeButton("Non", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();


                    //Constants.getInstance().getPendindList().remove(position);

                }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mDate;
        public final TextView mContact;
        public final TextView mClient;
        public final Button yes;
        public final Button no;
        public CallEndedEvent mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id1);
            mDate = (TextView) view.findViewById(R.id.showdate1);
            mContact = (TextView) view.findViewById(R.id.showcontact1);
            mClient = (TextView) view.findViewById(R.id.showclient1);
            yes = (Button) view.findViewById(R.id.yes);
            no = (Button) view.findViewById(R.id.no);
        }

    }
}
