package fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import fr.pds.isintheair.crmtab.common.model.database.OrmTabDataBase;


/**
 * Created by tlacouque on 27/12/2015.
 */
@Table(database = OrmTabDataBase.class)
public class Specialty extends BaseModel {

    @Column
    @PrimaryKey
    int id;

    @Column
    String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
