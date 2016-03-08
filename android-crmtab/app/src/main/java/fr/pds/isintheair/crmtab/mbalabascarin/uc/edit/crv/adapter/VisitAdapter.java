package fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.model.Visit;

/**
 * Created by Muthu on 06/02/2016.
 */
public class VisitAdapter extends ArrayAdapter<Visit> {

    private Context context;
    private List<Visit> objects;
    TextView title, infos , infos2;
    public VisitAdapter(Context context,  List<Visit> objects) {
        super(context, R.layout.visitlist_layout, objects);
        this.context = context;
        this.objects = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.visitlist_layout, parent, false);

        //get views
        title = (TextView) rowView.findViewById(R.id.textTitle);
        infos = (TextView) rowView.findViewById(R.id.textInfo);
        infos2 = (TextView) rowView.findViewById(R.id.textInfo2);


        title.setText("Visite faite le: " + objects.get(position).getDate());

        for(int i=0; i<objects.get(position).getSubject().length; i++){
            infos.append(objects.get(position).getSubject()[i]+"\n");
        }



        return  rowView;
    }
}