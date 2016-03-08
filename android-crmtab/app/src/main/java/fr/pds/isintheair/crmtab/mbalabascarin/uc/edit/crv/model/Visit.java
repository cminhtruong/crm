package fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.model;

import java.io.Serializable;

/**
 * Created by Muthu on 18/12/2015.
 */
public class Visit implements Serializable {

    private int id;
    private String[] subject;
    private String date;
    private long idContact;


    public Visit(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String[] getSubject() {
        return subject;
    }

    public void setSubject(String[] subject) {
        this.subject = subject;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getIdContact() {
        return idContact;
    }

    public void setIdClient(long idContact) {
        this.idContact = idContact;
    }


}
