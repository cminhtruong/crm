package api.tlacouque.uc.admin.ref.customer.createhc;

import com.mongodb.MongoClient;
import miage.pds.MongoDatastoreConfig;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createhc.dao.PurchasingCentralDAO;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createhc.entities.PurchasingCentral;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by tlacouque on 17/01/2016.
 */
 public class PurchasingCenterDAOTest {

    MongoClient mongoClient;
    Morphia morphia;
    Datastore datastore;
    PurchasingCentralDAO purchasingCentralDAO;
    String dbName = "crm";


    @Before
    public void setUp() throws Exception {

        purchasingCentralDAO = new PurchasingCentralDAO(MongoDatastoreConfig.getDataStore());

    }

    @Test
    public void testFindAll() throws Exception {
        List<PurchasingCentral> purchasingCentrals = purchasingCentralDAO.findAll();
        assertEquals(7,purchasingCentrals.size());
    }


}
