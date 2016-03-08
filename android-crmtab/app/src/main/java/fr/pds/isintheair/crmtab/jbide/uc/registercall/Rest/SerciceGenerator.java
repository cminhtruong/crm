package fr.pds.isintheair.crmtab.jbide.uc.registercall.Rest;

import java.util.List;

import fr.pds.isintheair.crmtab.jbide.uc.registercall.Rest.Model.Cra;
import fr.pds.isintheair.crmtab.common.model.database.entity.User;


import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by j-d on 22/12/2015.
 */
public interface SerciceGenerator {


    @GET("cra/listcra")
    Call<List<Cra>> listcraforuser(@Query("iduser") String iduser) ;


    @POST("cra/create")
    Call<Boolean> createcra(@Body Cra cra) ;


    @POST("login")
    Call<User> basicLogin(@Body User user);

}
