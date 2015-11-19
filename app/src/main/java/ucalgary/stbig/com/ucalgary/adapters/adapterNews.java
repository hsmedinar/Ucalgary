package ucalgary.stbig.com.ucalgary.adapters;

import android.content.Context;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ucalgary.stbig.com.ucalgary.R;
import ucalgary.stbig.com.ucalgary.objects.News;

/**
 * Created by helbert on 18/09/15.
 */
public class adapterNews extends BaseAdapter {

    private ArrayList<News> news;
    private Context context;

    public adapterNews(Context context, ArrayList<News> news){
        this.news=news;
        this.context=context;
    }

    @Override
    public Object getItem(int position) {
        return news.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return news.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        view_holder row_view;
        final News n=(News) news.get(position);

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

        row_view.icon.setVisibility(View.GONE);

        if(position!=0){


        }

        if (position == 0) {
            row_view.bh.setVisibility(View.VISIBLE);
        }else{
            News fa = (News) news.get(position - 1);
            if (!n.getDate().equals(fa.getDate()))
                row_view.bh.setVisibility(View.VISIBLE);
            else
                row_view.bh.setVisibility(View.GONE);

        }

        int maxLength = 100;
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(maxLength);
        row_view.body.setFilters(fArray);

        row_view.header.setText(n.getDate());
        row_view.title.setText(n.getTitle());
        row_view.body.setText(n.getContent() + "...");

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
