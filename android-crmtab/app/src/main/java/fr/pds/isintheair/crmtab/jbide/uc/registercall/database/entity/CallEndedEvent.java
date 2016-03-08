package fr.pds.isintheair.crmtab.jbide.uc.registercall.database.entity;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import fr.pds.isintheair.crmtab.common.model.database.OrmTabDataBase;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Constants;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.enums.CallType;

/**
 * Created by j-d on 20/12/2015.
 */
@Table(database = OrmTabDataBase.class)
public class CallEndedEvent extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    private long id;
    @Column
    private String idcontact;
    @Column
    private String duration;
    @Column
    private String date;
    @Column
    private CallType callemitted;
    @Column
    private String iduser;


    public CallEndedEvent(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CallEndedEvent(CallType callemitted, String date, String duration, String idcontact) {
        this.callemitted = callemitted;
        this.date = date;
        this.duration = duration;
        this.idcontact = idcontact;
        this.iduser = Constants.getInstance().getCurrentUser().getId();
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public void setIdcontact(String idcontact) {
        this.idcontact = idcontact;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public CallType getCallemitted() {
        return callemitted;
    }

    public void setCallemitted(CallType callemitted) {
        this.callemitted = callemitted;
    }

    public String getIdcontact() {
        return idcontact;
    }

    public CallType getCalltype() {
        return callemitted;
    }

    public String getDate() {
        return date;
    }

    public String getDuration() {
        return duration;
    }

}
