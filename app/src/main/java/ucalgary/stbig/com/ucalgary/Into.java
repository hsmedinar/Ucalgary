package ucalgary.stbig.com.ucalgary;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.transition.Explode;
import android.view.Window;
import android.widget.Toast;

import ucalgary.stbig.com.ucalgary.web.WebCourses;
import ucalgary.stbig.com.ucalgary.web.WebEvents;
import ucalgary.stbig.com.ucalgary.web.WebNews;
import ucalgary.stbig.com.ucalgary.web.WebSsc;

/**
 * Created by helbert on 14/09/15.
 */
public class Into extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_splash);
        getWindow().setExitTransition(new Explode());

        if(checkNetwork()){
            loadData();
        }else{
            Toast.makeText(this,"Necesitas conexion de internet",Toast.LENGTH_LONG).show();
        }
    }

    private boolean checkNetwork(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void loadData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                WebNews wn = new WebNews(Into.this);
                boolean rn = wn.getData();

                WebSsc ws = new WebSsc(Into.this);
                boolean rs = ws.getData();



                WebCourses wc = new WebCourses(Into.this);
                boolean rc = wc.getData();

                Message msg = new Message();
                msg.obj=rc;
                responseWebNegociacion.sendMessage(msg);

            }
        }).start();


    }

    private Handler responseWebNegociacion = new Handler(){
        public void handleMessage(Message msg){

            if(Boolean.valueOf(msg.obj.toString())){
                Intent intent = new Intent(Into.this, MainActivity.class);
                startActivity(intent,
                        ActivityOptions
                                .makeSceneTransitionAnimation(Into.this).toBundle());
            }
        };
    };



}
