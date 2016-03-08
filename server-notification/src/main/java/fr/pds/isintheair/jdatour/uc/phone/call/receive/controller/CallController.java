package fr.pds.isintheair.jdatour.uc.phone.call.receive.controller;

import fr.pds.isintheair.jdatour.uc.phone.call.receive.entity.Call;
import fr.pds.isintheair.jdatour.uc.phone.call.receive.entity.Message;
import fr.pds.isintheair.jdatour.uc.phone.call.receive.entity.MessageMeta;
import fr.pds.isintheair.jdatour.uc.phone.call.receive.enumeration.DeviceType;
import fr.pds.isintheair.jdatour.uc.phone.call.receive.enumeration.MessageType;
import fr.pds.isintheair.jdatour.uc.phone.call.receive.helper.JSONHelper;

import javax.websocket.Session;
import java.io.IOException;

public class CallController {
    public static void call(Session tabletSession, String phoneNumber) {
        Session phoneSession = PeerController.getPhoneSession(tabletSession);

        if (phoneSession == null) {
            MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(MessageType.CALL_FAILED).build();
            Message message = new Message.MessageBuilder().addMessageMeta(messageMeta).build();

            try {
                tabletSession.getBasicRemote().sendText(JSONHelper.serialize(message, Message.class));
            }
            catch (IOException e) {
                //TODO handle exception
            }
        }

        else {
            MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(MessageType.CALL).build();
            Call call = new Call(phoneNumber);
            Message message = new Message.MessageBuilder().addMessageMeta(messageMeta).addCall(call).build();

            try {
                phoneSession.getBasicRemote().sendText(JSONHelper.serialize(message, Message.class));
            }
            catch (IOException e) {
                //TODO handle exception
            }
        }
    }

    public static void endCall(DeviceType deviceType, Session session) {
        Session peerSession = null;

        switch (deviceType) {
            case PHONE:
                peerSession = PeerController.getTabletSession(session);
                break;
            case TABLET:
                peerSession = PeerController.getPhoneSession(session);
                break;
        }

        MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(MessageType.CALL_ENDED).build();
        Message message = new Message.MessageBuilder().addMessageMeta(messageMeta).build();

        try {
            peerSession.getBasicRemote().sendText(JSONHelper.serialize(message, Message.class));
        }
        catch (IOException e) {
            //TODO handle exception
        }
    }

    public static void notifyCallHooked(Session phoneSession) {
        Session tabletSession = PeerController.getTabletSession(phoneSession);
        MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(MessageType.CALL_HOOKED).build();
        Message message = new Message.MessageBuilder().addMessageMeta(messageMeta).build();

        try {
            tabletSession.getBasicRemote().sendText(JSONHelper.serialize(message, Message.class));
        }
        catch (IOException e) {
            //TODO handle exception
        }
    }

    public static void notifyCallPassed(Session phoneSession, String phoneNumber) {
        Session tabletSession = PeerController.getTabletSession(phoneSession);
        MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(MessageType.CALL_PASSED).build();
        Call call = new Call(phoneNumber);
        Message message = new Message.MessageBuilder().addMessageMeta(messageMeta).addCall(call).build();

        try {
            tabletSession.getBasicRemote().sendText(JSONHelper.serialize(message, Message.class));
        }
        catch (IOException e) {
            //TODO handle exception
        }
    }

    public static void notifyCallReceived(Session phoneSession, String phoneNumber) {
        Session tabletSession = PeerController.getTabletSession(phoneSession);
        MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(MessageType.CALL_RECEIVED).build();
        Call call = new Call(phoneNumber);
        Message message = new Message.MessageBuilder().addMessageMeta(messageMeta).addCall(call).build();

        try {
            tabletSession.getBasicRemote().sendText(JSONHelper.serialize(message, Message.class));
        }
        catch (IOException e) {
            //TODO handle exception
        }
    }
}
