package itcr.laborm.activities;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import itcr.laborm.dbhelpers.DatabaseHelper;
import itcr.laborm.entities.Contact;


public abstract class DatabaseActivity extends AppCompatActivity {

    protected DatabaseHelper databaseHelper = null;
    protected Dao<Contact, Integer> contactDao;

    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    protected Dao<Contact, Integer> getContactDao() {
        try {
            contactDao = getHelper().getContactDao();
        } catch (SQLException ex) {

        }
        return contactDao;
    }

    protected void createToastMessage(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }
}
