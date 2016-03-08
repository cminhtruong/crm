package api.tlacouque.uc.admin.ref.customer.common;

import miage.pds.MongoDatastoreConfig;
import miage.pds.api.tlacouque.uc.admin.ref.customer.common.XYZCalcul;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createindep.entities.Independant;
import miage.pds.api.tlacouque.uc.admin.ref.customer.dao.MapInfoDAO;
import miage.pds.api.tlacouque.uc.admin.ref.customer.entities.MapInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by tlacouque on 02/02/2016.
 */
public class XYZCalculTest {

    MapInfo mapInfo;
    MapInfoDAO mapInfoDAO;

    @Before
    public void setUp() throws Exception {
         mapInfoDAO = new MapInfoDAO(MongoDatastoreConfig.getDataStore());
    }

    @Test
    public void testGetMapInfo() throws Exception {
    Independant independant = new Independant();
        independant.setLattitude(48.8724472);
        independant.setLongitude(2.3435509);
        independant.setSiretNumber(1L);

        mapInfo = XYZCalcul.getMapInfo(independant);
        assertEquals(mapInfo.getX(),15);
        assertEquals(mapInfo.getY(),16597);
        assertEquals(mapInfo.getZ(),11270);
}

@After
public void tearDown()throws Exception{
         mapInfoDAO.delete(mapInfo);

        }
}
