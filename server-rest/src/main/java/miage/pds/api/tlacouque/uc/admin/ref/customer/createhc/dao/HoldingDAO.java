package miage.pds.api.tlacouque.uc.admin.ref.customer.createhc.dao;

import miage.pds.api.tlacouque.uc.admin.ref.customer.createhc.entities.Holding;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import java.util.List;

/**
 * Created by tlacouque on 09/01/2016.
 * Class used to do operation between mongodb table "holding" and the rest server
 */
public class HoldingDAO extends BasicDAO<Holding,ObjectId> {

    public HoldingDAO(Datastore ds) {
        super(ds);
    }

    /**
     * Return a list of all holding from the db
     * @return List<Holding>
     */
    public List<Holding> findAll() {
        return find().asList();
    }

}
