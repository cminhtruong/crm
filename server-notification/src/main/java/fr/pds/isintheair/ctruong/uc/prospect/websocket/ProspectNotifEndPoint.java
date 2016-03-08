package fr.pds.isintheair.ctruong.uc.prospect.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * The EndPoint to connect to the websocket server
 * Created by Truong on 1/23/2016.
 *
 * @version 1.0.0
 * @since 2016-01-24
 */
@ServerEndpoint("/prospect")
public class ProspectNotifEndPoint {

    static final Logger log = Logger.getLogger(ProspectNotifEndPoint.class.getSimpleName());
    static ScheduledExecutorService timer =
            Executors.newSingleThreadScheduledExecutor();

    @OnOpen
    public void onOpen(Session session){
        log.info("I'm starting");
        timer.scheduleAtFixedRate(() -> sendTimeToAll(session), 7, 7, TimeUnit.HOURS);
    }


    private void sendTimeToAll(Session session) {
        try {
            session.getBasicRemote().sendText("We found new prospect. Are you interesting?");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(Session session, String message){
        log.info(message);
    }

    @OnClose
    public void onClose(Session session){
        log.info("I'm closing ...");
    }


}
