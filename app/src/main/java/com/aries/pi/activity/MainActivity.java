package com.aries.pi.activity;

import android.content.Intent;
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
import com.aries.pi.utils.ProductAdd;
import com.aries.pi.utils.SQLiteHandler;

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

    private String idx, namax, lensax;
    private String hargax;
    String ukuranx;
    Button addcart,cart, btn_cari;
    private EditText et_cari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       app = AppController.obtainApp(this);
       app.addActivity(this);

        SessionUser user = new SessionUser(getApplicationContext());
        if(user.isLoggedIn()){
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }else {
            et_cari = (EditText)findViewById(R.id.et_cari);
            btn_cari = (Button) findViewById(R.id.cari);
            cart = (Button)findViewById(R.id.btn_add);
             final TextView id, nama, harga, ukuran, lensa;
            id = (TextView)findViewById(R.id.tv_product_id);
            nama = (TextView)findViewById(R.id.tv_product_name);
            harga = (TextView)findViewById(R.id.tv_product_price);
            ukuran = (TextView)findViewById(R.id.tv_product_size);
            lensa = (TextView)findViewById(R.id.tv_product_lensa);

            mTextMessage = (TextView) findViewById(R.id.message);
            BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

            cart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), ProductAdd.class));
                }
            });

            btn_cari.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                   String idx = et_cari.getText().toString();
                    if(idx.isEmpty()){
                        Toast.makeText(getApplicationContext(), "ID Product Belum Di Isi", Toast.LENGTH_SHORT).show();
                    }else if(!idx.isEmpty()){

                        namax = nama.getText().toString();
                        hargax = harga.getText().toString();
                        ukuranx = ukuran.getText().toString();
                        lensax = lensa.getText().toString();
                        namax = nama.getText().toString();
                        hargax = harga.getText().toString();
                        ukuranx = ukuran.getText().toString();
                        lensax = lensa.getText().toString();
                        SQLiteHandler db = new SQLiteHandler(getApplicationContext());
                        db.getProductDetail();
                        }

                }

            });

        }
    }


}
