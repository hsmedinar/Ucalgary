package ucalgary.stbig.com.ucalgary.fragments;

import android.app.Application;
import android.content.Context;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.lang.reflect.Method;

import ucalgary.stbig.com.ucalgary.R;

/**
 * Created by helbert on 05/10/15.
 */
public class FragmentAppointment extends Fragment {

    private Context context;
    private Application application;
    private View view;
    private WebView webview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        application = getActivity().getApplication();
        context = application.getApplicationContext();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_login, container, false);
        webview = (WebView) view.findViewById(R.id.weburl);

        webview.setWebViewClient(new WebViewClient(){

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                // TODO Auto-generated method stub
                Log.e("onReceivedSslError", "errorCode " + errorCode + " description:" + "failingUrl");

                super.onReceivedError(view, errorCode, description, failingUrl);
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // Log.i(TAG, "Processing webview url click...");
                view.loadUrl(url);
                return true;
            }

            public void onReceivedSslError (WebView view,  SslErrorHandler handler, SslError error) {
                Log.e("onReceivedSslError",error.toString());
                handler.proceed() ;

            }

        });

        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setSupportZoom(true);
        webview.getSettings().setLoadsImagesAutomatically(true);
        webview.getSettings().setJavaScriptEnabled(true);

        //webview.setInitialScale(100);
        webview.setInitialScale(1);
        webview.getSettings().setBuiltInZoomControls(true);
        try {
            Method m = webview.getClass().getMethod("enableMultiTouch",
                    null);
            if (m != null) {
                m.invoke(webview, null);
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }

        try {
            Method m = webview.getClass().getMethod(
                    "enableMultiTouchTextRelow", boolean.class);
            if (m != null) {
                m.invoke(webview, false);
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        try {

            Method m = webview.getClass().getMethod(
                    "setIsCacheDrawBitmap", boolean.class);
            if (m != null) {
                m.invoke(webview, false);
            }
        } catch (Exception e) {
            // e.printStackTrace();
        }

        // Must for HTC EVO to do text reflow after double tap
        try {
            Method m = webview.getClass().getMethod("enableSmartZoom",
                    null);
            if (m != null) {
                m.invoke(webview, null);
            }
        } catch (Exception e) {
            // e.printStackTrace();
        }

        webview.loadUrl(getString(R.string.appointments_url));

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
