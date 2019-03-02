package com.aries.pi.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aries.pi.R;
import com.aries.pi.SessionUser;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

     EditText _emailText;
    EditText _passwordText;
     Button _loginButton;
    private SharedPreferences prefs;
    private String email, password;
    private String userType;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        _emailText = (EditText) findViewById(R.id.input_email);
        _passwordText = (EditText) findViewById(R.id.input_password);
        _loginButton = (Button) findViewById(R.id.btn_login);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });
        prefs = getSharedPreferences("product", 0);
        Boolean logged = prefs.getBoolean("isLoggedIn", true);
        SessionUser ses = new SessionUser(getApplicationContext());
        if(!ses.isLoggedIn()){
            return;
        }else{
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        userType = prefs.getString("user", "");
        if (logged && userType.equals("admin")) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }

    //methode untuk login
    public void login() {
        Log.d(TAG, "Loging In");


        if (!validate()) {
            //jika fungsi validasi false maka akan memanggil methode onLoginFailed()
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);


        //untuk membuat progressDialog
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");

        progressDialog.show();

        email = _emailText.getText().toString();
        password = _passwordText.getText().toString();


        //kondisi untuk username dan password admin
        if(email.equals("admin@admin.com") && password.equals("admin")) {

            //input data login ke dalam preferences android
            prefs = getSharedPreferences("isLoggedIn", 0);
            prefs.edit().putString("user", "admin").apply();
            prefs.edit().putBoolean("LoggedIn", true).apply();

            //username dan pass untuk customer

        }

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    //fungsi turunan untuk / ketika kita menekan tombol back
    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }


    public void onLoginSuccess() {
        _loginButton.setEnabled(true);


        userType = prefs.getString("user", "");
        //jika user admin, maka akan diarahkan kedalam page admin
        if(userType.equals("admin")) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));

        }
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed\nCheck Username or Password", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        }
        if (password.isEmpty() || password.length() < 4 ) {
            //password tidak boleh kosong, tidak boleh kurang dari 4 karakter
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else if(email.equals("admin@admin.com") && password.equals("aries")){
            prefs = getSharedPreferences("isLoggedIn", 0);
            prefs.edit().putString("user", "admin").apply();
            prefs.edit().putBoolean("LoggedIn", true).apply();
            _passwordText.setError(null);
        }

        return valid;
    }
}