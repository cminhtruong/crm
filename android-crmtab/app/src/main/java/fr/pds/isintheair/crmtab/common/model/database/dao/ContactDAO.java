package fr.pds.isintheair.crmtab.common.model.database.dao;

import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

import fr.pds.isintheair.crmtab.common.model.database.entity.Contact;

/******************************************
 * Created by        : jdatour            *
 * Creation date     : 01/23/2016         *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/

public class ContactDAO {
    public static List<Contact> getAll() {
        return new Select().from(Contact.class).queryList();
    }
}
