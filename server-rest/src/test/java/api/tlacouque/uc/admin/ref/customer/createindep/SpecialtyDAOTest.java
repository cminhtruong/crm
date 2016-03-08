package api.tlacouque.uc.admin.ref.customer.createindep;

import com.mongodb.MongoClient;
import miage.pds.MongoDatastoreConfig;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createindep.dao.SpecialtyDAO;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createindep.entities.Specialty;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

/**
 * Created by tlacouque on 17/01/2016.
 */
 public class SpecialtyDAOTest {
    MongoClient mongoClient;
    Morphia morphia;
    Datastore datastore;
    SpecialtyDAO specialtyDAO;
    String dbName = "crm";


    @Before
    public void setUp() throws Exception {

        specialtyDAO = new SpecialtyDAO(MongoDatastoreConfig.getDataStore());

    }

    @Test
    public void testFindAll() throws Exception {
        List<Specialty> specialties = specialtyDAO.findAll();
        assertEquals(13,specialties.size());
        assertThat(14,not(specialties.size()));
    }

}
