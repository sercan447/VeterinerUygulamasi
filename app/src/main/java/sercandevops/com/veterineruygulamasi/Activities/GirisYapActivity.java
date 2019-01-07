package sercandevops.com.veterineruygulamasi.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sercandevops.com.veterineruygulamasi.Models.LoginModel;
import sercandevops.com.veterineruygulamasi.R;
import sercandevops.com.veterineruygulamasi.RestApi.ManagerAll;
import sercandevops.com.veterineruygulamasi.Utils.GetSharedPreferences;

public class GirisYapActivity extends AppCompatActivity {


    private EditText ed_mail,ed_pass;
    private TextView tv_loginKayitol;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_yap);

        Tanimalama();
        Click();
    }

    public void Tanimalama()
    {
        ed_mail = (EditText)findViewById(R.id.ed_loginMail);
        ed_pass = (EditText)findViewById(R.id.ed_loginPass);
        tv_loginKayitol = findViewById(R.id.tv_loginKayitol);
        loginButton = (Button)findViewById(R.id.btn_loginGiris);

    }

    public void Click()
    {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = ed_mail.getText().toString();
                String pass = ed_pass.getText().toString();

                login(mail,pass);
            }
        });


        tv_loginKayitol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GirisYapActivity.this,KayitolActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    public void login(String mailAdres,String parola)
    {

        Call<LoginModel> login = ManagerAll.getInstance().GirisYap(mailAdres,parola);
        login.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                if (response.body().isTf())
                {
                    Toast.makeText(getApplicationContext(),"Y"+response.body().getText(),Toast.LENGTH_LONG).show();

                    GetSharedPreferences getSharedPreferences = new GetSharedPreferences(GirisYapActivity.this);
                    getSharedPreferences.setSession(response.body().getId(),response.body().getUsername(),response.body().getMailadres());

                    Intent intent = new Intent(GirisYapActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else
                {
                    Toast.makeText(getApplicationContext(),"N"+response.body().getText(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"hata",Toast.LENGTH_LONG).show();
            }
        });

    }
}
