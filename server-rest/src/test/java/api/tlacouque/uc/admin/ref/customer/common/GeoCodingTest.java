package api.tlacouque.uc.admin.ref.customer.common;

import miage.pds.api.tlacouque.uc.admin.ref.customer.common.GeoCoding;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createhc.entities.HealthCenter;

import org.junit.Test;


import static org.junit.Assert.assertEquals;


/**
 * Created by tlacouque on 02/02/2016.
 */
 public class GeoCodingTest {

    @Test
    public void testGetLocation() throws Exception {
        HealthCenter healthCenter = new HealthCenter();
        healthCenter.setStreetNumber(35);
        healthCenter.setStreetName("rue bergere");
        healthCenter.setTown("paris");
        GeoCoding.getLocation(healthCenter);
        assertEquals(healthCenter.getLattitude(),48.8724472,0.0001);
        assertEquals(healthCenter.getLongitude(),2.3435509,0.0001);
    }
}
