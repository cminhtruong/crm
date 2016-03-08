package miage.pds.api.ctruong.uc.prospect.suggest.dao;

import miage.pds.api.ctruong.uc.prospect.suggest.model.Sales;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by Truong on 2/6/2016.
 */
public class SalesDAOImpl extends BasicDAO<Sales, ObjectId> implements SalesDAO{

    private static final String PROSPECT_ID = "prospectId";

    public SalesDAOImpl(Class<Sales> entityClass, Datastore ds) {
        super(entityClass, ds);
    }


    @Override
    public List<Sales> getSalesSixMonthEachProspect(ObjectId prospectId, Date date) {
        Query<Sales> query = createQuery().field(PROSPECT_ID).equal(prospectId).filter("date >=", date);
        return query.asList();
    }
}
