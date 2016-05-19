package itcr.laborm.entities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import itcr.laborm.R;


public class RecordArrayAdapter extends ArrayAdapter<String> {

    private LayoutInflater inflater;
    private List records;
    private int resource;

    public RecordArrayAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.records = objects;
        this.resource = resource;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(resource, parent, false);
        }

        final Contact contact = (Contact) records.get(position);
        TextView tv = (TextView) convertView.findViewById(R.id.contact_name);
        tv.setText(contact.getName());

        return convertView;
    }
}
