package miage.pds.api.ctruong.uc.prospect.suggest.mock;

import miage.pds.api.ctruong.uc.prospect.suggest.service.MongoService;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.logging.Logger;
import org.mongodb.morphia.logging.MorphiaLoggerFactory;

/**
 * Created by Truong on 1/23/2016.
 */
public class MockTable {
    private static final Logger log = MorphiaLoggerFactory.get(MockTable.class);
    private MongoService mongoService;
    private Datastore datastore;



    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

}
