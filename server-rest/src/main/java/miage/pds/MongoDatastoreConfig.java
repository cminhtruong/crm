package miage.pds;

import com.mongodb.MongoClient;
import miage.pds.api.tlacouque.uc.admin.ref.customer.SpringMongoConfig;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.net.UnknownHostException;

/**
 * Created by tlacouque on 22/01/2016.
 */
public class MongoDatastoreConfig {

    static Datastore datastore;
    static final Morphia morphia = new Morphia();

    public static Datastore getDataStore() {
        if(datastore == null ) {
            try {
                datastore = morphia.createDatastore(new MongoClient(MongoConfig.VM_PROD_IP,MongoConfig.VM_PROD_PORT),
                        SpringMongoConfig.DB_NAME);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        return datastore;
    }


}
