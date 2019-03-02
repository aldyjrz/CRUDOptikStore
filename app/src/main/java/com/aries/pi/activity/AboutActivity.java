package com.aries.pi.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.aries.pi.R;
import com.aries.pi.SessionUser;

public class AboutActivity extends AppCompatActivity {

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
    private SharedPreferences prefs;
    private SessionUser session;

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        menu.add(0, R.id.menu_logout, 0, R.string.logout);
        logoutUser();
        return super.onCreateOptionsMenu(menu);
    }

    private void logoutUser() {
        prefs = getSharedPreferences("product", 0);
        session = new SessionUser(getApplicationContext());
        session.setLogin(false);
        prefs.edit().clear().apply();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_logout:
                logoutUser();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
