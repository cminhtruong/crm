package miage.pds.api.tlacouque.uc.admin.ref.customer.entities;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by tlacouque on 01/02/2016.
 */

@Entity("mapinfo")
public class MapInfo {
    @Id
    long siretNumber;
    int x;
    int y;
    int z;


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


