package miage.pds.api.ctruong.uc.prospect.suggest.dao;

import miage.pds.api.ctruong.uc.prospect.suggest.model.RelationClient;
import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.DAO;

import java.util.List;

/**
 * Created by Truong on 2/6/2016.
 */
public interface RelationClientDAO extends DAO<RelationClient, ObjectId>{

    public List<RelationClient> getAllRelation();

    public boolean checkRelationWithClient(ObjectId prospectId);
}
