package fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.model.Report;


/**
 * Created by Muthu on 08/01/2016.
 */
public class ReportAdapter extends ArrayAdapter<Report> {

    private  Context context;
    private List<Report> objects;
    TextView title, infos , infos2, lblCount;
    public ReportAdapter(Context context,  List<Report> objects) {
        super(context, R.layout.reportlist_layout, objects);
        this.context = context;
        this.objects = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.reportlist_layout, parent, false);

        //get views
        title = (TextView) rowView.findViewById(R.id.textTitle);
        infos = (TextView) rowView.findViewById(R.id.textInfo);
        infos2 = (TextView) rowView.findViewById(R.id.textInfo2);
        lblCount = (TextView) rowView.findViewById(R.id.txtCount);


        title.setText("Compte rendu fait le " + objects.get(position).getDate());
        infos.setText("Résumé du compte rendu: " + objects.get(position).getComment());
        infos2.setText("Client satisfait? " + objects.get(position).getSatisfaction());
        lblCount.setText(objects.get(position).getDate());

        return  rowView;
    }
}
