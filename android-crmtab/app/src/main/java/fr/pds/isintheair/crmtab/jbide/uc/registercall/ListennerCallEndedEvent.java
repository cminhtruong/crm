package fr.pds.isintheair.crmtab.jbide.uc.registercall;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import java.util.List;

import fr.pds.isintheair.crmtab.common.model.database.entity.Contact;
import fr.pds.isintheair.crmtab.common.view.activity.MainActivity;
import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.database.dao.CallEndedDAO;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.database.entity.CallEndedEvent;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Events.DisplayPopUpFragmentEvent;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Events.PendingLogEvent;


/**
 * Created by j-d on 21/12/2015.
 */
public class ListennerCallEndedEvent extends Service {

    private static final int notification_id = 10000;
    private NotificationManager mNM;
    private int numMessages;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        //registering service for events on the bus
        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Constants.getInstance().getCurrentBusInstance().register(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        // Cancel the persistent notification.
        //mNM.cancel(NOTIFICATION);

        // Tell the user we stopped.
        Toast.makeText(this, "CrvRetrofitService stopped", Toast.LENGTH_SHORT).show();
    }


    // This is the object that receives interactions from clients.  See
    // RemoteService for a more complete example.
    /*private final IBinder mBinder = new LocalBinder();

    /**
     * Class for clients to access.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with
     * IPC.
     */
    /*public class LocalBinder extends Binder {
        LocalService getService() {
            return LocalService.this;
        }
    }*/
    @Subscribe
    public void showPopup(CallEndedEvent event) {
        //if no popup displayed show
        if(!Constants.getInstance().isPopUpDisplayed()) {
            Constants.getInstance().setPopUpDisplayed(true);
            Constants.getInstance().getCurrentBusInstance().post(new DisplayPopUpFragmentEvent(event));
        }else{  //else add to job
            //add event to pending list
            event.save();
            //Constants.getInstance().getPendindList().add(event);
            //tell subscribers that list has been updated
            //Constants.getInstance().getCurrentBusInstance().post(new PendingLogEvent());
            notifyLocally();
        }
    }

    /**
     * Shows notifications if popup already displayed
     */
    public void notifyLocally() {

        //List<CallEndedEvent> liste = Constants.getInstance().getPendindList();
        List<CallEndedEvent> liste = CallEndedDAO.getAll();
        Log.v("size",String.valueOf(liste.size()));
        // Set the info for the views that show in the notification panel.
        NotificationCompat.Builder notification = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.logo)  // the status icon
                .setTicker("Nouvel appel à historiser")  // the status text
                        //.setWhen(System.currentTimeMillis())  // the time stamp
                .setContentTitle("CRM-TAB")  // the label of the entry
                .setContentText("Appels en attente d'enregistrement");


        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        // The PendingIntent to launch our activity if the user selects this notification
          // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, MainActivity.class);
        resultIntent.putExtra("msg","notification");
        //TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        //stackBuilder.addParentStack(NotificationView.class);
         // Adds the Intent that starts the Activity to the top of the stack
        //stackBuilder.addNextIntent(resultIntent);
        //PendingIntent contentIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        notification.setContentIntent(resultPendingIntent);

        notification.setNumber(++numMessages);
        String[] events = new String[liste.size()];
        for (int i=0; i < liste.size(); i++) {
            Contact co = (Contact) Contact.getNameFromNumber(liste.get(i).getIdcontact());
            if(co!=null)
                events[i] = liste.get(i).getDate() + ":" + co.getLastName()+" "+co.getFirstName() ;
            else
            events[i] = liste.get(i).getDate() + ":" + liste.get(i).getIdcontact();
        }
        // Sets a title for the Inbox in expanded layout
        inboxStyle.setBigContentTitle("Comptes-rendus d'appels à enregistrer");

        // Moves events into the expanded layout
        for (int i=0; i < events.length; i++) {
            inboxStyle.addLine(events[i]);
        }
        // Moves the expanded layout object into the notification object.
        notification.setStyle(inboxStyle);

        // Send the notification.
        mNM.notify(notification_id, notification.build());
    }

}