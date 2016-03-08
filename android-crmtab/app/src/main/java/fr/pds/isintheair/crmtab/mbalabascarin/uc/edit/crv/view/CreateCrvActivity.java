package fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.view;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.common.model.database.entity.User;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.CallController;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.cache.CacheDao;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.controller.CrvConfig;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.mock.RandomInformation;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.model.Client;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.model.Product;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.model.Report;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.model.Reporting;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.retrofit.CrvRetrofitService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class CreateCrvActivity extends AppCompatActivity {

    private final int REQ_CODE_SPEECH_INPUT = 100;
    //Init all var
    TextView commercial, date, contact, tel, comment, client;
    CheckBox ch1, ch2, ch3, ch4;
    Button btnMessageList, btnList;
    RadioButton radio;
    ListView listView, lstProducts;
    CardView card;
    List<String> messages = new ArrayList<String>();
    String userId, clientId, conatcId, visitId;
    RadioGroup radioGroup, grpContacter;
    List<String> presentedProducts = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    RadioButton          satisfaction;
    Report               report;
    CacheDao             dao;
    Report crv = new Report();
    TextView txtRelation, txtInformation, txtProducts, txtNote;
    SeekBar relation, information, productSatisfaction;
    ImageView imgMeteo;
    View promptView;
    int progress = 0;
    int note = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_crv);


        //get session user id
        User              currentUser = new User();
        SharedPreferences prefs       = PreferenceManager.getDefaultSharedPreferences(this);
        currentUser.setId(prefs.getString("id", null));


        //get all preformatted messages from cache
        dao = new CacheDao(this);
        messages = dao.getAllMessages();


        //get all views
        commercial = (TextView) findViewById(R.id.txtName);
        date = (TextView) findViewById(R.id.txtDate);
        contact = (TextView) findViewById(R.id.txtContact);
        tel = (TextView) findViewById(R.id.txtTel);
        comment = (EditText) findViewById(R.id.txtComment);
        btnMessageList = (Button) findViewById(R.id.btnMessageList);
        card = (CardView) findViewById(R.id.card_view2);
        client = (TextView) findViewById(R.id.lblInfoClient);
        radioGroup = (RadioGroup) findViewById(R.id.grpSatisfaction);
        lstProducts = (ListView) findViewById(R.id.lstProducts);
        btnList = (Button) findViewById(R.id.btnList);


        ch1 = (CheckBox) findViewById(R.id.chk1);
        ch2 = (CheckBox) findViewById(R.id.chk2);
        ch3 = (CheckBox) findViewById(R.id.chk3);
        ch4 = (CheckBox) findViewById(R.id.chk4);





        //get mocked client object
        Intent intent = getIntent();

        Client cl = (Client) intent.getSerializableExtra("ClientObject");
        report = (Report) intent.getSerializableExtra("report");


        //if "presentation prosuit" selected, enable product list button to show mocked product list
        ch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (compoundButton.isChecked()) {
                    //checked
                    btnList.setEnabled(true);
                }
                else {
                    //not checked
                    btnList.setEnabled(false);
                    presentedProducts.clear();
                    adapter.notifyDataSetChanged();
                }

            }
        });


        try {

            //Mock pre formated information
            JSONObject json = new JSONObject(new RandomInformation().getRandomInfo());
            JSONObject mockObject = new JSONObject(json.getJSONObject("mock").toString());
            commercial.setText(mockObject.get("user").toString());
            contact.setText(mockObject.get("contact").toString());
            tel.setText(mockObject.get("phoneNumber").toString());


            //Mock a visit
            int rand = RandomInformation.randInt(1, 4);

            client.setText(cl.getClientName() + " -- " + cl.getClientAddress());
            clientId = Long.toString(cl.getClientId());
            userId = currentUser.getId();
            conatcId = Integer.toString(rand);
            visitId = Integer.toString(rand);
            date.setText(getDate());


            //Select a random visit report subject
            if (rand == 1) {
                ch1.setChecked(true);


            }
            else if (rand == 2) {
                ch2.setChecked(true);

            }
            else if (rand == 3) {
                ch3.setChecked(true);

            }
            else if (rand == 4) {
                ch4.setChecked(true);
                //  lstProducts.setVisibility(View.VISIBLE);
                btnList.setEnabled(true);

            }

            if (report != null) {
                Product[] products = report.getProduct();
                if (products != null) {
                    for (int i = 0; i < products.length; i++) {
                        presentedProducts.add(products[i].getName());
                        comment.setText(report.getComment());
                        String sat = report.getSatisfaction();
                    }
                }


                date.setText(report.getDate());
                comment.setText(comment.getText());
                //get array of radio buttons from radio group
                int count = radioGroup.getChildCount();
                ArrayList<RadioButton> listOfRadioButtons = new ArrayList<RadioButton>();
                for (int j = 0; j < count; j++) {
                    View o = radioGroup.getChildAt(j);
                    if (o instanceof RadioButton) {
                        listOfRadioButtons.add((RadioButton) o);
                    }
                }
                for (RadioButton rd : listOfRadioButtons) {
                    if (rd.getText().toString().equalsIgnoreCase(report.getSatisfaction())) {
                        rd.setChecked(true);
                        break;
                    }
                }
            }

            //Init presented Product list
            // Define a new Adapter
            adapter = new ArrayAdapter<String>(this,
                                               android.R.layout.simple_list_item_1, android.R.id.text1, presentedProducts);


            // Assign adapter to ListView
            lstProducts.setAdapter(adapter);


            // ListView Item Click Listener
            lstProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {



                    //launch a new dialog box to delete if a product is selected
                    showDialogBox(position);

                }

            });


        }
        catch (JSONException e) {
            e.printStackTrace();
        }


    }

    //Dialog box that enables the option to delete a product in the list
    public void showDialogBox(final int position) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CreateCrvActivity.this);

        // set title
        alertDialogBuilder.setTitle(":)");

        // set dialog message
        alertDialogBuilder
                .setMessage("Êtes-vous sûr de supprimer ce produit?")
                .setCancelable(false)
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        presentedProducts.remove(position);
                        adapter.notifyDataSetChanged();

                    }
                })
                .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }


    //Init mocked product lsit
    public void launchInputDialogProduct(View v) {

        List<String> products = new ArrayList<String>();

        //mock small number of products
        products.add("Seringue");
        products.add("Spatules");
        products.add("Compresses");
        products.add("Robinet à 3 voies");
        products.add("Champs stérile");
        products.add("aiguille trocart");
        products.add("Garot");
        products.add("Pansemants");
        products.add("Absorbex");
        products.add("Laryngoscope");
        products.add("Stethescope");
        products.add("Ballon autoremplisseur à valve unidirectionnel");

        LayoutInflater      layoutInflater     = LayoutInflater.from(CreateCrvActivity.this);
        View                promptView         = layoutInflater.inflate(R.layout.msg_layout, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CreateCrvActivity.this);
        alertDialogBuilder.setView(promptView);

        TextView             txtTitle             = (TextView) promptView.findViewById(R.id.textViewMessage);
        Button               btnMessage           = (Button) promptView.findViewById(R.id.btnAddMessage);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) promptView.findViewById(R.id.autoCompleteTextView1);


        txtTitle.setText("Choisir les produits présentés");
        btnMessage.setVisibility(View.INVISIBLE);
        // Get ListView object from xml
        listView = (ListView) promptView.findViewById(R.id.lstMessages);

        // Define a new Adapter
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                                                                 android.R.layout.simple_list_item_1, android.R.id.text1, products);


        // Assign adapter to ListView
        listView.setAdapter(adapter1);

        autoCompleteTextView.setAdapter(adapter1);
        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);
                //comment.append(" " + itemValue);
                // Show Alert
                /*Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();*/
                if (!presentedProducts.contains(itemValue)) {
                    presentedProducts.add(itemValue);
                }

                adapter.notifyDataSetChanged();
            }

        });

        // setup a dialog window
        alertDialogBuilder.setCancelable(false)

                          .setNegativeButton("Fermer",
                                             new DialogInterface.OnClickListener() {
                                                 public void onClick(DialogInterface dialog, int id) {
                                                     dialog.cancel();
                                                 }
                                             });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();


    }

    // launch dialog box with pre formated message list
    public void launchInputDialog(View v) {
        messages = dao.getAllMessages();
        // get prompts.xml view
        LayoutInflater      layoutInflater     = LayoutInflater.from(CreateCrvActivity.this);
        View                promptView         = layoutInflater.inflate(R.layout.msg_layout, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CreateCrvActivity.this);
        alertDialogBuilder.setView(promptView);

        // Get ListView object from xml
        listView = (ListView) promptView.findViewById(R.id.lstMessages);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) promptView.findViewById(R.id.autoCompleteTextView1);
        // Define a new Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                                                                android.R.layout.simple_list_item_1, android.R.id.text1, messages);


        // Assign adapter to ListView
        listView.setAdapter(adapter);
        autoCompleteTextView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);
                comment.append(" " + itemValue);
                // Show Alert
                /*Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();*/


            }

        });

        // setup a dialog window
        alertDialogBuilder.setCancelable(false)

                          .setNegativeButton("Fermer",
                                             new DialogInterface.OnClickListener() {
                                                 public void onClick(DialogInterface dialog, int id) {
                                                     dialog.cancel();
                                                 }
                                             });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();

    }

    public String getDate() {
        return java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
    }

    public void createReport() {
        Gson gson = new GsonBuilder()
                .disableHtmlEscaping()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CrvConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        CrvRetrofitService iCrvRetrofitService = retrofit.create(CrvRetrofitService.class);


        //create reporting object


        Reporting reporting = new Reporting();


        int       count    = presentedProducts.size();
        Product[] products = new Product[count];
        if (count > 0) {

            Product p;

            for (int i = 0; i < count; i++) {
                p = new Product();
                p.setName(presentedProducts.get(i));
                products[i] = p;
            }

        }


        int selectedID = radioGroup.getCheckedRadioButtonId();

        satisfaction = (RadioButton) findViewById(selectedID);

        if (report != null) {
            crv.setId(report.getId());
        }
        else {
            crv.setId("");
        }

        crv.setCommercial(userId);
        crv.setDate(date.getText().toString());
        crv.setSatisfaction(satisfaction.getText().toString());
        crv.setComment(comment.getText().toString());
        crv.setClient(clientId);
        crv.setContact(conatcId);
        crv.setVisit(visitId);
        crv.setProduct(products);
        reporting.setReport(crv);


        Call<Boolean> call = iCrvRetrofitService.createReport(reporting);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Response<Boolean> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    Log.i("rest response", "Report created");
                    Toast.makeText(CreateCrvActivity.this, "Compte rendu de visite a bien été crée :)", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(CreateCrvActivity.this, "Response: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("rest response", t.toString());
            }
        });
    }


    public void launchVoiceRec(View v) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Dites quelque chose...");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        }
        catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                           "Speech not supported",
                           Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    comment.append(result.get(0));
                }
                break;
            }

        }
    }

    //this method enables first block in UI to edit fields
    public void editInfo(View view) {
        // commercial.setEnabled(true);
        date.setEnabled(true);
        contact.setEnabled(true);
        tel.setEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_crv, menu);
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
        if (id == R.id.action_save) {

            //call HttpRequestTask to send json to REST server
            // HttpRequestTask request = new HttpRequestTask();

            //tell that it comes from create report activity to send
            //json to correct url end point

            // request.setActivity("create");
            //request.setRequestJson(createJson());
            //request.execute();
            createReport();

            //create report locally
            showDialogBoxForLocalSave();

            Toast.makeText(this, "calling httprequest", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //Launch launchPreformattedMessageActivity
    public void launchPreformattedMessageActivity(View view) {
        Intent intent = new Intent(this, CrvPreformatedMessage.class);
        startActivity(intent);
    }


    public void showDialogBoxForLocalSave(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title
        alertDialogBuilder.setTitle("Sauvegarde local");

        // set dialog message
        alertDialogBuilder
                .setMessage("Sauvegarde local permet de garder une copie du compte rendu en local. Vous pouvez accéder à vos compte rendus" +
                        "local quand vous avez une perte de connexion vers le réseau." +
                        "\nVoulez vous garder une copie de ce compte rendu en local?")
                .setCancelable(true)
                .setPositiveButton("Oui",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                       //save report in cache
                        Gson gson = new Gson();
                        String json = gson.toJson(crv);

                        dao.insertReport(json);
                        dao.close();
                    }
                })
                .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }


    //call a contact
    public void callContact(View view){
        CallController.call(tel.getText().toString());
    }

    public void showSatisfactionDetail(View view){
        note= 0;
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        promptView = layoutInflater.inflate(R.layout.satisfaction_dialog, null);

        relation = (SeekBar) promptView.findViewById(R.id.seekBarRelation);
        information = (SeekBar) promptView.findViewById(R.id.seekBarInformations);
        productSatisfaction = (SeekBar) promptView.findViewById(R.id.seekBarProduits);

        txtRelation = (TextView) promptView.findViewById(R.id.txtRelation);
        txtInformation = (TextView) promptView.findViewById(R.id.txtInformation);
        txtProducts = (TextView) promptView.findViewById(R.id.txtProduits);
        txtNote = (TextView) promptView.findViewById(R.id.noteGlobale);
        imgMeteo = (ImageView) promptView.findViewById(R.id.imgMeteo);

        grpContacter = (RadioGroup)promptView.findViewById(R.id.grpContacter);



        //set on seek progress listener
        relation.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress = i;
                txtRelation.setText(progress +"/5");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                note += progress;
            }
        });

        information.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress = i;
                txtInformation.setText(progress +"/5");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                note += progress;
            }
        });

        productSatisfaction.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress = i;
                txtProducts.setText(progress + "/5");


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                note += progress;
            }
        });



        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(promptView);




        // setup a dialog window
        alertDialogBuilder.setCancelable(false)

                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //save all values in an object

                            }
                        })
                .setNegativeButton("Annuler",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });


        // create an alert dialog
        final AlertDialog alert = alertDialogBuilder.create();

        alert.show();



    }

    public void calculateNote(View view){
        relation.setEnabled(false);
        information.setEnabled(false);
        productSatisfaction.setEnabled(false);

        //block all components from being modified after validating the result
        view.setEnabled(false);
        for (int i = 0; i < grpContacter.getChildCount(); i++) {
            grpContacter.getChildAt(i).setEnabled(false);
        }

        //get checked radio button
        RadioButton contactAnswer = (RadioButton) promptView.findViewById(grpContacter.getCheckedRadioButtonId());

        //get the text of selected radio button to set points
        String result = contactAnswer.getText().toString();
        if(result.equalsIgnoreCase("oui")){
            note+=5;
        }else if(result.equalsIgnoreCase("non")){
            note = note-2;
        }else if(result.equalsIgnoreCase("aucune reponse")){
            note+=2;
        }

        txtNote.setText("Note globale: " + note);


        if(note <= 5){
            imgMeteo.setImageDrawable(getResources().getDrawable(R.drawable.meteo_pas_satisfait));
            radio = (RadioButton) findViewById(R.id.rdNon);
            radio.setChecked(true);
        }else if(note >5 && note <=10){
            imgMeteo.setImageDrawable(getResources().getDrawable(R.drawable.meteo_satisfait_moyen));
            radio = (RadioButton) findViewById(R.id.rdMoyen);
            radio.setChecked(true);
        }else if(note >10){
            imgMeteo.setImageDrawable(getResources().getDrawable(R.drawable.meteo_satisfait));
            radio = (RadioButton) findViewById(R.id.rdOui);
            radio.setChecked(true);
        }

        note = 0;



    }
}

