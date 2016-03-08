package miage.pds.api.tlacouque.uc.admin.ref.customer.entities;

/**
 * Created by tlacouque on 29/12/2015.
 */
public interface Customer {
    long getSiretNumber();
    String getName();
    String getAdress();
    void setLattitude(double lat);
    void setLongitude(double lng);
    double getLattitude();
    double getLongitude();
}
