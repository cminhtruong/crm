package tlacouque.uc.admin.ref.customer.fragment;

import org.junit.Test;

import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.dto.ResponseRestCustomer;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.Independant;
import retrofit.Response;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Created by tlacouque on 23/01/2016.
 */
 public class CreateIndepFragmentTest {

    @Test
    public void testCreateIndepWithValidHTTPResponse() throws Exception {

        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        responseRestCustomer.setIsInserted(true);
        Response<ResponseRestCustomer> response = Response.success(responseRestCustomer);

        // actual attribute used by the class
        boolean         createCalledisOk;
        boolean         createCalledisNOk;
        boolean         errorServRest;
        Independant independant = mock(Independant.class);

        createCalledisNOk = false;
        createCalledisOk = false;
        errorServRest = false;

        // actual method after rest response
        if (response.errorBody() != null) {
            createCalledisNOk = true;
        } else {
            if (response.body().getIsInserted()) {
                independant.save();
                createCalledisOk = true;
            } else {
                errorServRest = true;
            }
        }

        assertTrue(createCalledisOk);
        assertFalse(createCalledisNOk);
        assertFalse(errorServRest);

    }
}
