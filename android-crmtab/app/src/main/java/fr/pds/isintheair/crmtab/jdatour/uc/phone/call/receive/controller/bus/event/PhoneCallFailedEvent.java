package fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.bus.event;

/******************************************
 * Created by        : jdatour            *
 * Creation date     : 01/23/16           *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/

public class PhoneCallFailedEvent {
    private String failReason;

    public PhoneCallFailedEvent(String failReason) {
        this.failReason = failReason;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }
}
