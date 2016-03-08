package miage.pds.api.tlacouque.uc.admin.ref.customer.message;

import miage.pds.api.tlacouque.uc.admin.ref.customer.createhc.entities.HealthCenter;
import miage.pds.api.tlacouque.uc.admin.ref.customer.createindep.entities.Independant;

/**
 * Created by tlacouque on 17/12/2015.
 * DTO used between Android crm tab, to the current rest server
 */
public class MessageRestCustomer {

    private int idUser;
    private HealthCenter healthCenter;
    private Independant independant;

    public MessageRestCustomer() {
    }

    public MessageRestCustomer(int idUser, HealthCenter healthEtablishment) {
        this.idUser = idUser;
        this.healthCenter = healthEtablishment;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public HealthCenter getHealthCenter() {
        return healthCenter;
    }

    public void setHealthCenter(HealthCenter healthEtablishment) {
        this.healthCenter = healthEtablishment;
    }

    public Independant getIndependant() {
        return independant;
    }

    public void setIndependant(Independant independant) {
        this.independant = independant;
    }
}
