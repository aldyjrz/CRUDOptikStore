package com.aries.pi.utils;

import android.content.Context;
import android.database.Cursor;

import com.aries.pi.AppController;

import java.util.ArrayList;

public class DAO_Product

{
  private static SQLiteDBFavHelper dbHelper;

  public static void addProd(Context ctx, String paramString1, String paramString2, Integer paramString3, String paramString4, String paramString5)
  {
    dbHelper = AppController.obtainApp(ctx).dbHelper;
    dbHelper.execSQL("INSERT into product (id, nama_barang, harga_barang, ukurang_lensa, lensa) VALUES( ?,?,?,?,?);", new Object[]{ paramString1, paramString2, paramString3, paramString4, paramString5 });
  }
  
  public static void clearProd(Context paramContext)
  {
    dbHelper = AppController.obtainApp(paramContext).dbHelper;
   dbHelper.execSQL("delete from product", null);
  }
  
  public static void deleteProd(Context ctx, String paramString1, String paramString2, Integer paramString3, String paramString4, String paramString5)
  {
    dbHelper = AppController.obtainApp(ctx).dbHelper;

    dbHelper.execSQL("delete from product where id = '" + paramString1 + "' and nama_barang = '" + paramString2 + "' and harga_barang = '" + paramString3 +  "' and ukuran_lensa = '" + paramString4 + "' and lensa = '" + paramString5 +"'", null);
  }
  
  public static ArrayList<FavBean> getProduct(Context ctx)
  {
    dbHelper = AppController.obtainApp(ctx).dbHelper;
    Cursor cursor = dbHelper.rawQuery("select * from product", null);
    ArrayList<FavBean> favbeans = new ArrayList<>();
    while (cursor.moveToNext()) {
      FavBean fav = new FavBean();
      fav.id = String.valueOf(cursor.getString(0));
      fav.nama_barang = cursor.getString(1);
      fav.harga_barang = cursor.getDouble(2);
      fav.ukuran_lensa = cursor.getString(3);
      fav.lensa = cursor.getString(4);

      favbeans.add(fav);
    }
    cursor.close();
    return favbeans;
  }
}


/* Location:              C:\Users\Aldi Pranata\Desktop\dex2jar-2.0\module.jar!\com\gojek\driver\bike\fav\DAO_Fav.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */