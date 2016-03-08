package miage.pds.api.ctruong.uc.prospect.suggest.dao;

import miage.pds.api.ctruong.uc.prospect.suggest.model.RelationUserClient;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import java.util.List;

/**
 * Created by Truong on 2/6/2016.
 */
public class RelationUserClientDAOImpl extends BasicDAO<RelationUserClient, ObjectId> implements RelationUserClientDAO{

    private static final String CLIENT_ID = "clientId";

    public RelationUserClientDAOImpl(Class<RelationUserClient> entityClass, Datastore ds) {
        super(entityClass, ds);
    }

    @Override
    public boolean checkRelationBetweenUserAndClient(ObjectId prospectID) {
        Query<RelationUserClient> query = createQuery().field(CLIENT_ID).equal(prospectID);
        List<RelationUserClient> result = query.asList();
        if (result.size() == 0){
            return false;
        } else {
            return true;
        }

    }

    @Override
    public List<RelationUserClient> getAllRelation() {
        Query<RelationUserClient> query = createQuery();
        return query.asList();
    }
}
