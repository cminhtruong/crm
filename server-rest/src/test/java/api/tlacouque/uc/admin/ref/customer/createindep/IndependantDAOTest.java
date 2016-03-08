package api.tlacouque.uc.admin.ref.customer.createindep;

import com.mongodb.MongoClient;
import miage.pds.MongoDatastoreConfig;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createindep.dao.IndependantDAO;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createindep.entities.Independant;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by tlacouque on 17/01/2016.
 */

 public class IndependantDAOTest {
    MongoClient mongoClient;
    Morphia morphia;
    Datastore datastore;
    IndependantDAO independantDAO;
    String dbName = "crm";


    @Before
    public void setUp() throws Exception {

        independantDAO = new IndependantDAO(MongoDatastoreConfig.getDataStore());
    }

    @Test
    public void testfindAllWithoutUserId() throws Exception {
        List<Independant> independants = independantDAO.findAllWithUserId("1");
        assertNotNull(independants);
    }
}
