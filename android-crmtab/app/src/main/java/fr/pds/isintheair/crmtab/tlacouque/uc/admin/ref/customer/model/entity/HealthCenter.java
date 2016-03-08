package fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;

import fr.pds.isintheair.crmtab.common.model.database.OrmTabDataBase;


/**
 * Created by tlacouque on 12/12/2015.
 */

@Table(database = OrmTabDataBase.class)
public class HealthCenter extends BaseModel implements Customer, Parcelable {

    public static final Creator<HealthCenter> CREATOR = new Creator<HealthCenter>() {
        @Override
        public HealthCenter createFromParcel(Parcel in) {
            return new HealthCenter(in);
        }

        @Override
        public HealthCenter[] newArray(int size) {
            return new HealthCenter[size];
        }
    };
    @Column
    @PrimaryKey
    long    siretNumber;
    @Column
    String  name;
    @Column
    long    finessNumber;
    @Column
    int     streetNumber;
    @Column
    String  streetName;
    @Column
    String  town;
    @Column
    int     zipCode;
    @Column
    double  longitude;
    @Column
    double  lattitude;
    @Column
    int     bedNumber;
    @Column
    String  webSite;
    @Column
    int     serviceBuildingImage;
    @Column
    int     difficultyHavingContact;
    @Column
    String  origin;
    @Column
    boolean isPublic;
    @Column
    String  etablishmentType;
    @Column
    int     purchasingCentralId;
    @Column
    int     holdingId;
    @Column
    String  idUser;

    public HealthCenter() {
    }

    protected HealthCenter(Parcel in) {
        siretNumber = in.readLong();
        name = in.readString();
        finessNumber = in.readLong();
        streetNumber = in.readInt();
        streetName = in.readString();
        town = in.readString();
        zipCode = in.readInt();
        longitude = in.readDouble();
        lattitude = in.readDouble();
        bedNumber = in.readInt();
        webSite = in.readString();
        serviceBuildingImage = in.readInt();
        difficultyHavingContact = in.readInt();
        origin = in.readString();
        isPublic = in.readByte() != 0;
        etablishmentType = in.readString();
        purchasingCentralId = in.readInt();
        holdingId = in.readInt();
        idUser = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAdress() {
        return "" + streetNumber + " " + streetName + " ," + town;
    }

    public long getSiretNumber() {
        return siretNumber;
    }

    public void setSiretNumber(long siretNumber) {
        this.siretNumber = siretNumber;
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


    public Holding getHolding() {
        return new Select().from(Holding.class).where(Holding_Table.id.is(holdingId)).querySingle();
    }

    public PurchasingCentral getPurchasingCentral() {
        return new Select().from(PurchasingCentral.class)
                           .where(PurchasingCentral_Table.id.is(purchasingCentralId)).querySingle();
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    /**
     * Return the number of serializable attributes to needed to parse an health center object
     *
     * @return int
     */
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(siretNumber);
        dest.writeString(name);
        dest.writeLong(finessNumber);
        dest.writeInt(streetNumber);
        dest.writeString(streetName);
        dest.writeString(town);
        dest.writeInt(zipCode);
        dest.writeDouble(longitude);
        dest.writeDouble(lattitude);
        dest.writeInt(bedNumber);
        dest.writeString(webSite);
        dest.writeInt(serviceBuildingImage);
        dest.writeInt(difficultyHavingContact);
        dest.writeString(origin);
        dest.writeInt(isPublic ? 1 : 0);
        dest.writeString(etablishmentType);
        dest.writeInt(purchasingCentralId);
        dest.writeInt(holdingId);
        dest.writeString(idUser);
    }
}
