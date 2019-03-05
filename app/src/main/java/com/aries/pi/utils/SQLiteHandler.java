package com.aries.pi.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class SQLiteHandler
  extends SQLiteOpenHelper
{
  private static final String DATABASE_NAME = "BSH-MOD";
  private static final int DATABASE_VERSION = 1;
  private static final String KEY_EMAIL = "email";
  private static final String KEY_ID = "id";
  private static final String KEY_IMEI = "imei";
  private static final String KEY_NAMA = "nama";
  private static final String TABLE_USER = "product";
  private static SQLiteDatabase db;
  private static final String TAG = SQLiteHandler.class.getSimpleName();
  Context _context;
  SharedPreferences pref;
  
  public SQLiteHandler(Context paramContext)
  {
    super(paramContext, "product", null, 1);
  }
  @Override
  public void onCreate(SQLiteDatabase db) {
    String CREATE_LOGIN_TABLE ="CREATE TABLE if NOT EXISTS \"product\" (\n" +
            "\"ids\" TEXT PRIMARY KEY AUTOINCREMENT,\n" +
            "\"nama_barang\"  TEXT NOT NULL," +
            "\"harga_lensa_kiri\"  TEXT NOT NULL," +
            "\"harga_lensa_kanan\"  TEXT NOT NULL," +
            "\"ukuran_lensa_kanan\"  TEXT NOT NULL," +
            "\"ukuran_lensa_kiri\"  TEXT NOT NULL," +
            "\"lensa\"  TEXT NOT NULL" +
            ");";
    db.execSQL(CREATE_LOGIN_TABLE);


  }

  
  public void deleteUsers()
  {
    SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
    localSQLiteDatabase.delete("product", null, null);
    localSQLiteDatabase.close();
    Log.d(TAG, "Deleted all prod info from sqlite");
  }
  public void addProd(String ids, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7) {
    db = (SQLiteDatabase) getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put("ids", ids);
    values.put("nama_barang", paramString2);
    values.put("harga_lensa_kiri", paramString3);
    values.put("harga_lensa_kanan", paramString3);
    values.put("ukuran_lensa_kanan", paramString3);
    values.put("ukuran_lensa_kiri", paramString3);
    values.put("lensa", paramString3);
    long l = db.insert("product", null, values);
    db.close();
    Log.d(TAG, "New user inserted into sqlite: " + l);
  }
  public HashMap<String, String> getProductDetail()
  {
    HashMap localHashMap = new HashMap();
    SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
    Cursor localCursor = localSQLiteDatabase.rawQuery("SELECT  * FROM product", null);
    localCursor.moveToFirst();
    if (localCursor.getCount() > 0)
    {
      localHashMap.put("ids", localCursor.getString(1));
      localHashMap.put("nama_barang", localCursor.getString(2));
      localHashMap.put("harga_lensa_kiri", localCursor.getString(3));
      localHashMap.put("harga_lensa_kanan", localCursor.getString(4));
      localHashMap.put("ukuran_lensa_kanan", localCursor.getString(5));
      localHashMap.put("ukuran_lensa_kiri", localCursor.getString(6));
      localHashMap.put("email", localCursor.getString(7));
    }
    localCursor.close();
    localSQLiteDatabase.close();
    Log.d(TAG, "Fetching user from Sqlite: " + localHashMap.toString());
    return localHashMap;
  }


  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS product");
    onCreate(paramSQLiteDatabase);
  }
}


/* Location:              C:\Users\Aldi Pranata\Desktop\dex2jar-2.0\module.jar!\com\gojek\driver\bike\handler\SQLiteHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */