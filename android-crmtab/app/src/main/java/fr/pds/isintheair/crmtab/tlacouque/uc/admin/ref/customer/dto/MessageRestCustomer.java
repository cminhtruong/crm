package fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.dto;

import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.Independant;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.HealthCenter;

/**
 * Created by tlacouque on 17/12/2015.
 * Class used has DTO to pass information from android to the rest server
 */
public class MessageRestCustomer {

    private int idUser;
    private HealthCenter healthCenter;
    private Independant independant;

    public MessageRestCustomer(int idUser, HealthCenter healthCenter) {
        this.idUser = idUser;
        this.healthCenter = healthCenter;
    }

   public MessageRestCustomer(int idUser, Independant independant) {
        this.idUser = idUser;
       this.independant = independant;
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

    public void setHealthCenter(HealthCenter healthCenter) {
        this.healthCenter = healthCenter;
    }

   public Independant getIndependant() {
        return independant;
    }

    public void setIndependant(Independant independant) {
        this.independant = independant;
    }
}
