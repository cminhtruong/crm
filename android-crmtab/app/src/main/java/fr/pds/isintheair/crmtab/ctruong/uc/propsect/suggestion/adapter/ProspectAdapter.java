package fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.model.domain.Prospect;

/**
 * Created by Truong on 3/1/2016.
 */
public class ProspectAdapter extends ArrayAdapter<Prospect>{

    @Bind(R.id.tv_prospect)
    TextView tv_prospect;

    public ProspectAdapter(Context context, int resource, List<Prospect> prospects) {
        super(context, 0, prospects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ButterKnife.bind((Activity) getContext());
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_prospect, parent, false);
        }
        Prospect prospect = getItem(position);
        tv_prospect.setText(prospect.getName());

        return convertView;
    }
}
