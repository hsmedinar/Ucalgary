package ucalgary.stbig.com.ucalgary.adapters;

import android.content.Context;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ucalgary.stbig.com.ucalgary.R;
import ucalgary.stbig.com.ucalgary.objects.Events;

/**
 * Created by helbert on 26/10/15.
 */
public class adapterEvents  extends BaseAdapter {

    private ArrayList<Events> events;
    private Context context;

    public adapterEvents(Context context, ArrayList<Events> events){
        this.events=events;
        this.context=context;

    }

    @Override
    public Object getItem(int position) {
        return events.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        view_holder row_view;
        final Events n=(Events) events.get(position);

        if (convertView==null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_list_events, parent,false);

            row_view= new view_holder();
            row_view.title =(TextView)convertView.findViewById(R.id.title);
            row_view.body =(TextView)convertView.findViewById(R.id.body);
            convertView.setTag(row_view);

        }else{
            row_view=(view_holder)convertView.getTag();
        }




        int maxLength = 300;
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(maxLength);
        row_view.body.setFilters(fArray);

        row_view.title.setText(n.getLocate());
        row_view.body.setText(n.getTitle());

        return convertView;

    }

    private class view_holder{
        public TextView title;
        public TextView body;
    }
}