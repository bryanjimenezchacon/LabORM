package itcr.laborm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import itcr.laborm.R;
import itcr.laborm.entities.Contact;
import itcr.laborm.entities.RecordArrayAdapter;
import java.sql.SQLException;
import java.util.List;

public class MainActivity extends DatabaseActivity {

    private Button btn_nav_new_contact;
    private ListView listview;
    private List<Contact> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_nav_new_contact = (Button) findViewById(R.id.btn_nav_new_contact);

        btn_nav_new_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddContactActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        listview = (ListView) findViewById(R.id.contacts_list_view);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position >= 0) {
                    Intent intent = new Intent(getApplicationContext(), ContactDetails.class);
                    intent.putExtra("contact", contactList.get(position));
                    startActivityForResult(intent, 0);
                }
            }
        });

        populateListView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        populateListView();
    }

    private void populateListView() {
        try {
            contactList = getContactDao().queryForAll();
            listview.setAdapter(new RecordArrayAdapter(this, R.layout.list_item, contactList));
        } catch (SQLException ex) {

        }
    }
}
