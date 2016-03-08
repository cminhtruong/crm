package fr.pds.isintheair.phonintheair.model.websocket;

import android.util.Log;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import fr.pds.isintheair.phonintheair.helper.JSONHelper;
import fr.pds.isintheair.phonintheair.model.constant.Constant;
import fr.pds.isintheair.phonintheair.model.entity.Message;

public class WebSocketConnectionHandlerSingleton {
    private static WebSocketConnectionHandlerSingleton INSTANCE            = null;
    private        String                              TAG                 = getClass().getSimpleName();
    private        WebSocketConnection                 webSocketConnection = null;

    private WebSocketConnectionHandlerSingleton() {
        webSocketConnection = new WebSocketConnection();
    }

    public static synchronized WebSocketConnectionHandlerSingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WebSocketConnectionHandlerSingleton();
        }

        return INSTANCE;
    }

    /**
     * Connect to web socket server
     */
    public void connect() {
        CallWebSocketHandler callWebSocketHandler = new CallWebSocketHandler();

        try {
            webSocketConnection.connect(Constant.WS_URL, callWebSocketHandler);
        }
        catch (WebSocketException e) {
            Log.d(TAG, "Websocket connection failed : " + e.getMessage());
            //TODO handle exception
        }
    }

    /**
     * Send a message using websocket
     *
     * @param message the message to send
     */
    public void sendMessage(Message message) {
        String serializedMessage = JSONHelper.serialize(message, Message.class);

        Log.d(TAG, "Sending : " + serializedMessage);
        webSocketConnection.sendTextMessage(serializedMessage);
    }
}
