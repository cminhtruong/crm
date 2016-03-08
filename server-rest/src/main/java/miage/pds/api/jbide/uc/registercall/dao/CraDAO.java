package miage.pds.api.jbide.uc.registercall.dao;

import java.util.List;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import miage.pds.api.common.LoggingRestController;
import miage.pds.api.common.model.User;
import miage.pds.api.jbide.uc.registercall.model.Cra;

/**
 * Created by jbide on 20/12/2015.
 */

public class CraDAO extends BasicDAO<Cra, ObjectId> {

	private static final Logger logger = LoggerFactory.getLogger(CraDAO.class);

	public CraDAO(Datastore datastore) {
		super(datastore);					
	}

	// cra insertion
	public boolean createCra(Cra cra) {
		boolean state = false;
		// check if connected to DB
		cra.setIdcra(getUniqueIdCra());
		getDatastore().save(cra);
		return true;
	}
	
	public boolean deleteCra(String idcra) {
		getDatastore().delete(getDatastore().createQuery(Cra.class).field("idcra").equal(idcra));
		return true;
	}

	public List<Cra> getListCraForUser(String iduser) {
		return  getDatastore().createQuery(Cra.class).disableValidation().field("iduser").equal(iduser).asList();
	}


	public String getUniqueIdCra(){		
		Cra u = new Cra();
		boolean unique = false;
		String id = "" ;
		do{
			id = UUID.randomUUID().toString().substring(0, 10);
			u = getDatastore().createQuery(Cra.class).field("idcra").equal(id).get();			
			if(u==null) unique = true;
		}while(unique=false);
		return id;	
	}
	
	public void dropTableCraAndAddMock(){

		getDatastore().getCollection(Cra.class).drop();
		logger.info("DROPPED TABLE Cra");
    	//Mock Cra
        Cra newCra = new Cra();
        newCra.setCalltype("Recu");
        newCra.setClientname("CH HENRI MONDOR");
        newCra.setComments("Les rappeler lorsque nouveaux produits disponibles");
        newCra.setDate("23 janv 2016");
        newCra.setDuration((long) 1234);
        newCra.setContactname("Cong-Minh Truong");
        newCra.setIdcontact("0610772364");
        newCra.setSubject("Demande d'informations sur scanners");
        newCra.setIduser(LoggingRestController.idusertest);
        String s = getUniqueIdCra();
        newCra.setIdcra(s);
        
        createCra(newCra);
        s = getUniqueIdCra();
        
        newCra = new Cra();
        newCra.setCalltype("Emis");
        newCra.setClientname("CS Daniel Renoult et Montreuil");
        newCra.setComments("Nouveaux types de compresses");
        newCra.setDate("23 janv 2016");
        newCra.setDuration((long) 2010);
        newCra.setContactname("Titouan Lacouque");
        newCra.setIdcontact("0684894378");
        newCra.setSubject("Demande d'informations sur compresses");
        newCra.setIduser(LoggingRestController.idusertest);
        newCra.setIdcra(s);
        createCra(newCra);
        logger.info("ADDED CRA MOCK");
	}


}
