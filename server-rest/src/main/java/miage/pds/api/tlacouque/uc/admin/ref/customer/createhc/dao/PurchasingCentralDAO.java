package miage.pds.api.tlacouque.uc.admin.ref.customer.createhc.dao;

import miage.pds.api.tlacouque.uc.admin.ref.customer.createhc.entities.PurchasingCentral;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import java.util.List;

/**
 * Created by tlacouque on 09/01/2016.
 */
public class PurchasingCentralDAO extends BasicDAO<PurchasingCentral,ObjectId> {

    public PurchasingCentralDAO(Datastore ds) {
        super(ds);
    }

    /**
     * Return a list of all purchasing central from the db
     * @return List<PurchasingCentral>
     */
    public List<PurchasingCentral> findAll() {
        return find().asList();
    }
}
