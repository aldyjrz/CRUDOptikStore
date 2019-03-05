package com.aries.pi.utils;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aries.pi.AppController;
import com.aries.pi.R;
import com.aries.pi.activity.AboutActivity;
import com.aries.pi.activity.MainActivity;

import java.util.ArrayList;

public class ProductAct
        extends ListActivity
{
  AppController app;
  private String id;
  private String nama_barang;
  private Double harga_barang;
  private Integer ukuran_lensa;
  private String lensa;
  private SharedPreferences prefs;
  private SQLiteHandler SQLiteHandler;


  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.add_product);
    this.app = AppController.obtainApp(this);
    this.app.addActivity(this);

  }

  public boolean onCreateOptionsMenu(Menu menu)
  {
    menu.add(0, R.id.menu_clear_fav, 0, R.string.title_home);
    menu.add(0, R.id.menu_add_fav, 1, R.string.logout);
    return super.onCreateOptionsMenu(menu);
  }

  protected void onDestroy()
  {
    super.onDestroy();
    this.app.removeActivity(this);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.navigation_home:
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        break;
      case R.id.navigation_about:
        startActivity(new Intent(this, AboutActivity.class));
    }
    return super.onOptionsItemSelected(item);
  }

  private static final class ProductAdapter
          extends BaseAdapter
  {
    private Context context;
    private ArrayList<FavBean> favBeans;

    private ProductAdapter(Context paramContext, ArrayList<FavBean> paramArrayList)
    {
      this.context = paramContext;
      this.favBeans = paramArrayList;
    }

    public int getCount()
    {
      return this.favBeans.size();
    }

    public Object getItem(int paramInt)
    {
      return this.favBeans.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return 0L;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      ViewHolder holder;
      if (convertView == null) {
        convertView = LayoutInflater.from(context).inflate(R.layout.product_layout, parent, false);
        holder = new ViewHolder();
        holder.tv_id = (TextView) convertView.findViewById(R.id.tv_product_id);
        holder.tv_namabarang = (TextView) convertView.findViewById(R.id.tv_product_name);
        holder.tv_hargabarang = (TextView) convertView.findViewById(R.id.tv_harga);
        holder.tv_ukuranlensa = (TextView) convertView.findViewById(R.id.tv_ukuran);
        holder.tv_lensa = (TextView) convertView.findViewById(R.id.tv_lensa);


        convertView.setTag(holder);
      } else {
        holder = (ViewHolder) convertView.getTag();
      }
      FavBean history = favBeans.get(position);
      holder.tv_id.setText(history.ids);
      holder.tv_namabarang.setText(history.nama_barang);
      holder.tv_hargabarang.setText(String.valueOf(history.harga_barang));
      holder.tv_ukuranlensa.setText(history.ukuran_lensa);
      holder.tv_lensa.setText(history.lensa);



      return convertView;
    }
  }
  private static final class ViewHolder
  {
    public TextView tv_id;
    public TextView tv_namabarang;
    public TextView tv_hargabarang;
    public TextView tv_ukuranlensa;
    public TextView tv_lensa;

  }
}


/* Location:              C:\Users\Aldi Pranata\Desktop\dex2jar-2.0\module.jar!\com\gojek\driver\bike\fav\ProductAct.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */