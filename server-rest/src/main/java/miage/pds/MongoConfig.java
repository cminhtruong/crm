package miage.pds;

import javax.print.DocFlavor;

/**
 * Created by tlacouque on 21/01/2016.
 *
 */

/**
 * Modified by jbide  09/02/2016.
 * Modified by tlacouque  09/02/2016.
 */

public interface MongoConfig {

    // IP + PORT Adress Prod instance from local
    public static String LOCAL_PROD_IP = "192.168.20.3";
    public static int LOCAL_PROD_PORT = 8087;


    // IP + PORT Adress Dev instance local
    public static String LOCAL_DEV_IP = "192.168.20.3";
    public static int LOCAL_DEV_PORT = 8088;

    //IP + PORT Adress localhost instance
    // IP + PORT Adress Dev instancce
    public static String LOCAL_IP = "127.0.0.1";
    public static int LOCAL_PORT = 27017;


    // IP + PORT Adress Prod instance
    public static String VM_PROD_IP = "10.0.0.4";
    public static int VM_PROD_PORT = 27017;

    
    // IP + PORT Adress Dev instancce
    public static String VM_DEV_IP = "10.0.0.5";
    public static int VM_DEV_PORT = 27017;
    //public static int VM_DEV_PORT = 8088;
    //public static String VM_DEV_IP = "localhost";
    //public static int VM_DEV_PORT = 27017;

}
