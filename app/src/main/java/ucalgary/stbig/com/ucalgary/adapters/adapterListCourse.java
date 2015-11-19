package ucalgary.stbig.com.ucalgary.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ucalgary.stbig.com.ucalgary.R;
import ucalgary.stbig.com.ucalgary.objects.Cursos;

/**
 * Created by helbert on 07/11/15.
 */
public class adapterListCourse  extends BaseAdapter {

    private ArrayList<Cursos> cursos;
    private Context context;
    private Drawable icon;

    public adapterListCourse(Context context, ArrayList<Cursos> cursos){
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


        final Cursos c=(Cursos) cursos.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.row_spinner_course, parent,false);

        TextView co =(TextView)convertView.findViewById(R.id.course);

        co.setText(c.getTitle());

        return convertView;
    }

}
