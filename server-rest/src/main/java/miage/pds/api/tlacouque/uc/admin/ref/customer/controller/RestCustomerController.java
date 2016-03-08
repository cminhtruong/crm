package miage.pds.api.tlacouque.uc.admin.ref.customer.controller;

import com.mongodb.MongoClient;


import miage.pds.MongoConfig;

import miage.pds.MongoDatastoreConfig;
import miage.pds.api.tlacouque.uc.admin.ref.customer.common.GeoCoding;
import miage.pds.api.tlacouque.uc.admin.ref.customer.common.TileDownloader;
import miage.pds.api.tlacouque.uc.admin.ref.customer.common.XYZCalcul;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createhc.dao.HealthCenterDAO;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createhc.dao.HoldingDAO;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createhc.dao.PurchasingCentralDAO;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createhc.entities.HealthCenter;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createhc.entities.Holding;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createindep.dao.CompanyDAO;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createindep.dao.IndependantDAO;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createindep.dao.SpecialtyDAO;

import miage.pds.api.tlacouque.uc.admin.ref.customer.entities.MapInfo;
import miage.pds.api.tlacouque.uc.admin.ref.customer.message.MessageRestCustomer;
import miage.pds.api.tlacouque.uc.admin.ref.customer.message.ResponseRestCustomer;

import miage.pds.api.tlacouque.uc.admin.ref.customer.SpringMongoConfig;

import org.mockito.Mockito;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.util.List;

import static org.mockito.Mockito.when;


/**
 * Created by tlacouque on 16/12/2015.
 * Rest controller used to handle message from commercials tablet.
 */

@Controller
public class RestCustomerController {

    private static final Logger logger = LoggerFactory.getLogger(RestCustomerController.class);

    public RestCustomerController() {
    }

    /**
     * Take a messageRestCustomer (dto) in parameter with an healthcenter in it.
     * Save the healthcenter and return true with a ResponseRestCustomer (dto)
     * @param messageRestCustomer
     * @return ResponseRestCustomer
     */
    @RequestMapping(value = "/customer/hc/create/", method = RequestMethod.POST)
    public @ResponseBody
    ResponseRestCustomer createHealthCenter(@RequestBody MessageRestCustomer messageRestCustomer) {
        logger.info("Create HealthCenter is called");
        boolean customerInserted = true;
        HealthCenter healthCenter = messageRestCustomer.getHealthCenter();
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        try {
            new HealthCenterDAO(MongoDatastoreConfig.getDataStore()).save(healthCenter);
        } catch (Exception e) {
            customerInserted = false;
        }

        try {
            GeoCoding.getLocation(healthCenter);
            responseRestCustomer.setLat(healthCenter.getLattitude());
            responseRestCustomer.setLng(healthCenter.getLongitude());
        }catch (Exception e) {
            responseRestCustomer.setLat(0);
            responseRestCustomer.setLng(0);
        }
        MapInfo mapInfo = XYZCalcul.getMapInfo(healthCenter);
        TileDownloader.dwdTile(mapInfo);
        responseRestCustomer.setMapInfo(mapInfo);
        responseRestCustomer.setIsInserted(customerInserted);
        logger.info("Is the healthcenter inserted : "+customerInserted);
        return responseRestCustomer;
    }


    /**
     * Take a messageRestCustomer (dto) in parameter with an independant in it.
     * Save the independant and return true with a ResponseRestCustomer (dto)
     * @param messageRestCustomer
     * @return ResponseRestCustomer
     */
    @RequestMapping(value = "/customer/indep/create/", method = RequestMethod.POST)
    public @ResponseBody
    ResponseRestCustomer createIndependant(@RequestBody MessageRestCustomer messageRestCustomer) {
        logger.info("Create independant is called");
        boolean customerInserted = true;
        try {
            new IndependantDAO(MongoDatastoreConfig.getDataStore()).save(messageRestCustomer.getIndependant());
        } catch (Exception e) {
        customerInserted = false;
    }
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        responseRestCustomer.setIsInserted(customerInserted);
        logger.info("Is the independant inserted : "+customerInserted);
        return responseRestCustomer;
    }


    /**
     * Used to initialise holding in crm tab. It return a list of holding with a ResponseRestCustomer (dto)
     * @return ResponseRestCustomer
     */
    @RequestMapping(value = "/customer/holding", method = RequestMethod.GET)
    public @ResponseBody ResponseRestCustomer getHoldings() {
        logger.info("Get holdings is called");
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        try {
            final List<Holding> holdings = new HoldingDAO(MongoDatastoreConfig.getDataStore()).findAll();
            responseRestCustomer.setHoldings(holdings);
            logger.info("Number of holding returned : "+responseRestCustomer.getHoldings().size());
        } catch (Exception e) {
            responseRestCustomer.setHoldings(null);
        }

        return responseRestCustomer;
    }

    /**
     * Used to initialise purchasingcentral in crm tab.
     * It return a list of purchasingcentral with a ResponseRestCustomer (dto)
     * @return ResponseRestCustomer
     */
    @RequestMapping(value = "/customer/purchasingcentral", method = RequestMethod.GET)
    public @ResponseBody ResponseRestCustomer getPurchasingCentrals() {
        logger.info("Get purchasing central is called");
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        try {
            responseRestCustomer.setPurchasingCentrals(new PurchasingCentralDAO(MongoDatastoreConfig.getDataStore()).findAll());
            logger.info("Number of purchasing central returned : "+responseRestCustomer.getPurchasingCentrals().size());
        } catch (Exception e) {
            responseRestCustomer.setPurchasingCentrals(null);
        }

        return responseRestCustomer;
    }

    /**
     * Used to initialise company in crm tab. It return a list of company with a ResponseRestCustomer (dto)
     * @return ResponseRestCustomer
     */
    @RequestMapping(value = "/customer/company", method = RequestMethod.GET)
    public @ResponseBody ResponseRestCustomer getCompanies() {
        logger.info("Get company is called");
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        try {
            responseRestCustomer.setCompanies(new CompanyDAO(MongoDatastoreConfig.getDataStore()).findAll());
            logger.info("Number of company returned : "+responseRestCustomer.getCompanies().size());
        } catch (Exception e) {
            responseRestCustomer.setCompanies(null);
        }

        return responseRestCustomer;
    }

    /**
     * Used to initialise specialty in crm tab. It return a list of specialty with a ResponseRestCustomer (dto)
     * @return ResponseRestCustomer
     */
    @RequestMapping(value = "/customer/specialty", method = RequestMethod.GET)
    public @ResponseBody ResponseRestCustomer getSpecialties() {
        logger.info("Get specialty is called");
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        try {
            responseRestCustomer.setSpecialties(new SpecialtyDAO(MongoDatastoreConfig.getDataStore()).findAll());
            logger.info("Number of specialty returned : "+responseRestCustomer.getSpecialties().size());
        } catch (Exception e) {
            responseRestCustomer.setSpecialties(null);
        }

        return responseRestCustomer;
    }

    /**
     * Interface used to request all healthcenter created by someone who has not the user id pass in parameter.
     * HealthCenter are encapsulated in ResponseRestCustomer (dto)
     * @param iduser
     * @return ResponseRestCustomer
     */
    @RequestMapping(value = "/customer/healthcenter/{iduser}", method = RequestMethod.GET)
    public @ResponseBody ResponseRestCustomer getHealthCenters(@PathVariable String iduser) {
        logger.info("Get health center is called");
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        try {
            responseRestCustomer.setHealthCenters(new HealthCenterDAO(MongoDatastoreConfig.getDataStore()).findAllWithUserId(iduser));
            logger.info("Number of health center returned : "+responseRestCustomer.getHealthCenters().size());
        } catch (Exception e) {
            responseRestCustomer.setHealthCenters(null);
        }

        return responseRestCustomer;
    }

    /**
     * Interface used to request all independant created by someone who has not the user id pass in parameter.
     * Independant are encapsulated in ResponseRestCustomer (dto)
     * @param iduser
     * @return
     */
    @RequestMapping(value = "/customer/independant/{iduser}", method = RequestMethod.GET)
    public @ResponseBody ResponseRestCustomer getIndependants(@PathVariable String iduser) {
        logger.info("Get independant is called");
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        try {
            responseRestCustomer.setIndependants(new IndependantDAO(MongoDatastoreConfig.getDataStore()).findAllWithUserId(iduser));
            logger.info("Number of independant returned : "+responseRestCustomer.getIndependants().size());
        } catch (Exception e) {
            responseRestCustomer.setIndependants(null);
        }

         return responseRestCustomer;
    }

    /**
     * Interface used to request all healthcenter created by someone who has not the user id pass in parameter.
     * HealthCenter are encapsulated in ResponseRestCustomer (dto)
     * @param iduser
     * @return
     */
    @RequestMapping(value = "/customer/{iduser}", method = RequestMethod.GET)
    public @ResponseBody ResponseRestCustomer getCustomers(@PathVariable String iduser) {
        logger.info("Get customers is called");
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        try {
            responseRestCustomer.setIndependants(new IndependantDAO(MongoDatastoreConfig.getDataStore()).findAllWithUserId(iduser));
            responseRestCustomer.setHealthCenters(new HealthCenterDAO(MongoDatastoreConfig.getDataStore()).findAllWithUserId(iduser));
        } catch (Exception e) {
            responseRestCustomer.setHealthCenters(null);
            responseRestCustomer.setIndependants(null);
        }

       return responseRestCustomer;
    }

    @RequestMapping(value = "/customer/test", method = RequestMethod.GET)
    public void test() {
        logger.info("test is called");
        MapInfo mapInfo = new MapInfo(1,15,16597,11270);


        TileDownloader.dwdTile(mapInfo);


    }


}
