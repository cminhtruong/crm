package fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.model;

import java.io.Serializable;

/**
 * Created by Muthu on 18/12/2015.
 */
public class Client implements Serializable {
    private long clientId;
    private String clientSurname,clientName, clientAddress;

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public long getClientId() {

        return clientId;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public Client(){}

}
