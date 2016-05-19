package itcr.laborm.entities;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;


public class Contact implements Serializable{

    @DatabaseField(generatedId = true, columnName = "contact_id")
    public int contactId;

    @DatabaseField(columnName = "name")
    private String name;

    @DatabaseField(columnName = "phone")
    private String phone;

    @DatabaseField(columnName = "email")
    private String email;

    public Contact(){}

    public Contact(final String name, final String phone, final String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
