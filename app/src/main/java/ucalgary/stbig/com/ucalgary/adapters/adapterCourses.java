package ucalgary.stbig.com.ucalgary.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ucalgary.stbig.com.ucalgary.R;
import ucalgary.stbig.com.ucalgary.objects.Cursos;

/**
 * Created by helbert on 21/09/15.
 */
public class adapterCourses  extends BaseAdapter {

    private ArrayList<Cursos> cursos;
    private Context context;
    private Drawable icon;

    public adapterCourses(Context context, ArrayList<Cursos> cursos){
        this.cursos=cursos;
        this.context=context;
        icon = context.getDrawable(R.drawable.ic_courses);
    }

    @Override
    public Object getItem(int position) {
        return cursos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return cursos.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        view_holder row_view;
        final Cursos c=(Cursos) cursos.get(position);

        if (convertView==null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_list, parent,false);

            row_view= new view_holder();
            row_view.header =(TextView)convertView.findViewById(R.id.header);
            row_view.title =(TextView)convertView.findViewById(R.id.title);
            row_view.body =(TextView)convertView.findViewById(R.id.body);

            row_view.content =(TextView)convertView.findViewById(R.id.content_course);
            row_view.suscription =(Button)convertView.findViewById(R.id.suscription);


            row_view.icon=(ImageView)  convertView.findViewById(R.id.icon);
            row_view.bh=(LinearLayout)  convertView.findViewById(R.id.backgroundHeader);
            convertView.setTag(row_view);

        }else{
            row_view=(view_holder)convertView.getTag();
        }

        //Log.i("contenido curso", c.getContents());

        row_view.icon.setBackground(icon);
        row_view.header.setText(c.getInicio());
        row_view.title.setText(c.getTitle());
        row_view.body.setText(c.getDescripcion());

        row_view.icon.setVisibility(View.VISIBLE);


        return convertView;
    }


    private class view_holder{
        public TextView header;
        public TextView title;
        public TextView body;
        public TextView content;
        public Button suscription;
        public ImageView icon;
        public View news_row;
        public TextView visit_var;
        public LinearLayout bh;
    }
}
