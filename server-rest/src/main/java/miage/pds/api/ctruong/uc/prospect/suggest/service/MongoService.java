package miage.pds.api.ctruong.uc.prospect.suggest.service;


import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.logging.Logger;
import org.mongodb.morphia.logging.MorphiaLoggerFactory;

import java.net.UnknownHostException;

/**
 * The class to create the service to communicate with mongodb
 *
 * Created by Truong on 12/20/2015.
 * @version 1.1.19
 * @serial 111912202015
 */
public class MongoService {

    private Morphia morphia;
    private Datastore datastore;
    private static Logger logger = MorphiaLoggerFactory.get(MongoService.class);
    private static final String DATABASE_NAME = "crm";

    public MongoService() {
        try {
            MongoClient mongoClient = new MongoClient(MorphiaConfig.VM_PROD_IP, MorphiaConfig.VM_PROD_PORT);

            logger.info("The new instance of mongo service running with Morphia");
            // Create new instance
            this.morphia    =   new Morphia();
            this.datastore  =   morphia.createDatastore(mongoClient, DATABASE_NAME);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


    public Morphia getMorphia() {
        return morphia;
    }

    public void setMorphia(Morphia morphia) {
        this.morphia = morphia;
    }

    public Datastore getDatastore() {
        return datastore;
    }

    public void setDatastore(Datastore datastore) {
        this.datastore = datastore;
    }
}
