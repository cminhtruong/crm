package fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.rest;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raizlabs.android.dbflow.structure.ModelAdapter;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by tlacouque on 13/12/2015.
 */
public class RESTCustomerHandlerSingleton {


    private static RESTCustomerHandlerSingleton mInstance;
    private final  CustomerService              customerService;

    private RESTCustomerHandlerSingleton() {
        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaredClass().equals(ModelAdapter.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .create();

        Retrofit retrofit = new Retrofit.Builder()

                .baseUrl(CustomerService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        customerService = retrofit.create(CustomerService.class);
    }

    public static synchronized RESTCustomerHandlerSingleton getInstance() {
        if (mInstance == null) {
            mInstance = new RESTCustomerHandlerSingleton();
        }

        return mInstance;
    }


    public CustomerService getCustomerService() {
        return customerService;
    }

}
