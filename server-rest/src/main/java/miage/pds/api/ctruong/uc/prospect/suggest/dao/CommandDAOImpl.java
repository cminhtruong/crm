package miage.pds.api.ctruong.uc.prospect.suggest.dao;

import miage.pds.api.ctruong.uc.prospect.suggest.model.Command;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import java.util.List;

/**
 * Created by Truong on 2/6/2016.
 */
public class CommandDAOImpl extends BasicDAO<Command, ObjectId> implements CommandDAO{

    private static final String PROSPECT_ID = "prospectId";
    public CommandDAOImpl(Class<Command> entityClass, Datastore ds) {
        super(entityClass, ds);
    }

    @Override
    public List<Command> getAllCommand() {
        Query<Command> query = createQuery();
        return query.asList();
    }

    @Override
    public boolean checkExistCommandOfProspect(ObjectId prospectId) {
        Query<Command> query = createQuery().field(PROSPECT_ID).equal(prospectId);
        if (query.asList().size() > 0){
            return true;
        } else {
            return false;
        }

    }


}
