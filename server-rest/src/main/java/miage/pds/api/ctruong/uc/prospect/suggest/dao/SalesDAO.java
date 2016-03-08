package miage.pds.api.ctruong.uc.prospect.suggest.dao;

import miage.pds.api.ctruong.uc.prospect.suggest.model.Sales;
import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.DAO;

import java.util.Date;
import java.util.List;

/**
 * Created by Truong on 2/6/2016.
 */
public interface SalesDAO extends DAO<Sales, ObjectId>{

    /**
     * get list sales during 6 month for each prospect
     * @param prospectId
     * @param date
     * @return
     */
    public List<Sales> getSalesSixMonthEachProspect(ObjectId prospectId, Date date);
}
