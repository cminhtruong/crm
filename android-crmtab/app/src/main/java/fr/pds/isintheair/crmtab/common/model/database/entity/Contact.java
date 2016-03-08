package fr.pds.isintheair.crmtab.common.model.database.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

import fr.pds.isintheair.crmtab.common.model.database.OrmTabDataBase;
import fr.pds.isintheair.crmtab.common.model.database.dao.ContactDAO;

/******************************************
 * Created by        : mbalabascarin      *
 * Creation date     : 01/08/2016         *
 * Modified by       : jdatour            *
 * Modification date : 01/23/2016         *
 * Modified by       : jbide              *
 * Modification date : 01/27/2016         *
 ******************************************/

@Table(database = OrmTabDataBase.class)
public class Contact extends BaseModel implements Parcelable {
    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    @Column
    @PrimaryKey
    public String phoneNumber;

    @Column
    public String firstName;

    @Column
    public String lastName;



    public Contact() {
    }

    public Contact(String phoneNumber, String firstName, String lastName) {
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    protected Contact(Parcel in) {
        this.phoneNumber = in.readString();
        this.firstName = in.readString();
        this.lastName = in.readString();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static Contact getNameFromNumber(String num) {
        List<Contact> contacts       = ContactDAO.getAll();
        Contact result = null;
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getPhoneNumber().equals(num)) {
                result = contacts.get(i);
                break;
            }
        }
            return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.phoneNumber);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
    }
}
