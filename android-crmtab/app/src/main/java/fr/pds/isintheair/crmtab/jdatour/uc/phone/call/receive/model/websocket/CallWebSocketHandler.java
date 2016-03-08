package fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.websocket;

import android.util.Log;

import de.tavendo.autobahn.WebSocketConnectionHandler;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.MessageController;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.helper.JSONHelper;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.entity.Message;

public class CallWebSocketHandler extends WebSocketConnectionHandler {
    private String TAG = getClass().getSimpleName();

    @Override
    public void onOpen() {
        Log.d(TAG, "Session opened");

        MessageController.sendRegisterMessage();
    }

    @Override
    public void onClose(int code, String reason) {
        Log.d(TAG, "Session closed with code : " + code + " with reason : " + reason);
        WebSocketConnectionHandlerSingleton.getInstance().connect();
    }

    @Override
    public void onTextMessage(String payload) {
        Log.d(TAG, "Message received : " + payload);

        if (!payload.isEmpty())
            MessageController.handleMessage((Message) JSONHelper.deserialize(payload, Message.class));
    }
}
