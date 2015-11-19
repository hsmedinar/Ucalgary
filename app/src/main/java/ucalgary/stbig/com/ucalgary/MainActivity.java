package ucalgary.stbig.com.ucalgary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import ucalgary.stbig.com.ucalgary.fragments.FragmentCourses;
import ucalgary.stbig.com.ucalgary.fragments.FragmentDetails;
import ucalgary.stbig.com.ucalgary.fragments.FragmentEvents;
import ucalgary.stbig.com.ucalgary.fragments.FragmentInfo;
import ucalgary.stbig.com.ucalgary.fragments.FragmentNews;
import ucalgary.stbig.com.ucalgary.fragments.FragmentSocial;
import ucalgary.stbig.com.ucalgary.fragments.FragmentSuscription;
import ucalgary.stbig.com.ucalgary.listeners.OnHomeListener;
import ucalgary.stbig.com.ucalgary.objects.Cursos;
import ucalgary.stbig.com.ucalgary.objects.Events;
import ucalgary.stbig.com.ucalgary.objects.News;
import ucalgary.stbig.com.ucalgary.objects.SSC;


public class MainActivity extends FragmentActivity implements View.OnClickListener, OnHomeListener {

    private LinearLayout news;
    private LinearLayout social;
    private LinearLayout course;
    private LinearLayout suscription;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_content);

        news = (LinearLayout) findViewById(R.id.news);
        social = (LinearLayout) findViewById(R.id.social);
        course = (LinearLayout) findViewById(R.id.course);
        suscription = (LinearLayout) findViewById(R.id.suscription);

        news.setOnClickListener(this);
        social.setOnClickListener(this);
        course.setOnClickListener(this);
        suscription.setOnClickListener(this);

        fragment = new FragmentInfo();
        loadFragment(fragment, R.id.content, "News");

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.news:
                fragment = new FragmentNews();
                loadFragment(fragment,R.id.content,"News");
                break;
            case R.id.social:
                fragment = new FragmentSocial();
                loadFragment(fragment,R.id.content,"Social");
                break;
            case R.id.course:
                fragment = new FragmentCourses();
                loadFragment(fragment,R.id.content,"Courses");
                break;
            case R.id.suscription:
                fragment = new FragmentSuscription();
                loadFragment(fragment,R.id.content,"Suscription");
                break;
        }
    }


    private void loadFragment(Fragment fm,int layoutContainerId, String title){

        FragmentManager mFragmentManager = getSupportFragmentManager();

        if (fm != null){
            mFragmentManager.beginTransaction().replace(layoutContainerId, fm).commit();
        }

        this.setTitle(title);

    }

    @Override
    public void selectedListerNews(News news) {
        Bundle bundle= new Bundle();
        bundle.putSerializable("news",news);
        bundle.putSerializable("type",1);
        Fragment fragment = new FragmentDetails();
        fragment.setArguments(bundle);
        loadFragment(fragment, R.id.content, "NEWS");
    }

    @Override
    public void selectedListerSSC(SSC scc) {
        Bundle bundle= new Bundle();
        bundle.putSerializable("scc",scc);
        bundle.putSerializable("type",2);
        Fragment fragment = new FragmentDetails();
        fragment.setArguments(bundle);
        loadFragment(fragment, R.id.content, "SSC");
    }

    @Override
    public void selectedListerCursos(Cursos courses) {
        Bundle bundle= new Bundle();
        bundle.putSerializable("courses",courses);
        bundle.putSerializable("type",3);
        Fragment fragment = new FragmentDetails();
        fragment.setArguments(bundle);
        loadFragment(fragment, R.id.content, "COURSES");

    }

    @Override
    public void selectedListerEvents(Events events) {
        Bundle bundle= new Bundle();
        bundle.putSerializable("events",events);
        bundle.putSerializable("type",4);
        Fragment fragment = new FragmentDetails();
        fragment.setArguments(bundle);
        loadFragment(fragment, R.id.content, "EVENTS");

    }

    @Override
    public void backNews() {
        fragment = new FragmentNews();
        loadFragment(fragment,R.id.content,"News");
    }

    @Override
    public void backSSC() {
        fragment = new FragmentSocial();
        loadFragment(fragment,R.id.content,"Social");
    }

    @Override
    public void backCursos() {
        fragment = new FragmentCourses();
       loadFragment(fragment,R.id.content,"Course");
    }

    @Override
    public void backEvents() {
        fragment = new FragmentEvents();
        loadFragment(fragment,R.id.content,"Events");
    }

    @Override
    public void backSubscription() {
        fragment = new FragmentSuscription();
        loadFragment(fragment,R.id.content,"Subscription");
    }

}
