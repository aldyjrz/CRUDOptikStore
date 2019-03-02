package com.aries.pi.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aries.pi.AppController;
import com.aries.pi.R;
import com.aries.pi.SessionUser;
import com.aries.pi.utils.DAO_Product;
import com.aries.pi.utils.ProductAdd;
import com.aries.pi.utils.SQLiteDBFavHelper;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    return true;
                case R.id.navigation_about:
                    startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                    return true;
            }
            return false;
        }
    };
    private AppController app;
    private SQLiteDBFavHelper dbFavHelper;
    private String idx, namax, lensax;
    private Integer hargax;
    String ukuranx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       app = AppController.obtainApp(this);
       app.addActivity(this);

        initAllres();
        SessionUser user = new SessionUser(getApplicationContext());
        if(user.isLoggedIn()){
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    Button addcart,cart, btn_cari;
    public void initAllres(){
        //EditText
        final EditText et_cari = (EditText)findViewById(R.id.et_cari);
        btn_cari = (Button) findViewById(R.id.cari);
        cart = (Button)findViewById(R.id.btn_add);
        final TextView id, nama, harga, ukuran, lensa;
        id = (TextView)findViewById(R.id.tv_product_id);
        nama = (TextView)findViewById(R.id.tv_product_name);
        harga = (TextView)findViewById(R.id.tv_product_price);
        ukuran = (TextView)findViewById(R.id.tv_product_size);
        lensa = (TextView)findViewById(R.id.tv_product_lensa);

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProductAdd.class));
            }
        });
        btn_cari.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                idx = id.getText().toString();
                namax = nama.getText().toString();
                hargax = Integer.valueOf(harga.getText().toString());
                ukuranx = ukuran.getText().toString();
                lensax = lensa.getText().toString();
              if(idx.isEmpty()){
                  Toast.makeText(getApplicationContext(), "ID Product Belum Di Isi", Toast.LENGTH_SHORT).show();
              }else{


                  dbFavHelper = AppController.obtainApp(getApplicationContext()).dbHelper;
                  Cursor res = dbFavHelper.rawQuery("select * from product", null);
                  while (res.moveToNext()) {

                      idx = res.getString(0);
                      namax = res.getString(1);
                      hargax = res.getInt(2);
                      ukuranx = res.getString(3);
                      lensax = res.getString(4);
                      Toast.makeText(getApplicationContext(), idx, Toast.LENGTH_SHORT).show();

                  }
              }
                addcart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(idx.isEmpty()){
                            Toast.makeText(getApplicationContext(), "Barang Kosong", Toast.LENGTH_SHORT).show();
                        }
                        DAO_Product.addProd(getApplicationContext(), idx, namax, hargax, ukuranx, lensax);
                    }
                });
              }

        });

    }
}
