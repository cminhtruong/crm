package miage.pds.api.ctruong.uc.prospect.suggest.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

/**
 * The prospect model class
 * <p/>
 * Modified by Truong on 02/06/2016.
 *
 * @version 1.1.19
 * @serial 111912202015
 */
@Entity("prospect")
public class Prospect{

    @Id
    private ObjectId id;

    @Property
    private long siretNumber;

    @Property
    private String name;

    @Property
    private long finessNumber;

    @Property
    private int streetNumber;

    @Property
    private String streetName;

    @Property
    private String town;

    @Property
    private int zipCode;

    @Property
    private int bedNumber;

    @Property
    private String webSite;

    @Property
    private String etablishmentType;

    @Property
    private String idUser;

    private long turnover;

    /**
     *
     */
    public Prospect() {

    }

    public Prospect(long siretNumber, String name, long finessNumber, int streetNumber, String streetName, String town, int zipCode, int bedNumber, String webSite, String etablishmentType) {
        this.siretNumber = siretNumber;
        this.name = name;
        this.finessNumber = finessNumber;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.town = town;
        this.zipCode = zipCode;
        this.bedNumber = bedNumber;
        this.webSite = webSite;
        this.etablishmentType = etablishmentType;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public long getSiretNumber() {
        return siretNumber;
    }

    public void setSiretNumber(long siretNumber) {
        this.siretNumber = siretNumber;
    }

    public String getName() {
        return name;
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

    public int getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(int bedNumber) {
        this.bedNumber = bedNumber;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getEtablishmentType() {
        return etablishmentType;
    }

    public void setEtablishmentType(String etablishmentType) {
        this.etablishmentType = etablishmentType;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public long getTurnover() {
        return turnover;
    }

    public void setTurnover(long turnover) {
        this.turnover = turnover;
    }

    @Override
    public String toString() {
        return "Prospect{" +
                "id=" + id +
                ", siretNumber=" + siretNumber +
                ", name='" + name + '\'' +
                ", finessNumber=" + finessNumber +
                ", streetNumber=" + streetNumber +
                ", streetName='" + streetName + '\'' +
                ", town='" + town + '\'' +
                ", zipCode=" + zipCode +
                ", bedNumber=" + bedNumber +
                ", webSite='" + webSite + '\'' +
                ", etablishmentType='" + etablishmentType + '\'' +
                ", idUser='" + idUser + '\'' +
                ", turnover=" + turnover +
                '}';
    }


}
