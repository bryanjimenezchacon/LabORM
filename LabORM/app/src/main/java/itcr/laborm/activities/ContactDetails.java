package itcr.laborm.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import itcr.laborm.R;
import itcr.laborm.entities.Contact;
import java.sql.SQLException;

public class ContactDetails extends DatabaseActivity {

    Contact contact;
    TextView name;
    TextView phone;
    TextView email;
    Button btn_return;
    Button btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        name = (TextView) findViewById(R.id.tv_contact_name);
        phone = (TextView) findViewById(R.id.tv_contact_phone);
        email = (TextView) findViewById(R.id.tv_contact_email);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                contact = null;
            } else {
                contact = (Contact) extras.getSerializable("contact");
                updateTextViews();
            }
        } else {
            contact = (Contact) savedInstanceState.getSerializable("contact");
            updateTextViews();
        }

        btn_return = (Button) findViewById(R.id.btn_back);
        btn_delete = (Button) findViewById(R.id.btn_delete);

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteContact();
                finish();
            }
        });
    }

    private void updateTextViews() {
        try {
            contactDao = getContactDao();
            contactDao.queryForId(contact.getContactId());

            name.setText(contact.getName());
            phone.setText(contact.getPhone());
            email.setText(contact.getEmail());
        } catch (SQLException ex) {

        }
    }

    private void deleteContact() {
        try {
            contactDao = getContactDao();
            contactDao.deleteById(contact.getContactId());
            createToastMessage("Contact deleted successfully");
        } catch (SQLException ex) {

        }
    }
}
