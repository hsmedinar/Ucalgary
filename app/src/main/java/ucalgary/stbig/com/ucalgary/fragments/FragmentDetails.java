package ucalgary.stbig.com.ucalgary.fragments;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import ucalgary.stbig.com.ucalgary.R;
import ucalgary.stbig.com.ucalgary.datos.AppPreferences;
import ucalgary.stbig.com.ucalgary.listeners.OnHomeListener;
import ucalgary.stbig.com.ucalgary.objects.Cursos;
import ucalgary.stbig.com.ucalgary.objects.Events;
import ucalgary.stbig.com.ucalgary.objects.News;
import ucalgary.stbig.com.ucalgary.objects.SSC;

/**
 * Created by helbert on 21/09/15.
 */
public class FragmentDetails  extends Fragment implements View.OnClickListener {

    private Context context;
    private Application application;
    private View view;
    private TextView title;
    private TextView body;
    private TextView contents;
    private Button suscripcion;
    private News news;
    private SSC ssc;
    private Cursos courses;
    private Events events;
    private TextView header;
    private Button link;
    private String urlPage="";


    int value;
    private LinearLayout btnback;
    private OnHomeListener mListener;
    private AppPreferences preferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = getActivity().getApplication();
        context = application.getApplicationContext();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.details_item, container, false);
        title = (TextView) view.findViewById(R.id.title);
        body = (TextView) view.findViewById(R.id.body);
        contents = (TextView) view.findViewById(R.id.content_course);
        suscripcion = (Button) view.findViewById(R.id.suscription);
        header =  (TextView) view.findViewById(R.id.header);
        btnback=  (LinearLayout) view.findViewById(R.id.back);
        link = (Button) view.findViewById(R.id.link);

        preferences = new AppPreferences(context);

        btnback.setOnClickListener(this);
        suscripcion.setOnClickListener(this);
        link.setOnClickListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnHomeListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void loadData() {
        value = (int) getArguments().getSerializable("type");

        switch(value){
            case 1:
                news= (News) getArguments().getSerializable("news");
                loadNews(news);
                break;
            case 2:
                ssc= (SSC) getArguments().getSerializable("scc");
                loadSCC(ssc);
                break;
            case 3:
                courses= (Cursos) getArguments().getSerializable("courses");
                loadCourses(courses);
                break;
            case 4:
                events= (Events) getArguments().getSerializable("events");
                loadEvents(events);
                break;
        }


    }

    private void loadNews(News news){
        if(news!=null){
            title.setText(news.getTitle());
            body.setText(String.valueOf(news.getContent()));
            suscripcion.setVisibility(View.GONE);
            urlPage=news.getField_Browser();
            header.setText("NEWS");
        }
    }

    private void loadSCC(SSC ssc){
        if(ssc!=null){
            title.setText(ssc.getTitle());
            body.setText(String.valueOf(ssc.getContent()));
            suscripcion.setVisibility(View.GONE);
            urlPage=ssc.getUrl();
            header.setText("SSC");

        }
    }


    private void loadCourses(Cursos cursos){
        if(cursos!=null){
            title.setText(cursos.getTitle());
            body.setText(cursos.getDescripcion());
            contents.setText(cursos.getDuracion() + "\r\n\n" + cursos.getInicio());
            suscripcion.setVisibility(View.VISIBLE);
            link.setVisibility(View.GONE);
            header.setText("COURSES");
        }
    }

    private void loadEvents(Events events){
        if(events!=null){
            title.setText(events.getTitle());
            body.setText(String.valueOf(events.getBody()));
            contents.setText(String.valueOf(events.getLocate()));
            suscripcion.setVisibility(View.GONE);
            header.setText("EVENTS");
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.back:
                if(value==1)
                    mListener.backNews();

                if(value==2)
                    mListener.backSSC();

                if(value==3)
                    mListener.backCursos();

                if(value==4)
                    mListener.backEvents();

                break;
            case R.id.suscription:
                preferences.saveValue("cid",courses.getNid());
                mListener.backSubscription();
                break;

            case R.id.link:
                if(!urlPage.toString().trim().equals("")){
                    Intent in  = new Intent(Intent.ACTION_VIEW, Uri.parse(urlPage));
                    startActivity(in);
                }


        }

    }

}
