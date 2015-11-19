package ucalgary.stbig.com.ucalgary.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ucalgary.stbig.com.ucalgary.R;
import ucalgary.stbig.com.ucalgary.objects.SSC;

/**
 * Created by helbert on 20/09/15.
 */
public class adapterScc  extends BaseAdapter {

    private ArrayList<SSC> sccs;
    private Context context;
    private Drawable icon_twitter;
    private Drawable icon_facebook;

    public adapterScc(Context context, ArrayList<SSC> sccs){
        this.sccs=sccs;
        this.context=context;
        icon_twitter = context.getDrawable(R.drawable.twitter);
        icon_facebook = context.getDrawable(R.drawable.facebook);
    }

    @Override
    public Object getItem(int position) {
        return sccs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return sccs.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        view_holder row_view;
        final SSC n=(SSC) sccs.get(position);

        if (convertView==null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_list, parent,false);

            row_view= new view_holder();
            row_view.header =(TextView)convertView.findViewById(R.id.header);
            row_view.title =(TextView)convertView.findViewById(R.id.title);
            row_view.body =(TextView)convertView.findViewById(R.id.body);
            row_view.icon=(ImageView)  convertView.findViewById(R.id.icon);
            row_view.bh=(LinearLayout)  convertView.findViewById(R.id.backgroundHeader);
            convertView.setTag(row_view);

        }else{
            row_view=(view_holder)convertView.getTag();
        }


        if (position == 0) {
            row_view.bh.setVisibility(View.VISIBLE);
        }else{
            SSC fa = (SSC) sccs.get(position - 1);
            if (!n.getDate().equals(fa.getDate()))
                row_view.bh.setVisibility(View.VISIBLE);
            else
                row_view.bh.setVisibility(View.GONE);

        }

        int maxLength = 300;
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(maxLength);
        row_view.body.setFilters(fArray);


        row_view.header.setText(n.getDate());
        row_view.title.setText(n.getTitle());
        row_view.body.setText(n.getContent());

        row_view.icon.setVisibility(View.VISIBLE);

        Log.i("typew",n.getType());

        if(n.getType().toString().trim().equals("twitter"))
            row_view.icon.setBackground(icon_twitter);


        if (n.getType().toString().trim().equals("facebook"))
            row_view.icon.setBackground(icon_facebook);;



        return convertView;
    }


    private class view_holder{
        public TextView header;
        public TextView title;
        public TextView body;
        public ImageView icon;
        public View news_row;
        public TextView visit_var;
        public LinearLayout bh;
    }
}
