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
import sercandevops.com.veterineruygulamasi.Models.RegisterPojo;
import sercandevops.com.veterineruygulamasi.R;
import sercandevops.com.veterineruygulamasi.RestApi.ManagerAll;
import sercandevops.com.veterineruygulamasi.Utils.Warnings;

public class KayitolActivity extends AppCompatActivity {

    Button btn_kayitol;
    EditText ed_mailadres,ed_kadi,ed_parola;
    TextView tv_hesapvar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayitol);

        Tanimlamalar();
        registerToUser();
    }

    public void Tanimlamalar()
    {
        btn_kayitol = findViewById(R.id.btn_kayitol);
        ed_mailadres = (EditText)findViewById(R.id.ed_mailadres);
        ed_kadi = (EditText)findViewById(R.id.ed_kadi);
        ed_parola = (EditText)findViewById(R.id.ed_parola);
        tv_hesapvar = findViewById(R.id.tv_hesapvar);


    }
    public void TemizleEditText()
    {
        ed_mailadres.setText("");
        ed_kadi.setText("");
        ed_parola.setText("");
    }
    public void registerToUser()
    {

        btn_kayitol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail = ed_mailadres.getText().toString();
                String kadi = ed_kadi.getText().toString();
                String parola = ed_parola.getText().toString();

                  register(mail,kadi,parola);
                  TemizleEditText();
            }
        });

        tv_hesapvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KayitolActivity.this,GirisYapActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    public void register(String userMailAdres,String userName,String userPass)
    {
        Call<RegisterPojo> request = ManagerAll.getInstance().kayitOl(userMailAdres,userName,userPass);
        request.enqueue(new Callback<RegisterPojo>() {
            @Override
            public void onResponse(Call<RegisterPojo> call, Response<RegisterPojo> response) {

                if(response.body().isTf())
                {
                    Toast.makeText(getApplicationContext(),response.body().getText(),Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(KayitolActivity.this,GirisYapActivity.class);
                    startActivity(intent);
                    finish();
                }else
                {
                    Toast.makeText(getApplicationContext(),response.body().getText(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterPojo> call, Throwable t) {

                Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
