package miage.pds.api.ctruong.uc.prospect.suggest.dao;

import miage.pds.api.ctruong.uc.prospect.suggest.model.Command;
import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.DAO;

import java.util.List;

/**
 * Created by Truong on 2/6/2016.
 */
public interface CommandDAO extends DAO<Command, ObjectId>{

    public List<Command> getAllCommand();

    public boolean checkExistCommandOfProspect(ObjectId prospectId);
}
