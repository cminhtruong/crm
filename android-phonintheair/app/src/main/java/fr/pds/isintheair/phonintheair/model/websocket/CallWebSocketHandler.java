package fr.pds.isintheair.phonintheair.model.websocket;

import android.util.Log;

import de.tavendo.autobahn.WebSocketConnectionHandler;
import fr.pds.isintheair.phonintheair.controller.MessageController;
import fr.pds.isintheair.phonintheair.helper.JSONHelper;
import fr.pds.isintheair.phonintheair.helper.SharedPreferencesHelper;
import fr.pds.isintheair.phonintheair.model.entity.Message;

public class CallWebSocketHandler extends WebSocketConnectionHandler {
    private String TAG = getClass().getSimpleName();

    @Override
    public void onOpen() {
        Log.d(TAG, "Session opened");

        Integer userId = SharedPreferencesHelper.readInteger("userId", 0);

        MessageController.sendRegisterMessage(userId);
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

