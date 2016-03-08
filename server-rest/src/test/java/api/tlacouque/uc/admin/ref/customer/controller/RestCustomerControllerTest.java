package api.tlacouque.uc.admin.ref.customer.controller;

import com.mongodb.MongoClient;
import miage.pds.MongoDatastoreConfig;
import miage.pds.api.tlacouque.uc.admin.ref.customer.controller.RestCustomerController;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createhc.dao.HealthCenterDAO;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createhc.entities.HealthCenter;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createindep.dao.IndependantDAO;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createindep.entities.Independant;
import miage.pds.api.tlacouque.uc.admin.ref.customer.message.MessageRestCustomer;
import miage.pds.api.tlacouque.uc.admin.ref.customer.message.ResponseRestCustomer;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.net.UnknownHostException;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

/**
 * Created by tlacouque on 21/01/2016.
 *
 * @Autowired private HandlerAdapter handlerAdapter;
 * @Autowired private HandlerMapping handlerMapping;
 */
public class RestCustomerControllerTest {

private MockHttpServletRequest request;
private MockHttpServletResponse response;
RestCustomerController restCustomerController;
/**
 * @Autowired private HandlerAdapter handlerAdapter;
 * @Autowired private HandlerMapping handlerMapping;
 */
MongoClient mongoClient;
        Morphia morphia;
        Datastore datastore;
        String dbName="crm";

@Before
public void setUp()throws UnknownHostException {

        this.datastore= MongoDatastoreConfig.getDataStore();


        restCustomerController=new RestCustomerController();

        }

/**
@Test
public void testCreateHC()throws Exception{
        HealthCenter healthCenter=new HealthCenter();
        healthCenter.setSiretNumber(1L);
        healthCenter.setName("TestHc");

        MessageRestCustomer messageRestCustomer=new MessageRestCustomer(1,healthCenter);
        List<HealthCenter> list=new HealthCenterDAO(datastore).find().asList();
        int nbHCbeforeTest=new HealthCenterDAO(datastore).find().asList().size();
        nbHCbeforeTest=nbHCbeforeTest+1;
        ResponseRestCustomer responseRestCustomer=restCustomerController.createHealthCenter(messageRestCustomer);
        int nbHCAfterTest=new HealthCenterDAO(datastore).find().asList().size();
        new HealthCenterDAO(datastore).delete(healthCenter);
        assertEquals(nbHCbeforeTest,nbHCAfterTest);
        assertTrue(responseRestCustomer.getIsInserted());
        }

@Test public void testCreateIndep()throws Exception{
        Independant independant = new Independant();
        independant.setSiretNumber(1L);
        independant.setName("TestIndep");
        MessageRestCustomer messageRestCustomer = new MessageRestCustomer();
        messageRestCustomer.setIndependant(independant);
        messageRestCustomer.setIdUser(1);
        int nbIndepbeforeTest = new IndependantDAO(datastore).find().asList().size();
        nbIndepbeforeTest = nbIndepbeforeTest + 1;
        restCustomerController.createIndependant(messageRestCustomer);
        int nbIndepAfterTest = new IndependantDAO(datastore).find().asList().size();
        new IndependantDAO(datastore).delete(independant);
        assertEquals(nbIndepbeforeTest, nbIndepAfterTest);
        }
*/
@Test public void testGetHolding()throws Exception{
        ResponseRestCustomer responseRestCustomer=restCustomerController.getHoldings();
        assertNotNull(responseRestCustomer.getHoldings());
        }

@Test public void testGetPurchasingCentrals()throws Exception{
        ResponseRestCustomer responseRestCustomer=restCustomerController.getPurchasingCentrals();
        assertNotNull(responseRestCustomer.getPurchasingCentrals());
        }

@Test public void testGetCompanies()throws Exception{
        ResponseRestCustomer responseRestCustomer=restCustomerController.getCompanies();
        assertNotNull(responseRestCustomer.getCompanies());
        }

@Test public void testGetSpecialty()throws Exception{
        ResponseRestCustomer responseRestCustomer=restCustomerController.getSpecialties();
        assertNotNull(responseRestCustomer.getSpecialties());
        }

@Test public void testGetHealthCenters()throws Exception{
        int healthCentersNumber=new HealthCenterDAO(datastore).findAllWithUserId("1").size();
        ResponseRestCustomer responseRestCustomer=restCustomerController.getHealthCenters("1");
        assertEquals(healthCentersNumber,responseRestCustomer.getHealthCenters().size());
        }

@Test public void testGetIndependants()throws Exception{
        int independantNumber=new IndependantDAO(datastore).findAllWithUserId("1").size();
        ResponseRestCustomer responseRestCustomer=restCustomerController.getIndependants("1");
        assertEquals(independantNumber,responseRestCustomer.getIndependants().size());
        }

@Test public void testGetCustomers()throws Exception{
        int independantNumber=new IndependantDAO(datastore).findAllWithUserId("1").size();
        int healthCentersNumber=new HealthCenterDAO(datastore).findAllWithUserId("1").size();
        ResponseRestCustomer responseRestCustomer=restCustomerController.getCustomers("1");
        assertEquals(healthCentersNumber,responseRestCustomer.getHealthCenters().size());
        assertEquals(independantNumber,responseRestCustomer.getIndependants().size());
        }
        }
