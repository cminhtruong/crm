package miage.pds.api.tlacouque.uc.admin.ref.customer.common;

import miage.pds.MongoDatastoreConfig;
import miage.pds.api.tlacouque.uc.admin.ref.customer.dao.MapInfoDAO;
import miage.pds.api.tlacouque.uc.admin.ref.customer.entities.Customer;
import miage.pds.api.tlacouque.uc.admin.ref.customer.entities.MapInfo;

/**
 * Created by tlacouque on 01/02/2016.
 * Class used to calculate XYZ of an tile.
 */
public class XYZCalcul {
    private static int zoom = 15;


    /**
     * Calculate x,y,z of a tile and create a new MapInfo (save it)
     * to be used by android crm tab.
     * @param customer
     * @return MapInfo
     */
    public static MapInfo getMapInfo(Customer customer) {

        double lat = customer.getLattitude();
        double lon = customer.getLongitude();


        int xtile = (int)Math.floor( (lon + 180) / 360 * (1<<zoom) ) ;
        int ytile = (int)Math.floor( (1 - Math.log(Math.tan(Math.toRadians(lat)) + 1 / Math.cos(Math.toRadians(lat))) / Math.PI) / 2 * (1<<zoom) ) ;
        if (xtile < 0)
            xtile=0;
        if (xtile >= (1<<zoom))
            xtile=((1<<zoom)-1);
        if (ytile < 0)
            ytile=0;
        if (ytile >= (1<<zoom))
            ytile=((1<<zoom)-1);

        MapInfo mapInfo = new MapInfo(customer.getSiretNumber(),zoom,xtile,ytile);
        new MapInfoDAO(MongoDatastoreConfig.getDataStore()).save(mapInfo);
        return mapInfo;
    }

}
