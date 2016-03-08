package fr.pds.isintheair.crmtab.jbide.uc.registercall.database.dao;

import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

import fr.pds.isintheair.crmtab.common.model.database.entity.Contact;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.database.entity.CallEndedEvent;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.database.entity.CallEndedEvent_Table;

/**
 * Created by jbide on 22/01/2016.
 */
public class CallEndedDAO {
    public static List<CallEndedEvent> getAll() {
        return new Select().from(CallEndedEvent.class).queryList();
    }

    public static void delete(Long idcall) {
       new Delete().from(CallEndedEvent.class).where(CallEndedEvent_Table.id.eq(idcall)).query();
    }
}
