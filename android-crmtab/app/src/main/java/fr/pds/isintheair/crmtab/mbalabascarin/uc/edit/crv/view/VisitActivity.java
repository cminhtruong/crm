package fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.common.model.database.entity.Contact;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.adapter.VisitAdapter;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.controller.CrvController;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.mock.MockVisit;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.model.Client;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.model.Visit;

public class VisitActivity extends Activity {

    List<Visit> visits;
    List<Visit> visitsToShow = new ArrayList<Visit>();
    ListView visitList;
    VisitAdapter adapter;
    Client currentClient;
    Contact currentContact;
    Visit currentVisit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit);





        visitList = (ListView) findViewById(R.id.listVisit);
        currentContact = getIntent().getParcelableExtra("contact");
        currentClient = (Client) getIntent().getSerializableExtra("client");

        visits = new MockVisit().getMockVisit();
        for(Visit visit : visits){
            if(visit.getIdContact() == currentClient.getClientId()){
                visitsToShow.add(visit);
            }
        }

        // Define a new Adapter

        adapter = new VisitAdapter(this, visitsToShow);
        adapter.notifyDataSetChanged();

        // Assign adapter to ListView
        visitList.setAdapter(adapter);
        // ListView Item Click Listener
        visitList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;
                callCrvController(position);
            }

        });



    }


    public void callCrvController(int position){
        new CrvController().getAllReportForClient(Long.toString(currentClient.getClientId()), currentClient, currentContact,currentVisit, this);

        /*final Visit itemValue = (Visit) visitList.getItemAtPosition(position);
        Intent intent = new Intent(VisitActivity.this, CrvMainActivity.class);
        intent.putExtra("client", currentClient);
        intent.putExtra("contact", currentContact);
        intent.putExtra("visit", itemValue);
        startActivity(intent);
*/
    }
}
