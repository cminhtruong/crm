package fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.retrofit;

import java.util.List;

import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.model.Report;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.model.Reporting;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.dto.ResponseRestCustomer;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Muthu on 30/12/2015.
 */
public interface CrvRetrofitService {
    @POST("crv/addCrv")
    Call<Boolean> createReport(@Body Reporting reporting);

    @GET("crv/getCrv/{client}")
    Call<List<Report>> getReportList(@Path("client") String client);

    @GET("crv/deleteCrv/{idCrv}")
    Call<Boolean> deleteReport(@Path("idCrv") String idReport);

    @GET("customer/{idUser}")
    Call<ResponseRestCustomer> getClientList(@Path("idUser")String idUser);

}
