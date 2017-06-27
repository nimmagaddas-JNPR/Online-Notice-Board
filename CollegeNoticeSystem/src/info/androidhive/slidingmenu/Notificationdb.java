package info.androidhive.slidingmenu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class Notificationdb {
	 public static final String KEY_ROWID = "_id";
	    public static final String KEY_message = "message";
	    private static final String TAG = "Notificationdb";
	    private static final String DATABASE_NAME = "salesnotification";
	    private static final String DATABASE_TABLE = "notidetails";
	    private static final int DATABASE_VERSION = 1;
	    private static final String DATABASE_CREATE =
	    "create table notidetails (_id integer primary key autoincrement, "
	    + "message text not null);";
	    private final Context context;
	    private DatabaseHelper DBHelper;
	    private SQLiteDatabase db;
	
	    public Notificationdb(Context ctx)
	    {
	    this.context = ctx;
	    DBHelper = new DatabaseHelper(context);
	    }
	    public static class DatabaseHelper extends SQLiteOpenHelper
	    {
	    DatabaseHelper(Context context)
	    {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);//it invokes a constructor of 'SQLiteOpenHelper' 
	    }
	    
	    @Override
	public void onCreate(SQLiteDatabase db)//method of 'SQLiteOpenHelper' and u must be used it.
	    {
	    db.execSQL(DATABASE_CREATE);
	    }
	    
	    @Override//method of SQLiteOpenHelper,and u must be used it
	public void onUpgrade(SQLiteDatabase db, int oldVersion,
	    int newVersion)
	    {
	    Log.w(TAG, "Upgrading database from version " + oldVersion
	    + " to "
	    + newVersion + ", which will destroy all old data");
	    db.execSQL("DROP TABLE IF EXISTS orderdetails");//if DB already Exists,then remove it and create new one.
	    onCreate(db);//creating the table with given name..
	    }
	    }
	    
	    //---opens the database---it is calling from another Activity..
	public Notificationdb open() throws SQLException
	    {
	    db = DBHelper.getWritableDatabase();
	    return this;
	    }

	    //---closes the database---
	public void close()
	    {
	    DBHelper.close();
	    }
	    
	    //---insert a title into the database---
	public long insert(String message )
	    {
	    ContentValues initialValues = new ContentValues();
	    initialValues.put(KEY_message,message);
	  
	    return db.insert(DATABASE_TABLE, null, initialValues);
	    }
	    
	    //---deletes a particular title---
	public boolean deleteuser(int rowId)
	    {
	    return db.delete(DATABASE_TABLE, KEY_ROWID +
	    "=" + rowId, null) > 0;
	    }
	public void deleteusers()
	{
		db.execSQL("delete from orderdetails");
	}
	public void deletedata()
	{
		db.execSQL("DROP databse IF EXISTS salesapp1");
	}
	    
	    //---retrieves all the titles---
	

	    //---retrieves a particular title---
	public Cursor getMessage()throws SQLException
	{
		Cursor cursor = db.rawQuery(
			    "select * from notidetails",
			    null
			);
	return cursor;
	}



	    }
