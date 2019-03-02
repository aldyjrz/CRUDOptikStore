package com.aries.pi.utils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aries.pi.AppController;
import com.aries.pi.R;

public class ProductAdd
  extends AppCompatActivity

{
  AppController app;

  EditText id, nama, harga, ukuran, mins;
  Button save;
  private DAO_Product db;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.add_product);

    id = (EditText)findViewById(R.id.idbarang);
    nama = (EditText)findViewById(R.id.namabarang);
    harga = (EditText)findViewById(R.id.hargabarang);
    ukuran = (EditText)findViewById(R.id.ukuran);
    mins = (EditText)findViewById(R.id.lens);
    save = (Button)findViewById(R.id.btn_simpan);

    save.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        final String ids;
        final String namas;
        final Integer hargas;
        final String ukurans;
        final String minss;
        ids = id.getText().toString();
        namas = nama.getText().toString();
        hargas = Integer.valueOf(harga.getText().toString());
        ukurans = ukuran.getText().toString();
        minss = mins.getText().toString();

        if(!ids.isEmpty() && !namas.isEmpty() &&  !minss.isEmpty()) {
          DAO_Product.addProd(getApplicationContext(), ids, namas, hargas, ukurans, minss);
        }else{
          Toast.makeText(getApplicationContext(), "Harap isi semua field", Toast.LENGTH_SHORT).show();
        }
      }
    });
  }
}


/* Location:              C:\Users\Aldi Pranata\Desktop\dex2jar-2.0\module.jar!\com\gojek\driver\bike\fav\addFav.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */