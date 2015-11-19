package ucalgary.stbig.com.ucalgary.fragments;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.p_v.flexiblecalendar.FlexibleCalendarView;
import com.p_v.flexiblecalendar.entity.CalendarEvent;
import com.p_v.flexiblecalendar.view.BaseCellView;
import com.p_v.flexiblecalendar.view.SquareCellView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ucalgary.stbig.com.ucalgary.R;
import ucalgary.stbig.com.ucalgary.adapters.adapterEvents;
import ucalgary.stbig.com.ucalgary.datos.Datos;
import ucalgary.stbig.com.ucalgary.listeners.OnHomeListener;
import ucalgary.stbig.com.ucalgary.objects.Events;


/**
 * Created by helbert on 23/10/15.
 */
public class FragmentEvents extends Fragment implements FlexibleCalendarView.OnMonthChangeListener,
        FlexibleCalendarView.OnDateClickListener, AdapterView.OnItemClickListener  {

        private FlexibleCalendarView calendarView;
        private TextView someTextView;
        private TextView header;
        private ListView listaeventos;
        private ArrayList<String> fechas;
        private Datos data;
        private Context context;
        private Application application;
        private adapterEvents adapter;
        private OnHomeListener mListener;


        public FragmentEvents() {

        }


        @Override
        public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
            application = getActivity().getApplication();
            context = application.getApplicationContext();
         }

        @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        calendarView = (FlexibleCalendarView)view.findViewById(R.id.calendar_view);
        header = (TextView) view.findViewById(R.id.header);
        listaeventos = (ListView) view.findViewById(R.id.events);
        data = new Datos(context);

        listaeventos.setOnItemClickListener(this);

        fechas = data.listDateEvents();

        calendarView.setCalendarView(new FlexibleCalendarView.CalendarView() {
            @Override
            public BaseCellView getCellView(int position, View convertView, ViewGroup parent, @BaseCellView.CellType int cellType) {
                BaseCellView cellView = (BaseCellView) convertView;
                if (cellView == null) {
                    LayoutInflater inflater = LayoutInflater.from(getActivity());
                    cellView = (BaseCellView) inflater.inflate(R.layout.calendar_date_cell_view, null);
                }
                if (cellType == BaseCellView.OUTSIDE_MONTH) {
                    cellView.setTextColor(getResources().getColor(R.color.date_outside_month_text_color_activity_1));
                }

                return cellView;
            }

            @Override
            public BaseCellView getWeekdayCellView(int position, View convertView, ViewGroup parent) {
                BaseCellView cellView = (BaseCellView) convertView;
                if (cellView == null) {
                    LayoutInflater inflater = LayoutInflater.from(getActivity());
                    cellView = (SquareCellView) inflater.inflate(R.layout.calendar_week_cell_view, null);
                }

                return cellView;
            }

            @Override
            public String getDayOfWeekDisplayValue(int dayOfWeek, String defaultValue) {
                return String.valueOf(defaultValue.charAt(0));
            }
        });

        calendarView.setOnMonthChangeListener(this);
        calendarView.setOnDateClickListener(this);
        calendarView.setEventDataProvider(new FlexibleCalendarView.EventDataProvider() {
            @Override
            public List<CalendarEvent> getEventsForTheDay(int year, int month, int day) {

                String dayc = String.format("%02d" , day);
                String monthc = String.format("%02d" , month + 1);

                for (byte x = 0; x < fechas.size(); x++) {

                    String v = fechas.get(x);
                    String[] a = v.split("-");

                    if (v.trim().toString().equals(String.valueOf(year) + "-" + monthc + "-" + dayc)) {
                        List<CalendarEvent> eventColors = new ArrayList<>(3);
                        eventColors.add(new CalendarEvent(R.color.green_light));
                        eventColors.add(new CalendarEvent(R.color.pink_pressed));
                        eventColors.add(new CalendarEvent(R.color.blue_light));
                        return eventColors;
                    }


                }

                return null;
            }
        });

        return view;
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            updateTitle(calendarView.getSelectedDateItem().getYear(), calendarView.getSelectedDateItem().getMonth());
        }

        private void updateTitle(int year, int month){
            Calendar cal = Calendar.getInstance();
            cal.set(year, month, 1);
            header.setText(cal.getDisplayName(Calendar.MONTH, Calendar.LONG,
                    this.getResources().getConfiguration().locale) + " " + year);
        }

        @Override
        public void onMonthChange(int year, int month, int direction) {
            Calendar cal = Calendar.getInstance();
            cal.set(year, month, 1);
            updateTitle(year, month);
        }

        @Override
        public void onDateClick(int year, int month, int day) {


            String dayc = String.format("%02d" , day);
            String monthc = String.format("%02d", month + 1);
            loadEvents(String.valueOf(year) + "-" + monthc + "-" + dayc);

        }

        public static String getMonthShortName(int monthNumber) {
            String monthName="";

            if(monthNumber>=0 && monthNumber<12)
                try
                {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.MONTH, monthNumber);

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM");
                    simpleDateFormat.setCalendar(calendar);
                    monthName = simpleDateFormat.format(calendar.getTime());
                }
                catch (Exception e)
                {
                    if(e!=null)
                        e.printStackTrace();
                }
            return monthName;
        }


        private void loadEvents(String fecha){

            adapter = new adapterEvents(context,data.listEvents(fecha));
            listaeventos.setAdapter(adapter);
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
        Events n = (Events) adapter.getItem(position);
        mListener.selectedListerEvents(n);
    }


}
