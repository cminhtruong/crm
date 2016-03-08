package fr.pds.isintheair.phonintheair.controller.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

import fr.pds.isintheair.phonintheair.controller.MessageController;
import fr.pds.isintheair.phonintheair.helper.SharedPreferencesHelper;
import fr.pds.isintheair.phonintheair.model.enumeration.MessageType;

/******************************************
 * Created by        : jdatour            *
 * Creation date     : 01/24/16           *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/

public class PhoneCallBroadcastReceiver extends BroadcastReceiver {
    private String TAG = getClass().getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        String lastMessage   = SharedPreferencesHelper.readString("lastMessage", "");
        String previousState = SharedPreferencesHelper.readString("previousTelephonyState", "");
        String state         = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);

        Log.d(TAG, "State : " + state);

        if (state != null) {
            String phoneNumber = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);

            Log.d(TAG, "Phone number : " + phoneNumber);

            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                MessageController.sendCallReceivedMessage(phoneNumber);
            }

            else if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                MessageController.sendEndCallMessage();
            }

            else if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK) && previousState.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                MessageController.sendCallHookMessage();
            }

            else if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK) && previousState.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                if (!lastMessage.equals(MessageType.CALL))
                    MessageController.sendCallPassedMessage(phoneNumber);
            }

            SharedPreferencesHelper.writeString("lastMessage", "");
            SharedPreferencesHelper.writeString("previousTelephonyState", state);
        }
    }
}
