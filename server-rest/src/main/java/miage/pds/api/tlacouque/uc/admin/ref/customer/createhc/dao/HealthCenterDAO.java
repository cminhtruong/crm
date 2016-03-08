package miage.pds.api.tlacouque.uc.admin.ref.customer.createhc.dao;

import miage.pds.api.tlacouque.uc.admin.ref.customer.createhc.entities.HealthCenter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import java.util.List;

/**
 * Created by tlacouque on 09/01/2016.
 */
public class HealthCenterDAO extends BasicDAO<HealthCenter,ObjectId> {

    public HealthCenterDAO(Datastore ds) {
        super(ds);
    }

    /**
     * Return a list of health center from the db without all lines
     * where the idUser is equals to the id pass in parameter
     * @return List<HealthCenter>
     */
    public List<HealthCenter> findAllWithUserId(String id) {
       return getDatastore().createQuery(HealthCenter.class).filter("idUser =",id).asList();
    }





}
