package fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.Select;
import com.squareup.otto.Subscribe;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.common.model.database.entity.Contact;
import fr.pds.isintheair.crmtab.common.model.database.entity.Contact_Table;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.database.entity.CallEndedEvent;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.enums.CallType;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.MessageController;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.bus.BusHandlerSingleton;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.bus.event.PhoneCallEndedEvent;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.bus.event.PhoneCallFailedEvent;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.bus.event.PhoneCallHookedEvent;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.model.enumeration.MessageType;

public class CallActivity extends Activity {
    @Bind(R.id.phone_state_textview)
    TextView phoneStateTextview;

    @Bind(R.id.timer_textview)
    TextView timerTextview;

    private long        callDuration;
    private Contact     currentContact;
    private MessageType currentMessageType;
    private String      currentPhoneNumber;
    private long        startCallTime;

    private String TAG = getClass().getSimpleName();

    @OnClick(R.id.phone_imageview)
    public void onPhoneClick() {
        MessageController.sendEndCallMessage();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Activity created");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        ButterKnife.bind(this);
        BusHandlerSingleton.getInstance().getBus().register(this);

        String phoneStateMessage = null;

        currentMessageType = (MessageType) getIntent().getSerializableExtra("messageType");
        currentPhoneNumber = getIntent().getStringExtra("phoneNumber");
        currentContact = new Select().from(Contact.class).where(Contact_Table.phoneNumber.is(currentPhoneNumber)).querySingle();

        if (currentContact != null) {
            String fullContactName = currentContact.getFirstName() + " " + currentContact.getLastName();
            phoneStateMessage = "Appel de " + fullContactName + " en cours...";
        }

        else {
            phoneStateMessage = "Appel de " + currentPhoneNumber + " en cours...";
        }

        phoneStateTextview.setText(phoneStateMessage);
    }

    private void updateTimer() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                callDuration = System.currentTimeMillis() - startCallTime;
                int seconds = (int) (callDuration / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;

                timerTextview.setText(String.format("%d:%02d", minutes, seconds));
            }
        });
    }

    @Subscribe
    public void onPhoneCallEndedEvent(PhoneCallEndedEvent phoneCallEndedEvent) {
        CallType callType = null;

        switch (currentMessageType) {
            case CALL_PASSED:
                callType = CallType.OUTGOING;
                break;
            case CALL_RECEIVED:
                callType = CallType.INCOMING;
        }

        CallEndedEvent callEndedEvent = new CallEndedEvent(callType,
                                                           Calendar.getInstance().toString(),
                                                           Long.toString(callDuration / 1000),
                                                           currentPhoneNumber);

        BusHandlerSingleton.getInstance().getBus().post(callEndedEvent);
        finish();
    }

    @Subscribe
    public void onPhoneCallFailedEvent(PhoneCallFailedEvent phoneCallFailedEvent) {
        Toast.makeText(this, "Impossible de passer un appel : " + phoneCallFailedEvent.getFailReason(), Toast.LENGTH_LONG).show();
        finish();
    }

    @Subscribe
    public void onCallHooked(PhoneCallHookedEvent phoneCallHookedEvent) {
        Log.d(TAG, "Call hooked event");

        phoneStateTextview.setText("Appel décroché");
        timerTextview.setVisibility(View.VISIBLE);

        startCallTime = System.currentTimeMillis();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateTimer();
            }
        }, 0, 500);
    }
}
