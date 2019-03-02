package com.aries.pi;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class SessionUser
{
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String PREF_NAME = "BSH";
    private static String TAG = SessionUser.class.getSimpleName();
       int PRIVATE_MODE = 0;
    Context _context;
    Editor editor;
    SharedPreferences pref;

    public SessionUser(Context paramContext)
    {
        this._context = paramContext;
        this.pref = this._context.getSharedPreferences("product", this.PRIVATE_MODE);
        this.editor = this.pref.edit();
    }

    public boolean isLoggedIn()
    {
        return this.pref.getBoolean("isLoggedIn", false);
    }

    public void setLogin(boolean paramBoolean)
    {
        this.editor.putBoolean("isLoggedIn", paramBoolean);
        this.editor.apply();
        Log.d(TAG, "User login session modified!");
    }
}


/* Location:              C:\Users\Aldi Pranata\Desktop\dex2jar-2.0\module.jar!\com\gojek\driver\bike\handler\SessionManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */