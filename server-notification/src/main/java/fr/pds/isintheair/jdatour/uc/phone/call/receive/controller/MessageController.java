package fr.pds.isintheair.jdatour.uc.phone.call.receive.controller;

import fr.pds.isintheair.jdatour.uc.phone.call.receive.entity.Message;
import fr.pds.isintheair.jdatour.uc.phone.call.receive.enumeration.DeviceType;

import javax.websocket.Session;

public class MessageController {
    public static void handleMessage(Message message, Session session) {
        switch (message.getMessageMeta().getMessageType()) {
            case REGISTER_PHONE:
                RegisterController.register(DeviceType.PHONE, message.getRegister().getUserId(), session);
                break;
            case REGISTER_TABLET:
                RegisterController.register(DeviceType.TABLET, message.getRegister().getUserId(), session);
                break;
            case CALL:
                CallController.call(session, message.getCall().getPhoneNumber());
                break;
            case CALL_ENDED:
                CallController.endCall(message.getMessageMeta().getDeviceType(), session);
                break;
            case CALL_HOOKED:
                CallController.notifyCallHooked(session);
                break;
            case CALL_PASSED:
                CallController.notifyCallPassed(session, message.getCall().getPhoneNumber());
                break;
            case CALL_RECEIVED:
                CallController.notifyCallReceived(session, message.getCall().getPhoneNumber());
                break;
        }
    }
}
