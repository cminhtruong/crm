package api.tlacouque.uc.admin.ref.customer.createhc;

import com.mongodb.MongoClient;
import miage.pds.MongoDatastoreConfig;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createhc.dao.HoldingDAO;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createhc.entities.Holding;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by tlacouque on 17/01/2016.
 */
 public class HoldingDAOTest {
    MongoClient mongoClient;
    Morphia morphia;
    Datastore datastore;
    HoldingDAO holdingDAO;
    String dbName = "crm";


    @Before
    public void setUp() throws Exception {
        this.mongoClient    = new MongoClient();

        holdingDAO = new HoldingDAO(MongoDatastoreConfig.getDataStore());

    }

    @Test
    public void testFindAll() throws Exception {
        List<Holding> holdingList = holdingDAO.findAll();
        assertEquals(9,holdingList.size());
    }
}
