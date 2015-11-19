package ucalgary.stbig.com.ucalgary.datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import ucalgary.stbig.com.ucalgary.R;
import ucalgary.stbig.com.ucalgary.objects.Cursos;
import ucalgary.stbig.com.ucalgary.objects.Events;
import ucalgary.stbig.com.ucalgary.objects.News;
import ucalgary.stbig.com.ucalgary.objects.SSC;


public class Datos extends SQLiteOpenHelper {

	private static final String CREATE_NEWS = "CREATE TABLE news(title TEXT,content TEXT,date TEXT,time_stamp TEXT,field_browser TEXT);";
	private static final String CREATE_SSC = "CREATE TABLE ssc(title TEXT,content TEXT,url TEXT,date TEXT,time_stamp TEXT,type TEXT);";
	private static final String CREATE_COURSES = "CREATE TABLE courses(nid TEXT, title TEXT,descripcion TEXT,code TEXT,duration TEXT,inicio TEXT,fin TEXT,contents TEXT,state_suscription TEXT, category_nid TEXT);";
	private static final String CREATE_EVENTS = "CREATE TABLE events(title TEXT, body TEXT,date TEXT,locate TEXT,browser TEXT);";


	private SQLiteDatabase db;
	private Context context;	
	    
	public Datos(Context context) {
		super(context, context.getString(R.string.database), null, 1);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		db.beginTransaction();
		
		try {
			db.execSQL(CREATE_NEWS);
			db.execSQL(CREATE_SSC);
			db.execSQL(CREATE_COURSES);
			db.execSQL(CREATE_EVENTS);
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			throw e;
		} finally {
			db.endTransaction();
		}
	
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub		
	}
	


	public void registerNews(String title, String content, String date, String time_stamp, String field_browser) throws SQLException {

		try {
			db = getReadableDatabase();       
    
				if(checkRegister("news","title",title)==0){
					ContentValues values = new ContentValues();   
					values.put("title", title);
					values.put("content", content);
					values.put("date", date);
					values.put("time_stamp", time_stamp);
					values.put("field_browser", field_browser);
			        db.insert("news", null, values);
			    }else{
			    	String[] args = {String.valueOf(title)};
			    	ContentValues values = new ContentValues();
					values.put("content", content);
					values.put("date", date);
					values.put("time_stamp", time_stamp);
                    values.put("field_browser", field_browser);
					db.update("news", values, "title" + "=?", args);
			    }
	
		    db.close();
		
			} catch (SQLException e) {
				
			}
	}


	public ArrayList<News> listNews() {
		ArrayList<News> values = new ArrayList<News>();
		db = getReadableDatabase();
		Cursor c = db.rawQuery("select * from news order by date asc", null);
		if (c.getCount() != 0) {
			c.moveToFirst();
			do {
				News data = new News(c.getString(0), c.getString(1), c.getString(2), c.getString(3));
				values.add(data);
			} while (c.moveToNext());
		}
		db.close();
		c.close();
		return values;
	}


    public void registerSSC(String title, String content, String  url, String  date, String  time_stamp, String  type) throws SQLException {

        try {
            db = getReadableDatabase();

            if(checkRegister("ssc","title",title)==0){
                ContentValues values = new ContentValues();
                values.put("title", title);
                values.put("content", content);
				values.put("url", url);
                values.put("date", date);
                values.put("time_stamp", time_stamp);
                values.put("type", type);
                db.insert("ssc", null, values);
            }else{
                String[] args = {String.valueOf(title)};
                ContentValues values = new ContentValues();
                values.put("content", content);
				values.put("url", url);
                values.put("date", date);
                values.put("time_stamp", time_stamp);
                values.put("type", type);
                db.update("ssc", values, "title" + "=?", args);
            }

            db.close();

        } catch (SQLException e) {

        }
    }


	public ArrayList<SSC> listSsc() {
		ArrayList<SSC> values = new ArrayList<SSC>();
		db = getReadableDatabase();
		Cursor c = db.rawQuery("select * from ssc", null);
		if (c.getCount() != 0) {
			c.moveToFirst();
			do {
				SSC data = new SSC(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(5));
				values.add(data);
			} while (c.moveToNext());
		}
		db.close();
		c.close();
		return values;
	}

    public void registerCourses(String nid ,String  title ,String  descripcion , String code ,String duration, String inicio, String fin ,String contents ,String state_suscription, String category_nid) throws SQLException {

        try {
            db = getReadableDatabase();

            if(checkRegister("courses","nid",nid)==0){
                ContentValues values = new ContentValues();
                values.put("nid", nid);
                values.put("title", title);
                values.put("descripcion", descripcion);
				values.put("code", code);
				values.put("duration", duration);
				values.put("inicio", inicio);
				values.put("fin", fin);
				values.put("contents", contents);
				values.put("state_suscription", state_suscription);
				values.put("category_nid", category_nid);
                db.insert("courses", null, values);
            }else{
                String[] args = {String.valueOf(nid)};
                ContentValues values = new ContentValues();
                values.put("title", title);
                values.put("descripcion", descripcion);
				values.put("code", code);
				values.put("duration", duration);
				values.put("inicio", inicio);
				values.put("fin", fin);
				values.put("contents", contents);
				values.put("state_suscription", state_suscription);
				values.put("category_nid", category_nid);
                db.update("courses", values, "nid" + "=?", args);
            }

            db.close();

        } catch (SQLException e) {

        }
    }

	public ArrayList<Cursos> listCourses() {
		ArrayList<Cursos> values = new ArrayList<Cursos>();
		db = getReadableDatabase();
		Cursor c = db.rawQuery("select * from courses", null);
		if (c.getCount() != 0) {
			c.moveToFirst();
			do {
				Cursos data = new Cursos(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6), c.getString(7), c.getString(8), c.getString(9));
				values.add(data);
			} while (c.moveToNext());
		}
		db.close();
		c.close();
		return values;
	}

	public ArrayList<Cursos> listCoursesUnsubscribe() {
		ArrayList<Cursos> values = new ArrayList<Cursos>();
		db = getReadableDatabase();
		Cursor c = db.rawQuery("select * from courses where state_suscription=0", null);
		if (c.getCount() != 0) {
			c.moveToFirst();
			do {
				Cursos data = new Cursos(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6), c.getString(7), c.getString(8), c.getString(9));
				values.add(data);
			} while (c.moveToNext());
		}
		db.close();
		c.close();
		return values;
	}


	public void updateSuscriptionCourse(String id) throws SQLException {

		try {
			db = getReadableDatabase();

				String[] args = {String.valueOf(id)};
				ContentValues values = new ContentValues();
				values.put("state_suscription", "1");
				db.update("courses", values, "nid" + "=?", args);


			db.close();

		} catch (SQLException e) {

		}
	}



	public void registerEvents(String title, String body, String date, String locate, String browser) throws SQLException {

		try {
			db = getReadableDatabase();

			if(checkRegister("events","title",title)==0){
				ContentValues values = new ContentValues();
				values.put("title", title);
				values.put("body", body);
				values.put("date", date);
				values.put("locate", locate);
				values.put("browser", browser);
				db.insert("events", null, values);
			}else{
				String[] args = {String.valueOf(title)};
				ContentValues values = new ContentValues();
				values.put("body", body);
				values.put("date", date);
				values.put("locate", locate);
				values.put("browser", browser);
				db.update("events", values, "title" + "=?", args);
			}

			db.close();

		} catch (SQLException e) {

		}
	}


	public ArrayList<Events> listEvents() {
		ArrayList<Events> values = new ArrayList<Events>();
		db = getReadableDatabase();
		Cursor c = db.rawQuery("select * from events", null);
		if (c.getCount() != 0) {
			c.moveToFirst();
			do {
				Events data = new Events(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4));
				values.add(data);
			} while (c.moveToNext());
		}
		db.close();
		c.close();
		return values;
	}

	public ArrayList<Events> listEvents(String date) {
		ArrayList<Events> values = new ArrayList<Events>();
		db = getReadableDatabase();
		Cursor c = db.rawQuery("select * from events where date='" + date + "'", null);
		if (c.getCount() != 0) {
			c.moveToFirst();
			do {
				Events data = new Events(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4));
				values.add(data);
			} while (c.moveToNext());
		}
		db.close();
		c.close();
		return values;
	}

	public ArrayList<String> listDateEvents() {
		ArrayList<String> values = new ArrayList<String>();
		db = getReadableDatabase();
		Cursor c = db.rawQuery("select date from events group by date order by date", null);
		if (c.getCount() != 0) {
			c.moveToFirst();
			do {
				values.add(c.getString(0));
			} while (c.moveToNext());
		}
		db.close();
		c.close();
		return values;
	}

	public int checkRegister(String table,String field,String data) {
    	int total=0;
    	String[] param = {data};    	
        Cursor c = db.rawQuery("select * from " + table + "  where " + field + "=?", param);        
        total = c.getCount();
        c.close();
        return total;
    }
    
    
    public void resetDataBase() {    	
    	db = getReadableDatabase();    	
    	db.execSQL("delete from news");
    	db.execSQL("delete from ssc");
    	db.execSQL("delete from cursos");
    }
    
}
