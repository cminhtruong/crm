package miage.pds.api.ctruong.uc.prospect.suggest.dao;

import miage.pds.api.ctruong.uc.prospect.suggest.model.RelationClient;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import java.util.List;

/**
 * Created by Truong on 2/6/2016.
 */
public class RelationClientDAOImpl extends BasicDAO<RelationClient, ObjectId> implements RelationClientDAO{

    private static final String PROSPECT_ID = "prospectId";

    public RelationClientDAOImpl(Class<RelationClient> entityClass, Datastore ds) {
        super(entityClass, ds);
    }

    @Override
    public List<RelationClient> getAllRelation() {
        Query<RelationClient> query = createQuery();
        return query.asList();
    }

    @Override
    public boolean checkRelationWithClient(ObjectId prospectId) {
        Query<RelationClient> query = createQuery().field(PROSPECT_ID).equal(prospectId);
        if (query.get() != null){
            return true;
        } else {
            return false;
        }
    }


}
