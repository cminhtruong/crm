package fr.pds.isintheair.crmtab.jbide.uc.registercall;

import java.util.ArrayList;
import java.util.List;

import fr.pds.isintheair.crmtab.common.model.database.entity.User;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.database.entity.CallEndedEvent;


import fr.pds.isintheair.crmtab.jbide.uc.registercall.Rest.Model.Cra;

/**
 * Created by j-d on 21/12/2015.
 */
public class Constants {
    private  static Constants instance = null;
    private String BASE_URL ;
    private  AndroidBus currentBusInstance;
    private  Boolean popupdisplayed;
    private List<Cra> CraListForUser;
    private User currentUser;
    private List<CallEndedEvent> pendingList;


    private Constants(){
        currentBusInstance = new AndroidBus();
        popupdisplayed = false;
        //BASE_URL = "http://192.168.43.131:8080/api/";
        //BASE_URL = "http://192.168.1.68:8080/api/";
        BASE_URL = "http://192.168.20.3:8082/api/";
        CraListForUser = new ArrayList<Cra>();
        currentUser = new User();
        pendingList = new ArrayList<CallEndedEvent>();

        
    }

    public static Constants getInstance(){
        if(instance == null)
        {
            instance = new Constants();
        }
        return instance;
    }

    public Boolean isPopUpDisplayed(){

        if(popupdisplayed == null)
        {
            popupdisplayed = false;
        }
        return popupdisplayed;

    }

    public void setPopUpDisplayed(boolean state){

         popupdisplayed = state;
    }

    public  AndroidBus getCurrentBusInstance(){

        if(currentBusInstance == null)
        {
            currentBusInstance = new AndroidBus();
        }
        return currentBusInstance;
    }

    public  String getBaseUrl(){

        return BASE_URL;
    }

    public void setCraListForUser(List<Cra> liste){

            CraListForUser = liste;

    }


    public List<Cra> getCraListForUser(){

        if(CraListForUser == null)
        {
            CraListForUser = new ArrayList<Cra>();
        }
        return CraListForUser;
    }

    public void setCurrentUser(User currentuser) {
        currentUser = currentuser;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public List<CallEndedEvent> getPendindList() {
        return pendingList;
    }
}
