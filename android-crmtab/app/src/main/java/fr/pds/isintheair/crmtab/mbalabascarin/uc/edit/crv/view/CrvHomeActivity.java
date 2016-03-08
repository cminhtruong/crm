package fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.model.Client;


public class CrvHomeActivity extends AppCompatActivity {

    Fragment clientListFragment;
    List<Client> clients;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crv_home);
        ClientListFragment clf = (ClientListFragment) getFragmentManager().findFragmentById(R.id.fragment_place);
        clients = (ArrayList<Client>) (getIntent().getBundleExtra("listClient").getSerializable("list"));
        clf.updateClients(clients);
    }
    @Override
      public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_crv, menu);
        return true;
    }

}
