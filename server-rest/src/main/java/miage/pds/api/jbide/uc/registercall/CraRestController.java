package miage.pds.api.jbide.uc.registercall;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import miage.pds.MongoDatastoreConfig;
import miage.pds.api.common.LoggingRestController;
import miage.pds.api.jbide.uc.registercall.dao.CraDAO;
import miage.pds.api.jbide.uc.registercall.model.Cra;


/**
 * Handles requests for the application home page.
 */
@Controller
public class CraRestController {

	private static final Logger logger = LoggerFactory.getLogger(CraRestController.class);
	
	CraDAO dao = new CraDAO(MongoDatastoreConfig.getDataStore());

	public CraRestController() {
		dao.dropTableCraAndAddMock();

	}
	
	/**
	 * Simply returns a status string.
	 */

	@RequestMapping(value = "/cra/listcra",method = RequestMethod.GET)
	public @ResponseBody List<Cra> getListCraForUser(@RequestParam("iduser") String iduser){

		List<Cra> liste = dao.getListCraForUser(iduser);
		logger.info("Liste Cra for user " + iduser +"response list size :" + liste.size());
		return liste;
	}

	@RequestMapping(value = "/cra/create", method = RequestMethod.POST)
	public @ResponseBody Boolean createOrModifyCra(@RequestBody Cra cra) {
		boolean status = false;  
		status = dao.createCra(cra);
		if(status) logger.info("Cra registered :)");
		else logger.info("Cra not registered :)");
		return status;
	}
	
	@RequestMapping(value = "/cra/delete", method = RequestMethod.POST)
	public @ResponseBody Boolean deleteCra(@RequestBody String idcra) {
		boolean status = false;  
		status = dao.deleteCra(idcra);
		if(status) logger.info("Cra deleted :)");
		else logger.info("Cra not deleted :)");
		return status;
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public @ResponseBody Boolean createCra() {

		return true;
	}
	
}
