package tlacouque.uc.admin.ref.customer.rest;

import com.squareup.okhttp.ResponseBody;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.dto.ResponseRestCustomer;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.Company;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.Holding;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.PurchasingCentral;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.Specialty;
import retrofit.Response;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Created by tlacouque on 23/01/2016.
 */
 public class InitValueTest {



    @Test
    public void testInitHoldingWithValidHTTPResponse() throws Exception {
        Holding holdingMocked = mock(Holding.class);
        List<Holding> holdings = new ArrayList<>();
        holdings.add(holdingMocked);
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        responseRestCustomer.setHoldings(holdings);
        Response<ResponseRestCustomer> response = Response.success(responseRestCustomer);
        boolean bool = false;
        // actual method after rest response
        if(response.errorBody() == null) {
            if(response.body().getHoldings() != null) {
                for (Holding holding : response.body().getHoldings()) {
                    holding.save();
                    bool = true;
                }
            }
        }
        assertTrue(bool);
    }


    @Test
    public void testInitHoldingWithoutValidHTTPResponse() throws Exception {
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        ResponseBody body = ResponseBody.create(null,"");
        Response<ResponseRestCustomer> response = Response.error(500,body);
        boolean bool = false;
        // actual method after rest response
        if(response.errorBody() == null) {
            if(response.body().getHoldings() != null) {
                for (Holding holding : response.body().getHoldings()) {
                }
            }

        } else {
            bool = true;
        }
        assertTrue(bool);
    }

    @Test
    public void testPurchasingCentralWithOKHttpResponse() throws Exception {
        PurchasingCentral purchasingCentralMocked = mock(PurchasingCentral.class);
        List<PurchasingCentral> purchasingCentrals = new ArrayList<>();
        purchasingCentrals.add(purchasingCentralMocked);
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        responseRestCustomer.setPurchasingCentrals(purchasingCentrals);
        Response<ResponseRestCustomer> response = Response.success(responseRestCustomer);
        
        boolean bool = false;
        // actual method after rest response
        if(response.errorBody() == null) {
            if(response.body().getPurchasingCentrals() != null) {
                for (PurchasingCentral purchasingCentral : response.body().getPurchasingCentrals()) {
                    purchasingCentral.save();
                    bool = true;
                }
            }
        }
        assertTrue(bool);
    }


    @Test
    public void testCompaniesWithOKHTTPResponse() throws Exception {
        Company companyMocked = mock(Company.class);
        List<Company> companies = new ArrayList<>();
        companies.add(companyMocked);
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        responseRestCustomer.setCompanies(companies);
        Response<ResponseRestCustomer> response = Response.success(responseRestCustomer);

        boolean bool = false;
        // actual method after rest response
        if(response.errorBody() == null) {
            if(response.body().getCompanies() != null) {
                for (Company company : response.body().getCompanies()) {
                    company.save();
                    bool = true;
                }
            }
        }
        assertTrue(bool);
    }

    @Test
    public void testSpecialtiesWithOKHTTPResponse() throws Exception {
        Specialty specialtyMocked = mock(Specialty.class);
        List<Specialty> specialties = new ArrayList<>();
        specialties.add(specialtyMocked);
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        responseRestCustomer.setSpecialties(specialties);
        Response<ResponseRestCustomer> response = Response.success(responseRestCustomer);

        boolean bool = false;
        // actual method after rest response
        if(response.errorBody() == null) {
            if(response.body().getSpecialties() != null) {
                for (Specialty specialty : response.body().getSpecialties()) {
                    specialty.save();
                    bool = true;
                }
            }
        }
        assertTrue(bool);

    }
}

