package sercandevops.com.veterineruygulamasi.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;
import sercandevops.com.veterineruygulamasi.Adapters.AnswerAdapter;
import sercandevops.com.veterineruygulamasi.Models.AnswerModel;
import sercandevops.com.veterineruygulamasi.Models.AskQuestionModel;
import sercandevops.com.veterineruygulamasi.R;
import sercandevops.com.veterineruygulamasi.RestApi.ManagerAll;
import sercandevops.com.veterineruygulamasi.Utils.ChangeFragments;
import sercandevops.com.veterineruygulamasi.Utils.GetSharedPreferences;
import sercandevops.com.veterineruygulamasi.Utils.Warnings;


public class HomeFragment extends Fragment {

    public View view;
    private LinearLayout petlerimLinearLayout,soruLinearLayout,cevaplarLinerlayout
                        ,kampanyaLinearLayout,asiTakipLinearLayout,linearLayoutSanalKarne;
    private ChangeFragments changeFragments;

    private GetSharedPreferences getSharedPreferences;
    private String id;
    private AnswerAdapter answerAdapter;
    private List<AnswerModel> listAnswer;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       view = inflater.inflate(R.layout.fragment_home, container, false);

        tanimla();
        Action();

        return view;
    }
    public void tanimla()
    {
        petlerimLinearLayout = (LinearLayout)view.findViewById(R.id.linearLayoutPetlerim);
        soruLinearLayout = (LinearLayout)view.findViewById(R.id.linearlayoutsorusor);
        cevaplarLinerlayout = (LinearLayout)view.findViewById(R.id.linearlayoutcevaplar);
        kampanyaLinearLayout = (LinearLayout)view.findViewById(R.id.linearlayoutKampanya);
        asiTakipLinearLayout = (LinearLayout)view.findViewById(R.id.LinearlayoutasiTakip);
        linearLayoutSanalKarne = (LinearLayout)view.findViewById(R.id.linearLayoutSanalKarne);

        getSharedPreferences = new GetSharedPreferences(getActivity());

        id = getSharedPreferences.getSession().getString("id",null);

        listAnswer = new ArrayList<>();
        //answerAdapter = new AnswerAdapter(listAnswer,getContext());


        changeFragments = new ChangeFragments(getActivity());
    }

    public void Action()
    {
        petlerimLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            changeFragments.change(new UserPetsFragment());
            }
        });

        soruLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQuestionAlert();
            }
        });
        cevaplarLinerlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswers(id);
            }
        });
        kampanyaLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragments.change(new KampanyaFragment());
            }
        });
        asiTakipLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragments.change(new AsiFragment());
            }
        });
        linearLayoutSanalKarne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragments.change(new SanalKarnePetler());
            }
        });
    }

   public void getAnswers(String mus_id)
   {
       final retrofit2.Call<List<AnswerModel>> req = ManagerAll.getInstance().getAnswers(mus_id);
       req.enqueue(new Callback<List<AnswerModel>>() {
           @Override
           public void onResponse(retrofit2.Call<List<AnswerModel>> call, Response<List<AnswerModel>> response) {

               if(response.body().get(0).isTf())
               {
                   if(response.isSuccessful())
                   {
                       Log.i("cevcaplarim",response.body().toString());

                       listAnswer = response.body();
                       answerAdapter = new AnswerAdapter(listAnswer,getContext());
                       openAnswerAlert();
                   }
               }else
               {
                   Toast.makeText(getContext(),"herhangi birşey donmedi",Toast.LENGTH_LONG).show();
               }
           }
           @Override
           public void onFailure(retrofit2.Call<List<AnswerModel>> call, Throwable t) {

               Toast.makeText(getActivity(),Warnings.INTERNETYOK,Toast.LENGTH_LONG).show();
           }
       });

   }

    public void openQuestionAlert()
    {
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sorusoralertlayout,null);

        final EditText sorusorText = (EditText)view.findViewById(R.id.ed_sorusor);
        final MaterialButton sorusorButton = (MaterialButton)view.findViewById(R.id.btn_sorusor);



        final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setView(view);
        alert.setCancelable(true);
        final AlertDialog alertDialog = alert.create();

        sorusorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            String soru = sorusorText.getText().toString();
                askQuestion(id,soru,alertDialog);
                alertDialog.cancel();

            }
        });

        alertDialog.show();
    }//FUNC

    public void openAnswerAlert()
    {
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.cevapalertlayout,null);

        RecyclerView cevapRecylerview = (RecyclerView) view.findViewById(R.id.recylerviewCevaplar);

        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setView(view);
        alert.setCancelable(true);

        final AlertDialog alertDialog = alert.create();

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        cevapRecylerview.setLayoutManager(layoutManager);
        cevapRecylerview.setAdapter(answerAdapter);

        alertDialog.show();

    }


    public void askQuestion(String mus_id, String text, final AlertDialog alertDialog)
    {
        final retrofit2.Call<AskQuestionModel> req = ManagerAll.getInstance().SoruSor(mus_id,text);
        req.enqueue(new Callback<AskQuestionModel>() {
            @Override
            public void onResponse(retrofit2.Call<AskQuestionModel> call, Response<AskQuestionModel> response) {

                if (response.body().isTf())
                {
                    Toast.makeText(getContext(),response.body().getText().toString(),Toast.LENGTH_LONG).show();
                    alertDialog.cancel();
                }else
                {
                    Toast.makeText(getContext(),response.body().getText().toString(),Toast.LENGTH_LONG).show();
                    alertDialog.cancel();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<AskQuestionModel> call, Throwable t) {
                Toast.makeText(getContext(),Warnings.INTERNETYOK,Toast.LENGTH_LONG).show();
            }
        });

    }
}
