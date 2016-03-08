package miage.pds.api.ctruong.uc.prospect.suggest.dao;

import miage.pds.api.ctruong.uc.prospect.suggest.model.RelationUserClient;
import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.DAO;

import java.util.List;

/**
 * Created by Truong on 2/6/2016.
 */
public interface RelationUserClientDAO extends DAO<RelationUserClient, ObjectId> {

    /**
     * check the relationship between user and client
     * @param prospectID
     * @return
     */
    public boolean checkRelationBetweenUserAndClient(ObjectId prospectID);

    /**
     * get list relation
     * @return
     */
    public List<RelationUserClient> getAllRelation();
}
