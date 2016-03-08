package fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.adapter.ReportAdapter;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.controller.CrvController;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.mock.RandomInformation;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.model.Client;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.model.Report;


public class CrvMainActivity extends AppCompatActivity {

    Client client;
    Report report;
    long clientId;
    ListView reportListView;
    ReportAdapter adapter;
    private List<Report> reportList = new ArrayList<Report>();
    private List<String> infos = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crv_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //get list viw object
        reportListView = (ListView) findViewById(R.id.listReport);

        try {
            client = (Client) getIntent().getSerializableExtra("client");
            reportList = (ArrayList<Report>) (getIntent().getBundleExtra("listReport").getSerializable("list"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        clientId = client.getClientId();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                RandomInformation mock = new RandomInformation();
                //String json = mock.getRandomInfo();
                Intent intent = new Intent(CrvMainActivity.this, CreateCrvActivity.class);
                //intent.putExtra("mock",json);
                intent.putExtra("ClientObject", client);
                CrvMainActivity.this.startActivity(intent);


            }
        });

        //reportList = new CrvController().getAllReportForClient(Integer.toString(client.getClientId()));

        /*for(Report report : reportList){
            infos.add(report.getId() +" -- "+ report.getDate() + " Client: " + client.getClientName() +" "+client.getClientSurname());
        }*/


        // Define a new Adapter

        adapter = new ReportAdapter(this, reportList);
        adapter.notifyDataSetChanged();

        // Assign adapter to ListView
        reportListView.setAdapter(adapter);

        // ListView Item Click Listener
        reportListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;
                showDialogBox(position);


            }

        });

    }

    public void edit(View v, final int position){
        final Report itemValue = (Report) reportListView.getItemAtPosition(position);
        Intent intent = new Intent(CrvMainActivity.this, CreateCrvActivity.class);
        intent.putExtra("ClientObject", client);
        intent.putExtra("report", itemValue);
        startActivity(intent);
    }

    public void showDialogBox(final int position) {

        final Report itemValue = (Report) reportListView.getItemAtPosition(position);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title
        alertDialogBuilder.setTitle("Que voulez-vous faire ?");

        // set dialog message
        alertDialogBuilder
                .setMessage("Choisir une option")
                .setCancelable(true)
                .setPositiveButton("Modifier :)", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        // ListView Clicked item value

                        Intent intent = new Intent(CrvMainActivity.this, CreateCrvActivity.class);
                        intent.putExtra("ClientObject", client);
                        intent.putExtra("report", itemValue);
                        startActivity(intent);

                    }
                })
                .setNegativeButton("Supprimer :(", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        finish();
                        new CrvController().deleteReport(itemValue.getId(),client, CrvMainActivity.this);

                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();




    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_create_crv, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
