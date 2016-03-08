package miage.pds.api.ctruong.uc.prospect.suggest.dao;

import miage.pds.api.ctruong.uc.prospect.suggest.model.Prospect;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import java.util.List;

/**
 * Created by Truong on 2/6/2016.
 */
public class ProspectDAOImp extends BasicDAO<Prospect, ObjectId> implements ProspectDAO{

    private static final String ID= "_id";

    public ProspectDAOImp(Class<Prospect> entityClass, Datastore ds) {
        super(entityClass, ds);
    }


    @Override
    public Prospect getProspetById(ObjectId id) {
        Query<Prospect> query = createQuery().field(ID).equal(id);
        return query.get();
    }

    @Override
    public List<Prospect> getListProspect() {
        Query<Prospect> query = createQuery();
        return query.asList();
    }
}
