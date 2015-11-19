package ucalgary.stbig.com.ucalgary.fragments;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ucalgary.stbig.com.ucalgary.R;
import ucalgary.stbig.com.ucalgary.adapters.adapterListCourse;
import ucalgary.stbig.com.ucalgary.datos.AppPreferences;
import ucalgary.stbig.com.ucalgary.datos.Datos;
import ucalgary.stbig.com.ucalgary.objects.Cursos;
import ucalgary.stbig.com.ucalgary.web.WebSubscription;

/**
 * Created by helbert on 14/09/15.
 */
public class FragmentSuscription extends Fragment implements OnItemSelectedListener {

    private Context context;
    private Application application;
    private View view;
    private Spinner cursos;
    private TextView nombre;
    private TextView email;
    private TextView telefono;
    private adapterListCourse adapter;
    private Button save;
    private Datos data;
    private Cursos c;
    private String currentCourse;
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

        view = inflater.inflate(R.layout.fragment_suscription, container , false);

        cursos = (Spinner) view.findViewById(R.id.course);
        nombre = (TextView) view.findViewById(R.id.name);
        email = (TextView) view.findViewById(R.id.email);
        telefono = (TextView) view.findViewById(R.id.phone);
        save = (Button) view.findViewById(R.id.save);

        data = new Datos(context);
        preferences = new AppPreferences(context);

        cursos.setOnItemSelectedListener(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSusbcription(context.getString(R.string.url_suscription),currentCourse,nombre.getText().toString(),
                        telefono.getText().toString(),email.getText().toString());
            }
        });

        return view;

    }


    @Override
    public void onResume() {
        super.onResume();
        listCourses();
    }

    private void listCourses(){
        adapter = new adapterListCourse(context,data.listCoursesUnsubscribe());
        cursos.setAdapter(adapter);
        selectCourse(preferences.getValue("cid"));
    }

    @Override

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Cursos c = (Cursos) adapter.getItem(position);
        currentCourse=c.getNid();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void saveSusbcription(final String url,final String code,final String nombres,final String telefono,final String email){

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                WebSubscription ws = new WebSubscription(context,url);
                int result = ws.suscriptionCourse(code, nombres, telefono, email);
                Message msg = new Message();
                msg.obj = result;
                handler.sendMessage(msg);
            }
        });
        th.start();

    }

    private android.os.Handler  handler = new Handler(){
        public void handleMessage(Message msg) {
            //pb.setVisibility(View.GONE);

            Integer r =(Integer) Integer.parseInt(msg.obj.toString());

            if(r==1){
                clearFields();
                listCourses();
                Toast.makeText(context, context.getText(R.string.suscription_successful), Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, context.getText(R.string.suscription_fail), Toast.LENGTH_SHORT).show();
            }



        };
    };

    private void clearFields(){

        nombre.setText("");
        email.setText("");
        telefono.setText("");
        cursos.setSelection(0);
    }

    private void selectCourse(String idc){

        ArrayList<Cursos> lc = data.listCoursesUnsubscribe();

        for(byte x=0;x<=lc.size()-1;x++){
           Cursos sc = (Cursos) lc.get(x);
                if(sc.getNid().equals(idc)){
                    cursos.setSelection(x);
                    currentCourse=idc;
                }
        }
    }
}
