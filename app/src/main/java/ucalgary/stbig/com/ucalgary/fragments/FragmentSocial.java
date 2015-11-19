package ucalgary.stbig.com.ucalgary.fragments;

import android.app.Activity;
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
import android.widget.ListView;

import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;
import ucalgary.stbig.com.ucalgary.R;

import ucalgary.stbig.com.ucalgary.adapters.adapterScc;
import ucalgary.stbig.com.ucalgary.datos.Datos;
import ucalgary.stbig.com.ucalgary.listeners.OnHomeListener;
import ucalgary.stbig.com.ucalgary.objects.SSC;
import ucalgary.stbig.com.ucalgary.web.WebSsc;

/**
 * Created by helbert on 14/09/15.
 */
public class FragmentSocial  extends Fragment implements AdapterView.OnItemClickListener   {
    private Context context;
    private Application application;
    private View view;
    private adapterScc adapter;
    private Datos data;
    private ListView lstscc;
    private OnHomeListener mListener;
    private WaveSwipeRefreshLayout mWaveswipe;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = getActivity().getApplication();
        context = application.getApplicationContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_social, container, false);
        lstscc = (ListView) view.findViewById(R.id.social);
        data = new Datos(context);
        lstscc.setOnItemClickListener(this);

        mWaveswipe = (WaveSwipeRefreshLayout) view.findViewById(R.id.main_swipe);
        mWaveswipe.setWaveRGBColor(170,0,0);
        mWaveswipe.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Do work to refresh the list here.
                loadData();
            }
        });

        return view;
    }

    private void loadData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                WebSsc ws = new WebSsc(context);
                boolean rs = ws.getData();

                Message msg = new Message();
                msg.obj=rs;
                responseWebNegociacion.sendMessage(msg);

            }
        }).start();

    }

    private Handler responseWebNegociacion = new Handler(){
        public void handleMessage(Message msg){
            mWaveswipe.setRefreshing(false);
            if(Boolean.valueOf(msg.obj.toString())){
                adapter = new adapterScc(context,data.listSsc());
                lstscc.setAdapter(adapter);
            }
        };
    };

    @Override
    public void onResume() {
        super.onResume();

        adapter = new adapterScc(context,data.listSsc());
        lstscc.setAdapter(adapter);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnHomeListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SSC s = (SSC) adapter.getItem(position);
        mListener.selectedListerSSC(s);
    }

}
