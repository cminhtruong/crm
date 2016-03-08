package fr.pds.isintheair.crmtab.common.model.rest;

import fr.pds.isintheair.crmtab.common.model.database.entity.User;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by jbide on 22/01/2016.
 */
public interface LoginServiceInterface {

    @POST("login")
    Call<User> login(@Body User user) ;
}
