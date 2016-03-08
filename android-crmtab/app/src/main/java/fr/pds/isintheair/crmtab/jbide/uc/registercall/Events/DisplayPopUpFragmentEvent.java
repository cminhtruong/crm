package fr.pds.isintheair.crmtab.jbide.uc.registercall.Events;

import fr.pds.isintheair.crmtab.jbide.uc.registercall.database.entity.CallEndedEvent;

/**
 * Created by j-d on 09/01/2016.
 */
public class DisplayPopUpFragmentEvent {
    private CallEndedEvent callEndedEvent;

    public CallEndedEvent getCallEndedEvent() {
        return callEndedEvent;
    }

    public DisplayPopUpFragmentEvent(CallEndedEvent callEndedEvent) {

        this.callEndedEvent = callEndedEvent;
    }
}
