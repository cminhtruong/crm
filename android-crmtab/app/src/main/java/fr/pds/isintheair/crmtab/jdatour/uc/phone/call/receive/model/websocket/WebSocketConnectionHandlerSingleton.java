package fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.websocket;

import android.util.Log;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.common.CrmTabApplication;
import fr.pds.isintheair.crmtab.common.helper.NetworkHelper;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.bus.BusHandlerSingleton;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.bus.event.PhoneCallFailedEvent;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.helper.JSONHelper;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.constant.Constant;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.entity.Message;

public class WebSocketConnectionHandlerSingleton {

    private static WebSocketConnectionHandlerSingleton INSTANCE             = null;
    private        String                              TAG                  = getClass().getSimpleName();
    private        CallWebSocketHandler                callWebSocketHandler = null;
    private        WebSocketConnection                 webSocketConnection  = null;

    private WebSocketConnectionHandlerSingleton() {
        callWebSocketHandler = new CallWebSocketHandler();
        webSocketConnection = new WebSocketConnection();
    }

    public static synchronized WebSocketConnectionHandlerSingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WebSocketConnectionHandlerSingleton();
        }

        return INSTANCE;
    }

    public void connect() {
        if (callWebSocketHandler == null)
            callWebSocketHandler = new CallWebSocketHandler();

        if (webSocketConnection == null)
            webSocketConnection = new WebSocketConnection();

        try {
            webSocketConnection.connect(Constant.WS_URL, callWebSocketHandler);
            Log.d(TAG, "Websocket connection opened");
        }
        catch (WebSocketException e) {
            Log.d(TAG, "Websocket connection failed : " + e.getMessage());
            //TODO handle exception
        }
    }

    public void sendMessage(Message message) {
        if (webSocketConnection == null) {
            connect();
            String errorMessage = CrmTabApplication.context.getResources().getString(R.string.message_failed_websocket_connection_killed);
            BusHandlerSingleton.getInstance().getBus().post(new PhoneCallFailedEvent(errorMessage));
            WebSocketConnectionHandlerSingleton.getInstance().connect();
            Log.d(TAG, "Websocket connection is dead");
        }

        else {
            if (NetworkHelper.isNetworkAvailable()) {
                String serializedMessage = JSONHelper.serialize(message, Message.class);
                webSocketConnection.sendTextMessage(serializedMessage);
                Log.d(TAG, "Sending message : " + serializedMessage);
            }
            else {
                String errorMessage = CrmTabApplication.context.getResources().getString(R.string.message_failed_no_internet);
                BusHandlerSingleton.getInstance().getBus().post(new PhoneCallFailedEvent(errorMessage));
                WebSocketConnectionHandlerSingleton.getInstance().connect();
                Log.d(TAG, "Could not send message due to lack of internet connection");
            }
        }
    }
}
