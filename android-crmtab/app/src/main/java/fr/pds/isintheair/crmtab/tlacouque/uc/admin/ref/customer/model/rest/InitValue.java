package fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.rest;

import com.raizlabs.android.dbflow.sql.language.Method;
import com.raizlabs.android.dbflow.sql.language.Select;

import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.Holding;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.PurchasingCentral;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.Company;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.Specialty;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.dto.ResponseRestCustomer;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by tlacouque on 28/12/2015.
 * Class used to initialise all data which are not in administration customer uc
 */
public final class InitValue {

    public static void doInit() {
        initHolding();
        initPurchasingCentral();
        initCompanies();
        initSpecialties();
    }

    /**
     * Initialise Holding if there is no holdings in the database
     */
    private static void initHolding() {
        if (new Select(Method.count()).from(Holding.class).count() == 0L) {

            Call<ResponseRestCustomer> call = RESTCustomerHandlerSingleton.getInstance().getCustomerService().getHoldings();

            call.enqueue(new Callback<ResponseRestCustomer>() {
                @Override
                public void onResponse(Response<ResponseRestCustomer> response, Retrofit retrofit) {
                    if(response.errorBody() == null) {
                        if(response.body().getHoldings() != null) {
                            for (Holding holding : response.body().getHoldings()) {
                                holding.save();
                            }
                        }
                    }

                }

                @Override
                public void onFailure(Throwable t) {
                }
            });
        }
    }

    /**
     * Initialise Purchasing Central if there is no Purchasing Central in the database
     */
    private static void initPurchasingCentral() {

        if (new Select(Method.count()).from(PurchasingCentral.class).count() == 0) {

            Call<ResponseRestCustomer> call = RESTCustomerHandlerSingleton.getInstance().getCustomerService().getPurchasingCentral();

            call.enqueue(new Callback<ResponseRestCustomer>() {
                @Override
                public void onResponse(Response<ResponseRestCustomer> response, Retrofit retrofit) {
                    if(response.errorBody() == null) {
                        if(response.body().getPurchasingCentrals() != null) {
                            for (PurchasingCentral purchasingCentral : response.body().getPurchasingCentrals()) {
                                purchasingCentral.save();
                            }
                        }
                    }

                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }
    }

    /**
     * Initialise Companies if there is no Companies in the database
     */
    private static void initCompanies() {

        if (new Select(Method.count()).from(Company.class).count() == 0) {

            Call<ResponseRestCustomer> call = RESTCustomerHandlerSingleton.getInstance().getCustomerService().getCompanies();

            call.enqueue(new Callback<ResponseRestCustomer>() {
                @Override
                public void onResponse(Response<ResponseRestCustomer> response, Retrofit retrofit) {
                    if(response.errorBody() == null) {
                        if(response.body().getCompanies() != null) {
                            for (Company company : response.body().getCompanies()) {
                                company.save();
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                }
            });
        }
    }

    /**
     * Initialise Specialties if there is no Specialties in the database
     */
    private static void initSpecialties() {

        if (new Select(Method.count()).from(Specialty.class).count() == 0) {

            Call<ResponseRestCustomer> call = RESTCustomerHandlerSingleton.getInstance()
                                                                          .getCustomerService().getSpecialties();

            call.enqueue(new Callback<ResponseRestCustomer>() {
                @Override
                public void onResponse(Response<ResponseRestCustomer> response, Retrofit retrofit) {
                    if(response.errorBody() == null) {
                        if(response.body().getSpecialties() != null) {
                            for (Specialty specialty : response.body().getSpecialties()) {
                                specialty.save();
                            }
                        }
                    }

                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }
    }


}



