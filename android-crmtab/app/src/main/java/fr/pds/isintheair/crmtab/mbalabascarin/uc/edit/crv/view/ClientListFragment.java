package fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.view;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.common.model.database.entity.User;
import fr.pds.isintheair.crmtab.common.view.fragment.ContactListFragment;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.mock.MockClient;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.model.Client;


public class ClientListFragment extends ListFragment {


    List<String> clients = new ArrayList<String>();
    List<Client> mockedClients = new ArrayList<Client>();
    MockClient mockClient;
   ListView listView, list;
    Client client;
    ArrayAdapter<String> adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        //get 5 mocked clients
        mockClient = new MockClient();
       // mockedClients = mockClient.getClients();

        //get session user id
        User currentUser = new User();
        SharedPreferences prefs       = PreferenceManager.getDefaultSharedPreferences(getActivity());
        currentUser.setId(prefs.getString("id", null));


        View myFragmentView = inflater.inflate(R.layout.fragment_client_list,
                container, false);


        list = (ListView) myFragmentView.findViewById(R.id.list);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
         client = mockedClients.get(position);

        launchListDialog();
    }

    public void updateClients(List<Client> list){
        mockedClients = list;
        for(Client client : mockedClients){
            clients.add(client.getClientId() +" -- " +client.getClientName() +" -- "+client.getClientAddress());
        }

        adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, clients);



        // mockedClients =
        adapter.notifyDataSetChanged();

        setListAdapter(adapter);
    }
    public void launchListDialog(){
        //create option list
        final List<String> options = new ArrayList<String>();
        options.add("Contact");
        //options.add("Visite");
        options.add("Agenda");
        options.add("Tâches");
        options.add("Information");

        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View promptView = layoutInflater.inflate(R.layout.options_layout, null);

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(promptView);

        // setup a dialog window
        alertDialogBuilder.setCancelable(true)

                .setNegativeButton("Fermer",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        final AlertDialog alert = alertDialogBuilder.create();

        // Get ListView object from xml
        listView = (ListView) promptView.findViewById(R.id.lstOptions);

        // Define a new Adapter
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, options);

        // Assign adapter to ListView
        listView.setAdapter(adapter1);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);

                if(itemValue.equalsIgnoreCase("visite")){





                }
                if(itemValue.equalsIgnoreCase("contact")){

                    ContactListFragment contactListFragment = new ContactListFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();

                    transaction.replace(R.id.fragment_place, contactListFragment);
                    transaction.addToBackStack(null);

                    Bundle args = new Bundle();
                    args.putSerializable("client", client);
                    contactListFragment.setArguments(args);
                    transaction.commit();
                    alert.cancel();

                }
            }

        });


        alert.show();

    }


}
