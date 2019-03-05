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

  EditText id, nama, harga_kiri, harga_kanan, ukuran_kiri, ukuran_kanan, mins;
  Button save;
  private SQLiteHandler db;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.add_product);
    db = new SQLiteHandler(getApplicationContext());
    id = findViewById(R.id.idbarang);
    nama = findViewById(R.id.namabarang);
    harga_kiri = findViewById(R.id.harga_kiri);
    harga_kanan = findViewById(R.id.harga_kanan);
    ukuran_kanan = findViewById(R.id.ukuran_kanan);
    ukuran_kiri = findViewById(R.id.ukuran_kiri);
    mins = findViewById(R.id.lens);
    save = findViewById(R.id.btn_simpan);

    save.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        final String ids;
        final String namas;
        final String hargas_kiris;
        final String harga_kanans;
        final String ukuran_kiris;
        final String ukuran_kanans;
        final String minss;
        ids = id.getText().toString();
        namas = nama.getText().toString();
        ukuran_kiris = ukuran_kiri.getText().toString();
        ukuran_kanans = ukuran_kanan.getText().toString();
        hargas_kiris = harga_kiri.getText().toString();
        harga_kanans = harga_kanan.getText().toString();
        minss = mins.getText().toString();

        if(!ids.isEmpty() && !namas.isEmpty() &&  !minss.isEmpty()) {
          db.addProd(id.getText().toString(), namas, harga_kanans, hargas_kiris, ukuran_kanans, ukuran_kiris, minss);


        }else  if(ids.isEmpty() && namas.isEmpty() && minss.isEmpty() && harga_kanans.isEmpty() && ukuran_kanans.isEmpty()) {
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