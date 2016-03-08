package miage.pds.api.common.LoggingDao;

import java.util.UUID;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import miage.pds.api.common.model.User;

public class LoggingDAO extends BasicDAO<User, ObjectId> {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggingDAO.class);

	public LoggingDAO(Datastore datastore) {
		super(datastore);
		// TODO Auto-generated constructor stub
	}

	public User logUser(User user) {
		User u = new User();
		u =   getDatastore().createQuery(User.class)
				.field("email").equal(user.getEmail())
				.field("password").equal(user.getPassword()).get();
			//logger.info("result" + u.getEmail());
			
		if(u!=null)
		return u;
		else return null;
	}

	public boolean addUser(User u) {
		boolean state = false;
		// check if connected to DB
			getDatastore().save(u);
		logger.info("ADDED USER : " + u.getLname());
		return true;		
	}
	
	public String getUniqueUid(){
	
		User u = new User();
		boolean unique = false;
		String uid = "" ;
		do{
			uid = UUID.randomUUID().toString();
			u = getDatastore().createQuery(User.class).field("id").equal(uid).get();			
			if(u==null) unique = true;
		}while(unique=false);
		return uid;	
	}
	
	

	
	
	
	public void dropTableUser(){

		getDatastore().getCollection(User.class).drop();
		logger.info("DROPPED TABLE User");
	}
}
