package fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.bus.event;

import fr.pds.isintheair.crmtab.common.model.database.entity.Contact;

/******************************************
 * Created by        : jdatour            *
 * Creation date     : 01/26/16           *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/

public class PhoneCallCutEvent {
    private Contact contact;

    public PhoneCallCutEvent(Contact contact) {
        this.contact = contact;
    }
}
