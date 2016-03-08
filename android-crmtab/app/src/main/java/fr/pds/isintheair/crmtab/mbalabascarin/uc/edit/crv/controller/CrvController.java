package fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import fr.pds.isintheair.crmtab.common.model.database.entity.Contact;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.cache.CacheDao;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.model.Client;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.model.Report;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.model.Visit;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.retrofit.CrvRetrofitService;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.view.CrvHomeActivity;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.view.CrvMainActivity;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.dto.ResponseRestCustomer;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.HealthCenter;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Muthu on 06/01/2016.
 */
public class CrvController {
    List<Report> reports = new ArrayList<Report>();
    List<Client> clients = new ArrayList<Client>();
    List<HealthCenter> healthCenters = new ArrayList<HealthCenter>();
    Client client;

    Boolean status;
    public List<Report> getAllReportForClient(String idClient , final Client client,final Contact contact, final Visit visit, final Context context){
        Gson gson = new GsonBuilder()
                .disableHtmlEscaping()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CrvConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        retrofit.client().setConnectTimeout(5000, TimeUnit.MILLISECONDS);
        CrvRetrofitService iCrvRetrofitService = retrofit.create(CrvRetrofitService.class);
        Call<List<Report>> call = iCrvRetrofitService.getReportList(idClient);

        call.enqueue(new Callback<List<Report>>() {

            @Override
            public void onResponse(Response<List<Report>> response, Retrofit retrofit) {
                reports = response.body();
                Log.d("onResponse", "" + response.body().size());


                Intent intent = new Intent(context, CrvMainActivity.class);
                intent.putExtra("client", client);
                intent.putExtra("contact", contact);
                intent.putExtra("visit", visit);
                Bundle b = new Bundle();
                b.putSerializable("list", (Serializable) reports);
                intent.putExtra("listReport", b);
                context.startActivity(intent);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("onFailure", "onFailure()");
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                List<Report> crvFromCache = new ArrayList<Report>();
                Gson gson = new Gson();

                CacheDao dao = new CacheDao(context);
                ArrayList<String> reportsJson = dao.getAllReports();

                for(String json : reportsJson){
                    Report deserializedReport = gson.fromJson(json, Report.class);
                    if(Long.valueOf(deserializedReport.getClient()).longValue() == client.getClientId()){
                        crvFromCache.add(deserializedReport);
                    }

                }
                dao.close();

                //get reports from cache
                Intent intent = new Intent(context, CrvMainActivity.class);
                intent.putExtra("client", client);
                intent.putExtra("contact", contact);
                intent.putExtra("visit", visit);
                Bundle b = new Bundle();
                b.putSerializable("list", (Serializable) crvFromCache);
                intent.putExtra("listReport", b);
                context.startActivity(intent);
            }
        });
        return reports;
    }

    public Boolean deleteReport(String id, final Client client, final Context context){


        Gson gson = new GsonBuilder()
                .disableHtmlEscaping()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CrvConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        CrvRetrofitService iCrvRetrofitService = retrofit.create(CrvRetrofitService.class);
        Call<Boolean> call = iCrvRetrofitService.deleteReport(id);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Response<Boolean> response, Retrofit retrofit) {
                if(response.isSuccess()){
                    status = true;
                   // getAllReportForClient(Integer.toString(client.getClientId()),client,context);


                }else{
                    status = false;
                }
            }

            @Override
            public void onFailure(Throwable t) {
                    status = false;
            }
        });

        return status;
    }

    public List<Client> getClientsForUser(String idUser,final Context context){
        Gson gson = new GsonBuilder()
                .disableHtmlEscaping()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CrvConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        retrofit.client().setConnectTimeout(5000, TimeUnit.MILLISECONDS);
        CrvRetrofitService iCrvRetrofitService = retrofit.create(CrvRetrofitService.class);
        Call<ResponseRestCustomer> call = iCrvRetrofitService.getClientList(idUser);
        call.enqueue(new Callback<ResponseRestCustomer>() {

            @Override
            public void onResponse(Response<ResponseRestCustomer> response, Retrofit retrofit) {
                    if(response !=null) try {
                        {
                            healthCenters = response.body().getHealthCenters();
                            if(healthCenters != null){
                                for(HealthCenter hc : healthCenters){
                                    client = new Client();


                                    client.setClientId(hc.getSiretNumber());
                                    client.setClientName(hc.getName());
                                    client.setClientAddress(hc.getAdress());
                                    clients.add(client);


                                }

                                Toast.makeText(context, "result: "+ clients.size(), Toast.LENGTH_SHORT).show();

                                //get reports from cache
                                Intent intent = new Intent(context, CrvHomeActivity.class);

                                Bundle b = new Bundle();
                                b.putSerializable("list", (Serializable) clients);
                                intent.putExtra("listClient", b);
                                context.startActivity(intent);
                            }
                        }
                    } catch (Exception e) {
                        Toast.makeText(context, "Erreur: ", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });


        return clients;
    }

}
