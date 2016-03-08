package fr.pds.isintheair.crmtab.jbide.uc.registercall.Rest.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by j-d on 22/12/2015.
 */
//@Table(databaseName = OrmTabDataBase.DBNAME)
public class Cra  {

    @SerializedName("calltype")
    @Expose
    private String calltype;
    @SerializedName("clientname")
    @Expose
    private String clientname;
    @SerializedName("comments")
    @Expose
    private String comments;
    @SerializedName("contactname")
    @Expose
    private String contactname;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("duration")
    @Expose
    private Long duration;
    @SerializedName("idcontact")
    @Expose
    private String idcontact;
    @SerializedName("iduser")
    @Expose
    private String iduser;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("idcra")
    @Expose
    private String idcra;

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
