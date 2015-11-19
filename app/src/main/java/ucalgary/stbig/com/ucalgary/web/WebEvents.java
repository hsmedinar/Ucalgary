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
 * Created by helbert on 22/10/15.
 */
public class WebEvents {


    private HttpConnectionWeb cn;
    private Context context;
    private Datos data;

    public WebEvents(Context context){
        this.context=context;
        cn = new HttpConnectionWeb(context.getString(R.string.url_events));
        data = new Datos(context);
    }

    public boolean getData() {


        try {

            String xml =  cn.connect();

            Log.i("events to parse ", xml);

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(xml));
            int eventType = xpp.getEventType();
            String elementName= "";


            boolean status=false;


            String title= "";
            String body = "";
            String date = "";
            String locate = "";
            String browser = "";

            int count_data=0;



            while (eventType != XmlPullParser.END_DOCUMENT) {


                if (eventType == XmlPullParser.START_DOCUMENT) {

                } else if (eventType == XmlPullParser.END_DOCUMENT) {

                } else if (eventType == XmlPullParser.START_TAG) {
                    elementName = xpp.getName();
                } else if (eventType == XmlPullParser.TEXT) {


                    if(elementName.equals("Title") && title.equals("")){
                        Log.i("title", xpp.getText());
                        title= xpp.getText();
                        count_data++;
                    }

                    if(elementName.equals("Body") && body.equals("")){
                        Log.i("body", xpp.getText());
                        body= xpp.getText();
                        count_data++;
                    }

                    if(elementName.equals("Event-date-day") && date.equals("")){
                        Log.i("date", xpp.getText());
                        date= xpp.getText();
                        count_data++;
                    }

                    if(elementName.equals("Location") && locate.equals("")){
                        Log.i("locate", xpp.getText());
                        locate= xpp.getText();
                        count_data++;
                    }

                    if(elementName.equals("Browser") && browser.equals("")){
                        Log.i("browser", xpp.getText());
                        browser= xpp.getText();
                        count_data++;
                    }


                }
                else if (eventType == XmlPullParser.END_TAG){
                    elementName = xpp.getName();

                    if( elementName.equals("node") && count_data==5) {
                        Log.i("respuesta", title);
                        data.registerEvents(title, body, date, locate, browser);
                        title= "";
                        body = "";
                        date = "";
                        locate = "";
                        browser = "";
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
