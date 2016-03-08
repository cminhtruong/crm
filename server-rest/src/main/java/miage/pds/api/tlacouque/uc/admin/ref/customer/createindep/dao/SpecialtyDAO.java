package miage.pds.api.tlacouque.uc.admin.ref.customer.createindep.dao;

import miage.pds.api.tlacouque.uc.admin.ref.customer.createindep.entities.Specialty;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import java.util.List;

/**
 * Created by tlacouque on 09/01/2016.
 * Class used to do operation between mongodb table "specialty" and the rest server
 */
public class SpecialtyDAO extends BasicDAO<Specialty,ObjectId> {
    public SpecialtyDAO(Datastore ds) {
        super(ds);
    }

    /**
     * Return a list of all specialty from the db
     * @return List<Specialty>
     */
    public List<Specialty> findAll() {
        return find().asList();
    }

}
