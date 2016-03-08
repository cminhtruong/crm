package fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.notification.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketConnectionHandler;
import de.tavendo.autobahn.WebSocketException;
import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.model.config.WebsocketConfig;
import fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.view.activity.ConsultProspect;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class NotificationIntentService extends IntentService {

    static final String TAG = NotificationIntentService.class.getSimpleName();

    private static final int NOTIFICATION_ID = 1;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     *
     */
    public NotificationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        start();
    }

    private void pushNotif(String message){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("Prospect").setSmallIcon(R.drawable.logo).setContentText(message).setAutoCancel(true).setVibrate(new long[]{1000, 1000, 1000, 1000, 1000}).setLights(Color.RED, 3000, 3000);
        Intent intent = new Intent(this, ConsultProspect.class);
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addParentStack(ConsultProspect.class);
        taskStackBuilder.addNextIntent(intent);
        PendingIntent resultPendingIntent = taskStackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // notificationID allows you to update the notification later on.
        mNotificationManager.notify(1, builder.build());
    }

    private final WebSocketConnection connection = new WebSocketConnection();

    private void start(){
        try {
            connection.connect(WebsocketConfig.WSURI, new WebSocketConnectionHandler(){
                @Override
                public void onOpen() {
                    super.onOpen();
                    Log.i(TAG, "I'm connect with the server");
                }

                @Override
                public void onClose(int code, String reason) {
                    super.onClose(code, reason);
                }

                @Override
                public void onTextMessage(String payload) {
                    super.onTextMessage(payload);
                    pushNotif(payload);
                }
            });
        } catch (WebSocketException e) {
            e.printStackTrace();
        }
    }
}
