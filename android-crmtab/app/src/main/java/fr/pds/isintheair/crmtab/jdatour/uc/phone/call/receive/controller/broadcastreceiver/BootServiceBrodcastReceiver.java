package fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.service.CallService;

/******************************************
 * Created by        : jdatour            *
 * Creation date     : 01/23/16           *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/

public class BootServiceBrodcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            final Intent serviceIntent = new Intent(context, CallService.class);

            context.startService(serviceIntent);

        }
    }
}
