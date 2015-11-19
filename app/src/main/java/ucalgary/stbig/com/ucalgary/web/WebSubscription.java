package ucalgary.stbig.com.ucalgary.web;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import ucalgary.stbig.com.ucalgary.R;
import ucalgary.stbig.com.ucalgary.datos.Datos;
import ucalgary.stbig.com.ucalgary.http.HttpConnectionWeb;

/**
 * Created by helbert on 07/11/15.
 */
public class WebSubscription {

    private Context contexto;
    private HttpConnectionWeb cn;
    private JSONObject jObj;
    private Datos data;

    private static final String TAG_MAIN = "result";
    private static final String TAG_MSG = "msj";
    private static final String TAG_DATA = "data";

    public WebSubscription(Context contexto, String url){
        this.contexto=contexto;
        cn = new HttpConnectionWeb(url);
        data = new Datos(contexto);
    }


    public int suscriptionCourse(String curso, String nombre, String telefono, String email){


        try {

            cn.AddParam(contexto.getString(R.string.field_email), email);
            cn.AddParam(contexto.getString(R.string.field_cell), telefono);
            cn.AddParam(contexto.getString(R.string.field_name), nombre);
            cn.AddParam(contexto.getString(R.string.field_id), curso);

            String json = cn.connectParm();

            Log.i("response ", json);

            if(!json.isEmpty()){
                jObj = new JSONObject(json);
                data.updateSuscriptionCourse(curso);
            }

            return 1;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.i("exception ", e.getMessage());
            e.printStackTrace();
            return 0;
        }

    }

}