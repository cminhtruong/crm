package fr.pds.isintheair.crmtab.jbide.uc.registercall.Views.displaycalls;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Rest.Model.Cra;


public class CallLogRecyclerViewAdapter extends RecyclerView.Adapter<CallLogRecyclerViewAdapter.ViewHolder> {

    private final List<Cra> liste;
    private final DisplayCallLogFragment.OnListFragmentInteractionListener mListener;

    public CallLogRecyclerViewAdapter(List<Cra> items, DisplayCallLogFragment.OnListFragmentInteractionListener listener) {
        liste = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_calllog_fragment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = liste.get(position);
        holder.mIdView.setText(Integer.toString(position+1));
        holder.mDate.setText(liste.get(position).getDate());
        holder.mContact.setText(liste.get(position).getContactname());
        holder.mClient.setText(liste.get(position).getClientname());
        holder.mSubject.setText(liste.get(position).getSubject());
        //holder.mContentView.setText(mValues.get(position).content);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return liste.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       // @Bind(R.id.showsubject) TextView mSubjet;
        public final View mView;
        public final TextView mIdView;
        public final TextView mDate;
        public final TextView mContact;
        public final TextView mClient;
        public final TextView mSubject;
        public Cra mItem;

        public ViewHolder(View view) {
            super(view);
            //ButterKnife.bind(this, view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mDate = (TextView) view.findViewById(R.id.showdate);
            mSubject = (TextView) view.findViewById(R.id.showsubject);
            mClient = (TextView) view.findViewById(R.id.showclient);
            mContact = (TextView) view.findViewById(R.id.showcontact);
            mItem = new Cra();

        }

    }
}
