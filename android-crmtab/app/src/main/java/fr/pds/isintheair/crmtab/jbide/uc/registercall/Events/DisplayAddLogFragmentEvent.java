package fr.pds.isintheair.crmtab.jbide.uc.registercall.Events;

import fr.pds.isintheair.crmtab.jbide.uc.registercall.database.entity.CallEndedEvent;

/**
 * Created by jbide on 24/01/2016.
 */
public class DisplayAddLogFragmentEvent {

    private CallEndedEvent callEndedEvent;

    public CallEndedEvent getCallEndedEvent() {
        return callEndedEvent;
    }

    public DisplayAddLogFragmentEvent(CallEndedEvent callEndedEvent) {

        this.callEndedEvent = callEndedEvent;
    }
}
