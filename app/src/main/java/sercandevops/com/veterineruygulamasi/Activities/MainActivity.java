package sercandevops.com.veterineruygulamasi.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import sercandevops.com.veterineruygulamasi.Fragment.HomeFragment;
import sercandevops.com.veterineruygulamasi.R;
import sercandevops.com.veterineruygulamasi.Utils.ChangeFragments;
import sercandevops.com.veterineruygulamasi.Utils.GetSharedPreferences;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private GetSharedPreferences getSharedPreferences;

    MaterialButton anasayfaButn;
    ChangeFragments changeFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetFragment();
        Tanimla();
        Kontrol();
        action();
    }

    public void action()
    {
        anasayfaButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragments.change(new HomeFragment());
            }
        });
    }
    public void GetFragment()
    {
         changeFragments = new ChangeFragments(MainActivity.this);
        changeFragments.change(new HomeFragment());
    }
    public void Tanimla()
    {
        getSharedPreferences = new GetSharedPreferences(MainActivity.this);
        sharedPreferences = getSharedPreferences.getSession();
        anasayfaButn = (MaterialButton)findViewById(R.id.btn_anasayfa);

    }

    public void Kontrol()
    {

        if(sharedPreferences.getString("id",null) == null && sharedPreferences.getString("mailadres",null) == null
                        && sharedPreferences.getString("username",null) == null)
        {
            Intent intent = new Intent(MainActivity.this,GirisYapActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
