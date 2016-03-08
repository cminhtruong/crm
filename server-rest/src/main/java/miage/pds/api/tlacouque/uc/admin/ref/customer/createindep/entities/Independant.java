package miage.pds.api.tlacouque.uc.admin.ref.customer.createindep.entities;


import miage.pds.api.tlacouque.uc.admin.ref.customer.entities.Customer;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

/**
 * Created by tlacouque on 28/12/2015.
 */

@Entity("independant")
public class Independant implements Customer {

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
    String webSite;

    @Property
    int longTermFidelity;

    @Property
    String origin;

    @Property
    String independantType;

    @Property
    int specialtyId;

    @Property
    int companyId;

    @Property
    String idUser;

    public String getName() {
        return name;
    }

    public String getAdress() {
        return ""+streetNumber+" "+streetName+" ,"+town;
    }

    public long getSiretNumber() {
        return siretNumber;
    }

    public void setSiretNumber(long siretNumber) {
        this.siretNumber = siretNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getFinessNumber() {
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

    public int getLongTermFidelity() {
        return longTermFidelity;
    }

    public void setLongTermFidelity(int longTermFidelity) {
        this.longTermFidelity = longTermFidelity;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getIndependantType() {
        return independantType;
    }

    public void setIndependantType(String intdependantType) {
        this.independantType = intdependantType;
    }

    public int getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(int specialtyId) {
        this.specialtyId = specialtyId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
