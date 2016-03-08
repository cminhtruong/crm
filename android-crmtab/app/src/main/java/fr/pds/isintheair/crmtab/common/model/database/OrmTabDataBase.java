package fr.pds.isintheair.crmtab.common.model.database;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by user on 08/12/2015.
 */

@Database(name = OrmTabDataBase.DBNAME, version = OrmTabDataBase.DBVERSION)
public class OrmTabDataBase {
    public static final String DBNAME = "CRMTabDataBase";
    public static final int DBVERSION = 1;
}
