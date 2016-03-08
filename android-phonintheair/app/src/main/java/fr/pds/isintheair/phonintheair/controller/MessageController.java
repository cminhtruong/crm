package fr.pds.isintheair.phonintheair.controller;

import fr.pds.isintheair.phonintheair.helper.SharedPreferencesHelper;
import fr.pds.isintheair.phonintheair.model.entity.Call;
import fr.pds.isintheair.phonintheair.model.entity.Message;
import fr.pds.isintheair.phonintheair.model.entity.MessageMeta;
import fr.pds.isintheair.phonintheair.model.entity.Register;
import fr.pds.isintheair.phonintheair.model.enumeration.DeviceType;
import fr.pds.isintheair.phonintheair.model.enumeration.MessageType;
import fr.pds.isintheair.phonintheair.model.websocket.WebSocketConnectionHandlerSingleton;

public class MessageController {
    public static void handleMessage(Message message) {
        switch (message.getMessageMeta().getMessageType()) {
            case CALL:
                CallController.call(message.getCall().getPhoneNumber());
                SharedPreferencesHelper.writeString("lastMessage", message.getMessageMeta().getMessageType().toString());
                break;
            case CALL_ENDED:
                CallController.endCall();
                break;
        }
    }

    public static void sendCallHookMessage() {
        MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(MessageType.CALL_HOOKED).build();
        Message     message     = new Message.MessageBuilder().addMessageMeta(messageMeta).build();

        WebSocketConnectionHandlerSingleton.getInstance().sendMessage(message);
    }

    public static void sendCallReceivedMessage(String phoneNumber) {
        MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(MessageType.CALL_RECEIVED).build();
        Call        call        = new Call(phoneNumber);
        Message     message     = new Message.MessageBuilder().addMessageMeta(messageMeta).addCall(call).build();

        WebSocketConnectionHandlerSingleton.getInstance().sendMessage(message);
    }

    public static void sendCallPassedMessage(String phoneNumber) {
        MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(MessageType.CALL_PASSED).build();
        Call        call        = new Call(phoneNumber);
        Message     message     = new Message.MessageBuilder().addMessageMeta(messageMeta).addCall(call).build();

        WebSocketConnectionHandlerSingleton.getInstance().sendMessage(message);
    }

    public static void sendEndCallMessage() {
        MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(MessageType.CALL_ENDED).addDeviceType(DeviceType.PHONE).build();
        Message     message     = new Message.MessageBuilder().addMessageMeta(messageMeta).build();

        WebSocketConnectionHandlerSingleton.getInstance().sendMessage(message);
    }

    public static void sendRegisterMessage(Integer userId) {
        MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(MessageType.REGISTER_PHONE).build();
        Register    register    = new Register(userId);
        Message     message     = new Message.MessageBuilder().addMessageMeta(messageMeta).addRegister(register).build();

        WebSocketConnectionHandlerSingleton.getInstance().sendMessage(message);
    }
}
