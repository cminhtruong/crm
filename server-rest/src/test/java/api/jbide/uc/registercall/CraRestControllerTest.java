package api.jbide.uc.registercall;

import static org.junit.Assert.*;

import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

import miage.pds.MongoDatastoreConfig;
import miage.pds.api.jbide.uc.registercall.dao.CraDAO;
import miage.pds.api.jbide.uc.registercall.model.Cra;

/**
 * Created by jbide on 22/01/2016.
 */
 public class CraRestControllerTest {

    MongoClient mongoClient;
    Morphia morphia;
    String dbName = "crm";

    private Datastore datastore;
    private CraDAO dao;

    @Before
    public void setUp() throws UnknownHostException {
        this.datastore = MongoDatastoreConfig.getDataStore();
        dao = new CraDAO(datastore);        
    }


    @Test
    public void testCreateCra() throws Exception {
        Cra cra = new Cra();
        cra.setIduser("id1");
        int size0 = dao.getListCraForUser("id1").size();
        dao.createCra(cra);
        int size1 = dao.getListCraForUser("id1").size();
        assertEquals(size0 + 1, size1);
        dao.delete(cra);
    }

    @Test
    public void getListCraForUserTest() {
        Cra cra = new Cra();
        cra.setIduser("id1");
        cra.setClientname("testclient");
        int size0 = dao.getListCraForUser("id1").size();
        dao.createCra(cra);
        assertEquals(dao.getListCraForUser("id1").size(), datastore.createQuery(Cra.class).field("iduser").equal("id1").asList().size());
        dao.delete(cra);
    }
} 
