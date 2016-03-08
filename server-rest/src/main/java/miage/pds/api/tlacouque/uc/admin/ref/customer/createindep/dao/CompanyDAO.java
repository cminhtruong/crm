package miage.pds.api.tlacouque.uc.admin.ref.customer.createindep.dao;

import miage.pds.api.tlacouque.uc.admin.ref.customer.createindep.entities.Company;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import java.util.List;

/**
 * Created by tlacouque on 09/01/2016.
 * Class used to do operation between mongodb table "company" and the rest server
 */
public class CompanyDAO extends BasicDAO<Company,ObjectId> {

    public CompanyDAO(Datastore ds) {
        super(ds);
    }

    /**
     * Return a list of all company from the db
     * @return List<Company>
     */
    public List<Company> findAll() {
        return find().asList();
    }
}
