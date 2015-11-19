package ucalgary.stbig.com.ucalgary.http;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by helbert on 16/05/15.
 */
public class HttpConnectionWeb {

    private HttpURLConnection conexion;



    private URL url;
    private String TAG_HTTP = "HttpDebug";
    private String link;
    private String link_get;
    private HashMap<String, String> params;
    private String suscription;

    public HttpConnectionWeb(String link){
        try {
        this.url= new URL(link);
            link_get=link;
        params = new HashMap<>();

        } catch (MalformedURLException e) {
        	e.printStackTrace();
        }

    }


    public String connect() throws IOException {

        String linea;

        StringBuilder construye = new StringBuilder();

        try{

            conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("POST");

            InputStreamReader input = new InputStreamReader(conexion.getInputStream());
            BufferedReader buffer = new BufferedReader(input);

            Log.i(TAG_HTTP, String.valueOf(conexion.getResponseCode()));

            if(conexion.getResponseCode()== HttpURLConnection.HTTP_OK){

                while((linea=buffer.readLine()) !=null){
                    construye.append(linea);
                }

                return construye.toString();

            }

            return "";

        }catch(Exception e){
            Log.e(TAG_HTTP, e.getMessage());
            return "";
        }
    }



    public String connectParm() throws IOException {

        String linea;

        StringBuilder construye = new StringBuilder();

        try{
          URL urlget = new URL(link_get + getPostDataString(params));

            conexion = (HttpURLConnection) urlget.openConnection();
            conexion.setRequestMethod("GET");


            Log.i("param to send",getPostDataString(params));



            InputStreamReader input = new InputStreamReader(conexion.getInputStream());
            BufferedReader buffer = new BufferedReader(input);

            if(conexion.getResponseCode()==HttpURLConnection.HTTP_OK){

                while((linea=buffer.readLine()) !=null){
                    construye.append(linea);
                }

                return construye.toString();
            }

            return "";

        }catch(Exception e){
            Log.e(TAG_HTTP, e.getMessage());
            return "";
        }
    }




    public void AddParam(String name, String value) {
        params.put(name, value);
    }

    public void AddSingleParam(String body) {
        suscription=body;
    }

    private String getPostDataString(HashMap<String, String> paramsPost) throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : paramsPost.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }




}

