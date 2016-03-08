package fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.bus.event;

/******************************************
 * Created by        : jdatour            *
 * Creation date     : 01/23/16           *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/

public class PhoneCallReceivedEvent {
    String phoneNumber;

    public PhoneCallReceivedEvent(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
