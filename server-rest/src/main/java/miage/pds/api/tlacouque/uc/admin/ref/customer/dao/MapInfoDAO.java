package miage.pds.api.tlacouque.uc.admin.ref.customer.dao;

import miage.pds.api.tlacouque.uc.admin.ref.customer.createindep.entities.Company;
import miage.pds.api.tlacouque.uc.admin.ref.customer.entities.MapInfo;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

/**
 * Created by tlacouque on 01/02/2016.
 */
public class MapInfoDAO extends BasicDAO<MapInfo,ObjectId> {

    public MapInfoDAO(Datastore ds) {
        super(ds);
    }

}
