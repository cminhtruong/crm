package fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.http;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Muthu on 12/12/2015.
 */
//class for http request
public class HttpRequestTask extends AsyncTask<String, Void, Void> {
    private String action;
    private String requestJson;

    public void setActivity(String action) {
        this.action = action;
    }
    public void setRequestJson(String requestJson) {
        this.requestJson = requestJson;
    }

    @Override
    protected Void doInBackground(String... params) {

        //call right method for given action
        switch (action) {
            case "create":
                createCrv();
                break;

        }

        return null;
    }


    //This method is to send JSON to REST server to create a VISIT REPORT
    /*
        It sends:
         id of the report
         Commercial firstName
         date of report creation
         Client satisfaction
         comment content
         contact id
         client id
         visit id

     */
    public void createCrv() {
        try {
            JSONObject data = new JSONObject(requestJson);
            String mockData = data.get("mock").toString();
            JSONObject mock = new JSONObject(mockData.toString());




            JSONObject crvObject = new JSONObject();
            crvObject.put("id", mock.get("id").toString());
            crvObject.put("commercial", mock.get("commercial").toString());
            crvObject.put("date", mock.get("date").toString());
            crvObject.put("satisfaction", mock.get("satisfaction").toString());
            crvObject.put("comment",  mock.get("comment").toString());
            crvObject.put("contact",  mock.get("contact").toString());
            crvObject.put("client",  mock.get("client").toString());
            crvObject.put("visit",  mock.get("visit").toString());

            JSONArray productArray = new JSONArray();
            JSONObject product = new JSONObject();
           // product.put("id", 1);
            productArray.put(product);

            crvObject.put("product", productArray);

            JSONObject mainObject = new JSONObject();
            mainObject.put("report", crvObject);


            final String url = "http://192.168.1.53:8080/api/crv/addCrv";


            makeRequest(url, mainObject.toString());
        } catch (Exception e) {
            Log.e("HttpRequestTask", e.getMessage(), e);
        }

    }



    //POST Json to REST SERVER
    public  String makeRequest(String uri, String json) {
        HttpURLConnection urlConnection;

        String data = json;
        String result = null;
        try {
            //Connect
            urlConnection = (HttpURLConnection) ((new URL(uri).openConnection()));
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestMethod("POST");
            urlConnection.connect();

            //Write
            OutputStream outputStream = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            writer.write(data);
            writer.close();
            outputStream.close();

            //Read
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

            String line = null;
            StringBuilder sb = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            bufferedReader.close();
            result = sb.toString();


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    public void getCrv(){


        URL url;
        Log.d("rest_service", "entered");

        try {
            url = new URL("http://ama-gestion-clients.appspot.com/rest/client");



        HttpURLConnection urlConnection;

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader input = new BufferedReader(new InputStreamReader(in));

			/*Toast toast = Toast.makeText(this, result, Toast.LENGTH_LONG);
				toast.show();*/
            //Log.d("RESULT",result);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}