package ucalgary.stbig.com.ucalgary.web;

import android.content.Context;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;

import ucalgary.stbig.com.ucalgary.R;
import ucalgary.stbig.com.ucalgary.datos.Datos;
import ucalgary.stbig.com.ucalgary.http.HttpConnectionWeb;

/**
 * Created by helbert on 17/09/15.
 */
public class WebCourses {


    private HttpConnectionWeb cn;
    private Context context;
    private Datos data;

    public WebCourses(Context context){
        this.context=context;
        cn = new HttpConnectionWeb(context.getString(R.string.url_courses));
        data = new Datos(context);
    }

    public boolean getData() {


        try {

            String xml =  cn.connect();

            Log.i("curses to parse ", xml);

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(xml));
            int eventType = xpp.getEventType();
            String elementName= "";


            boolean status=false;

            String nid = "";
            String title= "";
            String descripcion = "";
            String code = "";
            String duration = "";
            String inicio = "";
            String fin = "";
            String contents = "";
            String state = "";
            String category_nid="";



            int count_data=0;



            while (eventType != XmlPullParser.END_DOCUMENT) {


                if (eventType == XmlPullParser.START_DOCUMENT) {

                } else if (eventType == XmlPullParser.END_DOCUMENT) {

                } else if (eventType == XmlPullParser.START_TAG) {
                    elementName = xpp.getName();
                } else if (eventType == XmlPullParser.TEXT) {

                    if(elementName.equals("nid") && nid.equals("")){
                        Log.i("course nid ", xpp.getText());
                        nid= xpp.getText();
                        count_data++;
                    }

                    if(elementName.equals("title") && title.equals("")){
                        Log.i("course title ", xpp.getText());
                        title= xpp.getText();
                        count_data++;
                    }

                    if(elementName.equals("description") && descripcion.equals("")){
                        Log.i("course description ", xpp.getText());
                        descripcion= xpp.getText();
                        count_data++;
                    }

                    if(elementName.equals("code") && code.equals("")){
                        Log.i("course code ", xpp.getText());
                        code= xpp.getText();
                        count_data++;
                    }

                    if(elementName.equals("duration") && duration.equals("")){
                        Log.i("course duration ", xpp.getText());
                        duration= xpp.getText();
                        count_data++;
                    }

                    if(elementName.equals("from") && inicio.equals("")){
                        Log.i("course from ", xpp.getText());
                        inicio= xpp.getText();
                        count_data++;
                    }

                    if(elementName.equals("to") && fin.equals("")){
                        Log.i("course to ", xpp.getText());
                        fin= xpp.getText();
                        count_data++;
                    }

                    if(elementName.equals("contents") && contents.equals("")){
                        Log.i("course contents ", xpp.getText());
                        contents= xpp.getText();
                        count_data++;
                    }

                    if(elementName.equals("category_nid") && category_nid.equals("")){
                        Log.i("course category_nid ", xpp.getText());
                        category_nid= xpp.getText();
                        count_data++;
                    }
                }
                else if (eventType == XmlPullParser.END_TAG){
                    elementName = xpp.getName();

                    if( elementName.equals("node") && count_data==9) {

                        data.registerCourses(nid, title, descripcion, code, duration, inicio, fin, contents, "0", category_nid);
                        nid = "";
                        title= "";
                        descripcion="";
                        code = "";
                        duration = "";
                        inicio = "";
                        fin = "";
                        contents = "";
                        state = "";
                        category_nid="";
                        count_data=0;
                    }

                }


                eventType = xpp.next();
            }

        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
        return true;

    }



}
