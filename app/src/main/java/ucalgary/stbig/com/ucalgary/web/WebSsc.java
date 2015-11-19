package ucalgary.stbig.com.ucalgary.web;

import android.content.Context;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;

import ucalgary.stbig.com.ucalgary.R;
import ucalgary.stbig.com.ucalgary.datos.Datos;
import ucalgary.stbig.com.ucalgary.http.HttpConnectionWeb;

/**
 * Created by helbert on 17/09/15.
 */
public class WebSsc {

    private HttpConnectionWeb cn;
    private Context context;
    private Datos data;

    public WebSsc(Context context){
        this.context=context;
        cn = new HttpConnectionWeb(context.getString(R.string.url_ssc));
        data = new Datos(context);
    }


    public boolean getData()  {

        try {

            String xml =  cn.connect();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(xml));
            int eventType = xpp.getEventType();
            String elementName= "";


            boolean status=false;

            String Title= "";
            String Content = "";
            String Browser = "";
            String Date = "";
            String Type = "";
            int count_data=0;



            while (eventType != XmlPullParser.END_DOCUMENT) {

                if (eventType == XmlPullParser.START_DOCUMENT) {

                } else if (eventType == XmlPullParser.END_DOCUMENT) {

                } else if (eventType == XmlPullParser.START_TAG) {
                    elementName = xpp.getName();

                } else if (eventType == XmlPullParser.TEXT) {
                    if(elementName.equals("Title") && Title.equals("")){
                        Title= xpp.getText();
                        count_data++;
                    }
                    if(elementName.equals("Body") && Content.equals("")){
                        Content= xpp.getText();
                        count_data++;
                    }

                    if(elementName.equals("Browser") && Browser.equals("")){
                        Browser= xpp.getText();
                        count_data++;
                    }

                    if(elementName.equals("Post_date") && Date.equals("")){
                        Date= xpp.getText();
                        count_data++;
                    }

                    if(elementName.equals("Social") && Type.equals("")){
                        Type= xpp.getText();
                        count_data++;
                    }


                }
                else if (eventType == XmlPullParser.END_TAG )
                {
                    elementName = xpp.getName();
                    if( elementName.equals("node") && count_data==5) {

                        data.registerSSC(Title, Content, Browser, Date, "", Type);

                        Title= "";
                        Content = "";
                        Browser = "";
                        Date= "";
                        Type="";
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
