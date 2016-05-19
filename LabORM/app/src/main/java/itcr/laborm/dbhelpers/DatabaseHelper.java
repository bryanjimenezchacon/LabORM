package itcr.laborm.dbhelpers;

import android.content.Context;

import java.sql.Connection;
import java.sql.SQLException;

import android.content.pm.PackageInfo;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import itcr.laborm.R;
import itcr.laborm.entities.Contact;


public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "agenda.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Contact, Integer> contactDao;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Contact.class);
        } catch(SQLException ex) {
            Log.e(DatabaseHelper.class.getName(), "Unable to create tables", ex);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource, int oldVer, int newVer) {
        try {
            TableUtils.dropTable(connectionSource, Contact.class, true);
            onCreate(sqliteDatabase, connectionSource);
        } catch(SQLException ex) {
            Log.e(DatabaseHelper.class.getName(), "Unable to create tables", ex);
        }
    }

    public Dao<Contact, Integer> getContactDao() throws SQLException {
        if (contactDao == null) {
            contactDao = getDao(Contact.class);
        }
        return contactDao;
    }

}
