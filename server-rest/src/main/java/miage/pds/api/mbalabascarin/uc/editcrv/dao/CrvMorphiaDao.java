package miage.pds.api.mbalabascarin.uc.editcrv.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.DBPortPool.NoMoreConnection;

import miage.pds.api.mbalabascarin.uc.editcrv.model.Product;
import miage.pds.api.mbalabascarin.uc.editcrv.model.Report;
import miage.pds.api.mbalabascarin.uc.editcrv.model.Reporting;

public class CrvMorphiaDao {
	DB db;
	Long count;
	Morphia morphia;
	Datastore datastore;
	
	public CrvMorphiaDao(){}

	public boolean ConnectDB(){		
		/**** Connect to MongoDB ****/
		MongoClient mongo;
		
		try {

			mongo = new MongoClient(Config.URL, Config.PORT);
			morphia = new Morphia();
			
			/**** Get database ****/
			// if database doesn't exists, MongoDB will create it for you
			datastore = morphia.createDatastore(mongo, "crm");
			return true;

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	//insert or update a report 
	public boolean createOrModifyCrv(Report report){
			
		//check if connected to DB
		if(ConnectDB()){
			if(report.getId().equals("")){
				report.setId(Long.toString(datastore.getCount(Report.class)));
			}
			//save report to DB
			datastore.save(report);
			return true;
		}
		return false;
			
	}
	
	//get all reports for a specific client
	public List<Report> getAllReportsForClient(String id){
		List<Report> list = new ArrayList<Report>();
		if(ConnectDB()){
			list = datastore.createQuery(Report.class)
					.filter("client =", id)
					.asList();
		}
		
		
		return list;
	}
	
	//delete a report by given id
	public Boolean deleteReportById(String id){
		Report report;
		if(ConnectDB()){
			try {
				report = datastore.find(Report.class).field("_id").equal(id).get();
				datastore.delete(report);
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.getMessage();
			}
					
		}
		return false;
	}
	
}
