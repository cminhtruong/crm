package fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller;

import fr.pds.isintheair.crmtab.common.model.database.dao.UserDAO;
import fr.pds.isintheair.crmtab.common.model.database.entity.User;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.entity.Call;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.entity.Message;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.entity.MessageMeta;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.entity.Register;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.enumeration.DeviceType;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.enumeration.MessageType;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.websocket.WebSocketConnectionHandlerSingleton;

public class MessageController {
    public static void handleMessage(Message message) {
        switch (message.getMessageMeta().getMessageType()) {
            case CALL:
                CallController.call(message.getCall().getPhoneNumber());
                break;
            case CALL_ENDED:
                CallController.endCall();
                break;
            case CALL_HOOKED:
                CallController.callHooked();
                break;
            case CALL_PASSED:
            case CALL_RECEIVED:
                String phoneNumber = null;

                if (message.getCall() != null)
                    phoneNumber = message.getCall().getPhoneNumber();

                CallController.notifyCallFromPhone(phoneNumber, message.getMessageMeta().getMessageType());
                break;
        }
    }

    public static void sendCallMessage(String phoneNumber) {
        MessageType messageType = MessageType.CALL;
        MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(messageType).build();
        Call        call        = new Call(phoneNumber);
        Message     message     = new Message.MessageBuilder().addMessageMeta(messageMeta).addCall(call).build();

        WebSocketConnectionHandlerSingleton.getInstance().sendMessage(message);
    }

    public static void sendEndCallMessage() {
        MessageType messageType = MessageType.CALL_ENDED;
        MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(messageType).addDeviceType(DeviceType.TABLET).build();
        Message     message     = new Message.MessageBuilder().addMessageMeta(messageMeta).build();

        WebSocketConnectionHandlerSingleton.getInstance().sendMessage(message);
    }

    public static void sendRegisterMessage() {
        MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(MessageType.REGISTER_TABLET).build();
        User        currentUser = UserDAO.getCurrentUser();
        Register    register    = new Register(currentUser.getEmail().hashCode());
        Message     message     = new Message.MessageBuilder().addMessageMeta(messageMeta).addRegister(register).build();

        WebSocketConnectionHandlerSingleton.getInstance().sendMessage(message);
    }
}
