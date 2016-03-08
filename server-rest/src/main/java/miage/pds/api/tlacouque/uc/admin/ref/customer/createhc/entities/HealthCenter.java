package miage.pds.api.tlacouque.uc.admin.ref.customer.createhc.entities;

import miage.pds.MongoDatastoreConfig;
import miage.pds.api.tlacouque.uc.admin.ref.customer.controller.RestCustomerController;
import miage.pds.api.tlacouque.uc.admin.ref.customer.entities.Customer;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

/**
 * Created by tlacouque on 16/12/2015.
 */

@Entity("healthcenter")
public class HealthCenter implements Customer {

    @Id
    long  siretNumber;

    @Property
    String name;

    @Property
    long  finessNumber;

    @Property
    int streetNumber;

    @Property
    String streetName;

    @Property
    String town;

    @Property
    int zipCode;

    @Property
    double longitude;

    @Property
    double lattitude;

    @Property
    int bedNumber;

    @Property
    String webSite;


    @Property
    int serviceBuildingImage;

    @Property
    int difficultyHavingContact;


    @Property
    String origin;


    @Property
    boolean isPublic;

    @Property
    String etablishmentType;


    @Property
    int purchasingCentralId;

    @Property
    int holdingId;

    @Property
    String idUser;


    public HealthCenter() {
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return ""+streetNumber+" "+streetName+" ,"+town;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSiretNumber() {
        return siretNumber;
    }

    public void setSiretNumber(long siretNumber) {
        this.siretNumber = siretNumber;
    }

    public long  getFinessNumber() {
        return finessNumber;
    }

    public void setFinessNumber(long finessNumber) {
        this.finessNumber = finessNumber;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public int getServiceBuildingImage() {
        return serviceBuildingImage;
    }

    public void setServiceBuildingImage(int serviceBuildingImage) {
        this.serviceBuildingImage = serviceBuildingImage;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }


    public boolean isPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public String getEtablishmentType() {
        return etablishmentType;
    }

    public void setEtablishmentType(String etablishmentType) {
        this.etablishmentType = etablishmentType;
    }



    public int getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(int bedNumber) {
        this.bedNumber = bedNumber;
    }

    public int getDifficultyHavingContact() {
        return difficultyHavingContact;
    }

    public void setDifficultyHavingContact(int difficultyHavingContact) {
        this.difficultyHavingContact = difficultyHavingContact;
    }

    public int getPurchasingCentralId() {
        return purchasingCentralId;
    }

    public void setPurchasingCentralId(int purchasingCentralId) {
        this.purchasingCentralId = purchasingCentralId;
    }

    public int getHoldingId() {
        return holdingId;
    }

    public void setHoldingId(int holdingId) {
        this.holdingId = holdingId;
    }

    public PurchasingCentral getPurchasingCentral() {
        PurchasingCentral purchasingCentral = MongoDatastoreConfig.getDataStore()
                .find(PurchasingCentral.class,"id =",purchasingCentralId).get();

        return purchasingCentral;
    }

    public Holding getHolding() {
        Holding holding = MongoDatastoreConfig.getDataStore()
                .find(Holding.class,"id =",holdingId).get();
        return holding;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
