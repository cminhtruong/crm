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
 * Created by tlacouque on 27/12/2015.
 */
@Table(database = OrmTabDataBase.class)
public class Independant extends BaseModel implements Customer, Parcelable {


    public static final Creator<Independant> CREATOR = new Creator<Independant>() {
        @Override
        public Independant createFromParcel(Parcel in) {
            return new Independant(in);
        }

        @Override
        public Independant[] newArray(int size) {
            return new Independant[size];
        }
    };
    @Column
    @PrimaryKey
    long   siretNumber;
    @Column
    String name;
    @Column
    long   finessNumber;
    @Column
    int    streetNumber;
    @Column
    String streetName;
    @Column
    String town;
    @Column
    int    zipCode;
    @Column
    double longitude;
    @Column
    double lattitude;
    @Column
    String webSite;
    @Column
    int    longTermFidelity;
    @Column
    String origin;
    @Column
    String independantType;
    @Column
    int    specialtyId;
    @Column
    int    companyId;
    @Column
    String idUser;

    public Independant() {
    }

    protected Independant(Parcel in) {
        siretNumber = in.readLong();
        name = in.readString();
        finessNumber = in.readLong();
        streetNumber = in.readInt();
        streetName = in.readString();
        town = in.readString();
        zipCode = in.readInt();
        longitude = in.readDouble();
        lattitude = in.readDouble();
        webSite = in.readString();
        longTermFidelity = in.readInt();
        origin = in.readString();
        independantType = in.readString();
        specialtyId = in.readInt();
        companyId = in.readInt();
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

    public void setIndependantType(String independantType) {
        this.independantType = independantType;
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

    public Specialty getSpecialty() {
        return new Select().from(Specialty.class).where(Specialty_Table.id.is(specialtyId)).querySingle();
    }

    public Company getCompany() {
        return new Select().from(Company.class)
                           .where(Company_Table.id.is(companyId)).querySingle();
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    /**
     * Return the number of serializable attributes to needed to parse an independant object
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
        dest.writeString(webSite);
        dest.writeInt(longTermFidelity);
        dest.writeString(origin);
        dest.writeString(independantType);
        dest.writeInt(specialtyId);
        dest.writeInt(companyId);
        dest.writeString(idUser);
    }
}
