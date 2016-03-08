package miage.pds.api.jbide.uc.registercall.model;

import org.mongodb.morphia.annotations.Id;

/**
 * Created by j-d on 22/12/2015.
 */
public class Cra {

	@Id
	private String idcra;
	private String calltype;
	private String clientname;
	private String comments;
	private String contactname;
	private String date;
	private Long duration;
	private String idcontact;
	private String iduser;
	private String subject;

	/**
	* 
	* @return
	* The calltype
	*/
	public String getCalltype() {
	return calltype;
	}

	/**
	* 
	* @param calltype
	* The calltype
	*/
	public void setCalltype(String calltype) {
	this.calltype = calltype;
	}

	/**
	* 
	* @return
	* The clientname
	*/
	public String getClientname() {
	return clientname;
	}

	/**
	* 
	* @param clientname
	* The clientname
	*/
	public void setClientname(String clientname) {
	this.clientname = clientname;
	}

	/**
	* 
	* @return
	* The comments
	*/
	public String getComments() {
	return comments;
	}

	/**
	* 
	* @param comments
	* The comments
	*/
	public void setComments(String comments) {
	this.comments = comments;
	}

	/**
	* 
	* @return
	* The contactname
	*/
	public String getContactname() {
	return contactname;
	}

	/**
	* 
	* @param contactname
	* The contactname
	*/
	public void setContactname(String contactname) {
	this.contactname = contactname;
	}

	/**
	* 
	* @return
	* The date
	*/
	public String getDate() {
	return date;
	}

	/**
	* 
	* @param date
	* The date
	*/
	public void setDate(String date) {
	this.date = date;
	}

	/**
	* 
	* @return
	* The duration
	*/
	public Long getDuration() {
	return duration;
	}

	/**
	* 
	* @param duration
	* The duration
	*/
	public void setDuration(Long duration) {
	this.duration = duration;
	}

	/**
	* 
	* @return
	* The idcontact
	*/
	public String getIdcontact() {
	return idcontact;
	}

	/**
	* 
	* @param idcontact
	* The idcontact
	*/
	public void setIdcontact(String idcontact) {
	this.idcontact = idcontact;
	}

	/**
	* 
	* @return
	* The iduser
	*/
	public String getIduser() {
	return iduser;
	}

	/**
	* 
	* @param iduser
	* The iduser
	*/
	public void setIduser(String iduser) {
	this.iduser = iduser;
	}

	/**
	* 
	* @return
	* The subject
	*/
	public String getSubject() {
	return subject;
	}

	/**
	* 
	* @param subject
	* The subject
	*/
	public void setSubject(String subject) {
	this.subject = subject;
	}
	
    /**
    *
    * @param idcra
    * The id for the cra
    */
   public void setIdcra(String idcra) {
       this.idcra = idcra;
   }

   /**
    *
    * @return idcra
    * The id for the cra
    */
   public String getIdcra() {
       return idcra;
   }
}
