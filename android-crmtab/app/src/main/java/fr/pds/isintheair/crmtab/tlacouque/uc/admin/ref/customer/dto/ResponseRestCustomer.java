package fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.dto;

import java.util.List;

import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.Company;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.Customer;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.HealthCenter;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.Holding;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.Independant;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.MapInfo;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.PurchasingCentral;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.Specialty;


/**
 * Created by tlacouque on 17/12/2015.
 * Class used has DTO to pass information from rest server to android crm tab
 */
public class ResponseRestCustomer {

    private boolean isInserted;

    private List<Holding> holdings;

    private List<PurchasingCentral> purchasingCentrals;

    private List<Specialty> specialties;

    private List<Company> companies;

    private List<HealthCenter> healthCenters;

    private List<Independant> independants;

    private List<Customer> customers;

    private MapInfo mapInfo;

    private double lat;

    private double lng;


    public boolean getIsInserted() {
        return isInserted;
    }

    public void setIsInserted(boolean isInserted) {
        this.isInserted = isInserted;
    }

    public List<Holding> getHoldings() {
        return holdings;
    }

    public void setHoldings(List<Holding> holdings) {
        this.holdings = holdings;
    }

    public List<PurchasingCentral> getPurchasingCentrals() {
        return purchasingCentrals;
    }

    public void setPurchasingCentrals(List<PurchasingCentral> purchasingCentrals) {
        this.purchasingCentrals = purchasingCentrals;
    }


    public List<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(List<Specialty> specialties) {
        this.specialties = specialties;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public List<HealthCenter> getHealthCenters() {
        return healthCenters;
    }

    public void setHealthCenters(List<HealthCenter> healthCenters) {
        this.healthCenters = healthCenters;
    }

    public List<Independant> getIndependants() {
        return independants;
    }

    public void setIndependants(List<Independant> independants) {
        this.independants = independants;
    }

    public MapInfo getMapInfo() {
        return mapInfo;
    }

    public void setMapInfo(MapInfo mapInfo) {
        this.mapInfo = mapInfo;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

}
