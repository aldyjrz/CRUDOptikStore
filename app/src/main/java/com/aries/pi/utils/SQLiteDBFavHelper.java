package com.aries.pi.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.aries.pi.AppController;

import java.util.Map;
import java.util.Set;

public class SQLiteDBFavHelper
{
  private static final String DB_NAME = "product.db";
  private SQLiteDatabase db;

  public SQLiteDBFavHelper(AppController app)
  {
    if (app.dbHelper != null) {
      throw new RuntimeException("Duplicated db helper");
    }
    db = app.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
    this.db = app.openOrCreateDatabase("product.db", 0, null);
    app.dbHelper = this;
    createTableIfNotExist();
  }
  
  private void createTableIfNotExist()
  {
    String sql = "CREATE TABLE if NOT EXISTS \"product\" (\n\"id\"  TEXT PRIMARY KEY AUTOINCREMENT," +
            "\n\"nama_barang\"  TEXT NOT NULL," +
            "\n\"harga_barang\"  DOUBLE NOT NULL," +
            "\n\"ukuran_lensa\"  INTEGER NOT NULL," +
            "\n\"lensa\"  TEXT NOT NULL\n);\n";
    db.execSQL(sql);
  }
  /**
   * 一次执行多条SQL, 做一次事务，使用这个方法。
   *
   * @param sql_bind_map Map,  value是sql语句，key 是 绑定数据的 Object[]
   * @throws java.sql.SQLException if the SQL string is invalid
   */
  public void execBulkSQL(Map<Object[], String> sql_bind_map) {
    Set<Map.Entry<Object[], String>> entrySet = sql_bind_map.entrySet();
    synchronized (db) {
      db.beginTransaction();
      try {
        for (Map.Entry<Object[], String> sqlEntry : entrySet) {
          if (sqlEntry.getKey() == null) {
            db.execSQL(sqlEntry.getValue());
          } else {
            db.execSQL(sqlEntry.getValue(), sqlEntry.getKey());
          }
        }
        db.setTransactionSuccessful();
      } finally {
        db.endTransaction();
      }
    }
  }
  /**
   * 执行数据库可写的操作
   * {@link android.database.sqlite.SQLiteDatabase#execSQL(String, Object[]) }
   *
   * @throws java.sql.SQLException if the SQL string is invalid
   */
  public void execSQL(String sql, @android.support.annotation.Nullable Object[] bindArgs) {
    synchronized (db) {
      db.beginTransaction();
      try {
        if (bindArgs == null) {
          db.execSQL(sql);
        } else {
          db.execSQL(sql, bindArgs);
        }
        db.setTransactionSuccessful();
      } finally {
        db.endTransaction();
      }
    }
  }
    /**
     * proxy to: {@link android.database.sqlite.SQLiteDatabase#rawQuery(String, String[])}
     */
    public Cursor rawQuery (String sql, String[]selectionArgs){
      synchronized (db) {
        return db.rawQuery(sql, selectionArgs);
      }

  }
}


/* Location:              C:\Users\Aldi Pranata\Desktop\dex2jar-2.0\module.jar!\com\gojek\driver\bike\fav\SQLiteDBFavHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */