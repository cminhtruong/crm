package fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import fr.pds.isintheair.crmtab.common.model.database.OrmTabDataBase;

/**
 * Created by tlacouque on 03/02/2016.
 */

@Table(database = OrmTabDataBase.class)
public class MapInfo extends BaseModel {

    @Column
    @PrimaryKey
    long siretNumber;

    @Column
    int x;

    @Column
    int y;

    @Column
    int z;

    public MapInfo() {
    }

    public MapInfo(long siretNumber, int x, int y, int z) {
        this.siretNumber = siretNumber;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public long getSiretNumber() {
        return siretNumber;
    }

    public void setSiretNumber(long siretNumber) {
        this.siretNumber = siretNumber;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }
}
