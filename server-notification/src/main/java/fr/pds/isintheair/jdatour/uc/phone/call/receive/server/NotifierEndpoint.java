package fr.pds.isintheair.jdatour.uc.phone.call.receive.server;

import fr.pds.isintheair.jdatour.uc.phone.call.receive.controller.MessageController;
import fr.pds.isintheair.jdatour.uc.phone.call.receive.entity.Message;
import fr.pds.isintheair.jdatour.uc.phone.call.receive.helper.JSONHelper;
import fr.pds.isintheair.jdatour.uc.phone.call.receive.peer.PeerHandlerSingleton;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.logging.Logger;

@ServerEndpoint("/call")
public class NotifierEndpoint {
    private final static Logger logger = Logger.getLogger(NotifierEndpoint.class.getName());

    @OnOpen
    public void onOpen(Session session) {
        logger.info("Session opened");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        logger.info("Error : " + throwable.getMessage());
    }

    @OnMessage
    public void onMessage(String body, Session session) {
        logger.info("Message received : " + body);

        Message message = (Message) JSONHelper.deserialize(body, Message.class);

        MessageController.handleMessage(message, session);
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info("Cloded session : " + closeReason.getReasonPhrase());
        PeerHandlerSingleton.getInstance().removePeerSession(session);
    }
}
