package sercandevops.com.veterineruygulamasi.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class GetSharedPreferences {

    private SharedPreferences sharedPreferences;
    private Activity activity;

    public GetSharedPreferences(Activity activity)
    {
        this.activity = activity;
    }

    public SharedPreferences getSession()
    {
        sharedPreferences = activity.getApplicationContext().getSharedPreferences("session",Context.MODE_PRIVATE);
        return sharedPreferences;
    }


    public void setSession(String id, String username,String mailAdres)
    {
        sharedPreferences = activity.getApplicationContext().getSharedPreferences("session",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("id",id);
        editor.putString("username",username);
        editor.putString("mailadres",mailAdres);
        editor.commit();
    }
}
