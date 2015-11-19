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
public class WebNews {

    private HttpConnectionWeb cn;
    private Context context;
    private Datos data;

    public WebNews(Context context){
        this.context=context;
        cn = new HttpConnectionWeb(context.getString(R.string.url_news));
        data = new Datos(context);
    }


    public boolean getData() {


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
            String Date = "";
            String Field_Browser = "";
            int count_data=0;



            while (eventType != XmlPullParser.END_DOCUMENT) {

                if (eventType == XmlPullParser.START_DOCUMENT) {

                } else if (eventType == XmlPullParser.START_TAG) {
                    elementName = xpp.getName();

                } else if (eventType == XmlPullParser.TEXT) {
                    if(elementName.equals("Title") && Title.equals(""))
                    {
                        Title= xpp.getText();
                        count_data++;
                    }
                    if(elementName.equals("Body") && Content.equals(""))
                    {
                        Content= xpp.getText();
                        count_data++;
                    }

                    if(elementName.equals("Event-date") && Date.equals(""))
                    {
                        //SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                        //Date= formatoDelTexto.format(xpp.getText().toString());
                        Date= xpp.getText().substring(5, 21);
                        count_data++;
                    }

                    if(elementName.equals("field_browser_value") && Field_Browser.equals(""))
                    {
                        Field_Browser= xpp.getText();
                        count_data++;
                    }

                }
                else if (eventType == XmlPullParser.END_TAG )
                {
                    elementName = xpp.getName();
                    if( elementName.equals("node") && count_data>=3) {

                        data.registerNews(Title,Content,Date,"",Field_Browser);
                        //result.add(new News(Title,Content,Date,Field_Browser));
                        Title= "";
                        Content = "";
                        Date = "";
                        Field_Browser = "";
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
