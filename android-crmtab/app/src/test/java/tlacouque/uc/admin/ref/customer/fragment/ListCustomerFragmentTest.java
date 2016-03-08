package tlacouque.uc.admin.ref.customer.fragment;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.dto.ResponseRestCustomer;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.Customer;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.HealthCenter;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.Independant;
import retrofit.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Created by tlacouque on 23/01/2016.
 */
 public class ListCustomerFragmentTest {
    
    @Test
    public void testCreateIndepWithValidHTTPResponse() throws Exception {

        //Initialise variables, normaly it s set in the rest server
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        HealthCenter healthCenterMocked = mock(HealthCenter.class);
        Independant independantMocked = mock(Independant.class);
        List<Independant> independantsInit = new ArrayList<Independant>();
        independantsInit.add(independantMocked);
        List<HealthCenter> healthCentersInit = new ArrayList<HealthCenter>();
        healthCentersInit.add(healthCenterMocked);
        responseRestCustomer.setHealthCenters(healthCentersInit);
        responseRestCustomer.setIndependants(independantsInit);
        Response<ResponseRestCustomer> response = Response.success(responseRestCustomer);

        // actual attribute used by the class
        boolean         createCalledisOk;
        boolean         createCalledisNOk;
        boolean         errorServRest;
        List<Customer> customers = new ArrayList<Customer>();


        // actual method after rest response
        List<HealthCenter> healthCenters = response.body().getHealthCenters();
        List<Independant> independants = response.body().getIndependants();
        if(response.errorBody() == null) {

            if(healthCenters != null) {
                for (HealthCenter healthCenter : response.body().getHealthCenters()) {
                    customers.add(healthCenter);
                }
            }
            if(independants != null) {
                for (Independant independant : response.body().getIndependants()) {
                    customers.add(independant);
                }
            }
        }
        assertEquals(customers.size(), 2L);
    }

}
