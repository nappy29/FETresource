package com.example.fetapp;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TextView;


/*public class Helper extends SQLiteOpenHelper{
	public static final String DB_NAME = "courses";
	public static final int DB_VERSION = 1;
	public static final String Table= "course";
	public static final String ClassId = "id";
	public static final String Time7 = "first";
	public static final String Time9 = "second";
	public static final String Time11 = "third";
	public static final String Time13 = "fouth";
	public static final String Time15 = "fifth";
	public static final String Time17 = "sixth";
//	private Context context;
//	private DataBase ourHelper;
	
	private SQLiteDatabase ourDatabase;	
	TextView[][] textviews  = new TextView[6][6];
	
	public Helper(Context c){
		super(c, DB_NAME, null, DB_VERSION);
	}
	
		@Override
		public void onCreate(SQLiteDatabase db) {
			 String sql = "   create table " + Helper.Table + " (" + Helper.ClassId 
			          + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			          + Helper.Time7 + " TEXT," 
			          + Helper.Time9 + " TEXT," 
			          + Helper.Time11 +" TEXT," 
			          + Helper.Time13 +" TEXT," 
			          + Helper.Time15 +" TEXT," 
			          + Helper.Time17 +" TEXT)";
			
			db.execSQL("   create table " + Helper.Table + " (" + Helper.ClassId 
			          + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			          + Helper.Time7 + " TEXT," 
			          + Helper.Time9 + " TEXT," 
			          + Helper.Time11 +" TEXT," 
			          + Helper.Time13 +" TEXT," 
			          + Helper.Time15 +" TEXT," 
			          + Helper.Time17 +" TEXT)");
			Log.d("DB", "onCreated sql: " + sql);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("drop table if exists " + Table); // drops the old database
			Log.d("DB", "onUpdated");
			onCreate(db);

			
		}		
		
		public void insertData(String first, String second, String third, String fourth, String fifth, String sixth) {
			SQLiteDatabase db = this.getWritableDatabase();
			  ContentValues cv = new ContentValues();
			  cv.put(Helper.Time7, first);
			  cv.put(Helper.Time9, second);
			  cv.put(Helper.Time11, third);
			  cv.put(Helper.Time13, fourth);
			  cv.put(Helper.Time15, fifth);
			  cv.put(Helper.Time17, sixth);
			  db.insert(Helper.Table, null, cv);

			 }
		
		public Cursor retreiveData(){
		    SQLiteDatabase db = this.getReadableDatabase();
			String[] all = new String[]{Helper.Time7,Helper.Time9, Helper.Time11, Helper.Time13,Helper.Time15,Helper.Time17};
			 Cursor c = db.query(Helper.Table, all, null, null, null, null, null);
			 return c;
		}

	}
	*/
public class DbControl {

	public static final String DB_NAME = "courses";
	public static final int DB_VERSION = 1;
	public static final String Table= "course";
	public static final String ClassId = "id";
	public static final String Time7 = "first";
	public static final String Time9 = "second";
	public static final String Time11 = "third";
	public static final String Time13 = "fouth";
	public static final String Time15 = "fifth";
	public static final String Time17 = "sixth";
	
//	public static final String DATABASE_TABLE = "timetable";
//	public static final String KEY_ID = "_id";
//	public static final String KEY_SCHOOL = "school";
//	public static final String KEY_LEVEL = "level";
//	public static final String KEY_DAY = "day";
//	public static final String KEY_COURSE = "course";
//	
//	private static final String DATABASE_NAME = "timetableDB";
//	private static final int DATABASE_VERSION = 1;
	
	public final String sql = "   create table " + DbControl.Table + " (" + DbControl.ClassId 
	          + " INTEGER PRIMARY KEY AUTOINCREMENT, "
	          + DbControl.Time7 + " TEXT," 
	          + DbControl.Time9 + " TEXT," 
	          + DbControl.Time11 +" TEXT," 
	          + DbControl.Time13 +" TEXT," 
	          + DbControl.Time15 +" TEXT," 
	          + DbControl.Time17 +" TEXT)";
	private Context ourContext;
	private Helper ourHelper;
	private SQLiteDatabase ourDatabase;
	public class Helper extends SQLiteOpenHelper{

		public Helper(Context context) {
			super(context, DB_NAME, null, DB_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
	       db.execSQL(sql);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + Table);
			onCreate(db);
		}
		
	}
	public void droptable(){
//		ourDatabase.execSQL("DROP TABLE IF EXISTS " + Table);
//		ourHelper.onCreate(ourDatabase);
	}
	
	public DbControl(Context c){
		ourContext = c;
	}
	public DbControl open(){
		ourHelper = new Helper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	public void close(){
		ourHelper.close();
	}
	
	
	public void insertData(String first, String second, String third, String fourth, String fifth, String sixth) {

		  ContentValues cv = new ContentValues();
		  cv.put(DbControl.Time7, first);
		  cv.put(DbControl.Time9, second);
		  cv.put(DbControl.Time11, third);
		  cv.put(DbControl.Time13, fourth);
		  cv.put(DbControl.Time15, fifth);
		  cv.put(DbControl.Time17, sixth);
		  ourDatabase.insert(DbControl.Table, null, cv);

		 }
	
	public String getinfo() {
		// TODO Auto-generated method stub
		String[] columns = new String[]{Time7, Time9, Time11, Time13, Time15,Time17};
		Cursor c = ourDatabase.query(Table, columns, null, null, null, null, null, null);
		
		int iRowid = c.getColumnIndex(Time7);
		int iRowsch = c.getColumnIndex(Time9);
		int iRowlvl = c.getColumnIndex(Time11);
		int iRowdy = c.getColumnIndex(Time13);
		int iRowcrs = c.getColumnIndex(Time15);
		String result = " ";
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			
			result = result + c.getString(iRowid) + " " + c.getString(iRowsch) + " " + c.getString(iRowlvl) + " " + c.getString(iRowdy)
					+ " " + c.getString(iRowcrs) + "\n";
		}
		return result;
		
	}
	
	public Cursor retreiveData(){
	    
//		String[] all = new String[]{DbControl.Time7,DbControl.Time9, DbControl.Time11, DbControl.Time13,DbControl.Time15,DbControl.Time17};
//		 Cursor c = ourDatabase.query(DbControl.Table, all, null, null, null, null, null);
		   String query = "SELECT  * FROM " + DbControl.Table;
		 Cursor c =  ourDatabase.rawQuery(query, null );
		 return c;
		 
	}
	
}

	
	


