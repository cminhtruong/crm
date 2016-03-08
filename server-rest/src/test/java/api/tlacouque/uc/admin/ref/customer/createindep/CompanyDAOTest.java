package api.tlacouque.uc.admin.ref.customer.createindep;

import com.mongodb.MongoClient;
import miage.pds.MongoDatastoreConfig;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createindep.dao.CompanyDAO;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createindep.entities.Company;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.List;

import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by tlacouque on 17/01/2016.
 */
public class CompanyDAOTest {

    MongoClient mongoClient;
    Morphia morphia;
    Datastore datastore;
    CompanyDAO companyDAO;
    String dbName = "crm";


    @Before
    public void setUp() throws Exception {

        companyDAO = new CompanyDAO(MongoDatastoreConfig.getDataStore());

    }

    @Test
    public void testFindAll() throws Exception {
        List<Company> companies = companyDAO.findAll();
        assertEquals(34,companies.size());
        assertThat(35,not(companies.size()));
    }
}
