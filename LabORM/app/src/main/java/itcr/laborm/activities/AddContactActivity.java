package itcr.laborm.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import itcr.laborm.R;
import itcr.laborm.entities.Contact;
import java.sql.SQLException;

public class AddContactActivity extends DatabaseActivity {

    Button add_button;
    Button return_button;
    EditText ed_name;
    EditText ed_phone;
    EditText ed_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        ed_name = (EditText) findViewById(R.id.ed_name);
        ed_phone = (EditText) findViewById(R.id.ed_phone);
        ed_email = (EditText) findViewById(R.id.ed_email);
        add_button = (Button) findViewById(R.id.btn_add_contact);
        return_button = (Button) findViewById(R.id.btn_back);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateFields()) {
                    addContact();
                } else {
                    createToastMessage("All fields are mandatory");
                }
            }
        });

        return_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void addContact() {
        final Contact contact = new Contact();

        contact.setName(ed_name.getText().toString());
        contact.setPhone(ed_phone.getText().toString());
        contact.setEmail(ed_email.getText().toString());

        try {
            contactDao = getContactDao();
            contactDao.create(contact);
            createToastMessage("Contact created successfully.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private boolean validateFields() {
        boolean validationResult = false;

        if(ed_name.getText().toString().trim().length() > 0
                && ed_phone.toString().trim().length() > 0
                && ed_email.getText().toString().length() > 0) {
            validationResult = true;
        }

        return validationResult;
    }
}
