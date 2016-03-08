package fr.pds.isintheair.crmtab.common.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import fr.pds.isintheair.crmtab.common.CrmTabApplication;

/******************************************
 * Created by        : jdatour            *
 * Creation date     : 01/23/16           *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/

public class NetworkHelper {
    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) CrmTabApplication.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo         activeNetworkInfo   = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
