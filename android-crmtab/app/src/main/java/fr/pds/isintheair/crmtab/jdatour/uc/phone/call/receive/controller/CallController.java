package fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller;

import android.content.Intent;

import fr.pds.isintheair.crmtab.common.CrmTabApplication;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.bus.BusHandlerSingleton;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.bus.event.PhoneCallEndedEvent;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.bus.event.PhoneCallHookedEvent;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.enumeration.MessageType;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.view.CallActivity;

public class CallController {
    public static void call(String phoneNumber) {
        MessageController.sendCallMessage(phoneNumber);
        startCallActivity(phoneNumber, MessageType.CALL_PASSED);
    }

    public static void callHooked() {
        BusHandlerSingleton.getInstance().getBus().post(new PhoneCallHookedEvent());
    }

    public static void endCall() {
        BusHandlerSingleton.getInstance().getBus().post(new PhoneCallEndedEvent());
    }

    public static void notifyCallFromPhone(String phoneNumber, MessageType messageType) {
        startCallActivity(phoneNumber, messageType);
    }

    private static void startCallActivity(String phoneNumber, MessageType messageType) {
        Intent intent = new Intent();

        intent.setClass(CrmTabApplication.context, CallActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("phoneNumber", phoneNumber);
        intent.putExtra("messageType", messageType);

        CrmTabApplication.context.startActivity(intent);
    }
}
