package sercandevops.com.veterineruygulamasi.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sercandevops.com.veterineruygulamasi.Adapters.KampanyaAdapter;
import sercandevops.com.veterineruygulamasi.Models.KampanyaModel;
import sercandevops.com.veterineruygulamasi.R;
import sercandevops.com.veterineruygulamasi.RestApi.ManagerAll;
import sercandevops.com.veterineruygulamasi.Utils.ChangeFragments;


public class KampanyaFragment extends Fragment {


    private View view;
    private RecyclerView kampanyaRecylerview;
    private ChangeFragments changeFragments;
    private KampanyaAdapter kampanyaAdapter;
    private List<KampanyaModel> kampanyaList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_kampanya, container, false);

        Tanimlama();
        getKampanya();

        return view;
    }

    public void Tanimlama()
    {
        kampanyaRecylerview = (RecyclerView)view.findViewById(R.id.recylerviewKampanya);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        kampanyaRecylerview.setLayoutManager(layoutManager);

        changeFragments = new ChangeFragments(getContext());
        kampanyaList = new ArrayList<>();

    }//FUNC

    public void getKampanya()
    {
        Call<List<KampanyaModel>> req = ManagerAll.getInstance().getKampanya();
        req.enqueue(new Callback<List<KampanyaModel>>() {
            @Override
            public void onResponse(Call<List<KampanyaModel>> call, Response<List<KampanyaModel>> response) {

                if(response.body().get(0).isTf())
                {
                    kampanyaList = response.body();
                    kampanyaAdapter = new KampanyaAdapter(kampanyaList,getContext());
                    kampanyaRecylerview.setAdapter(kampanyaAdapter);

                    Toast.makeText(getActivity(),"BAŞARILI",Toast.LENGTH_LONG).show();
                }else{
                    changeFragments.change(new HomeFragment());
                }
            }

            @Override
            public void onFailure(Call<List<KampanyaModel>> call, Throwable t) {
                Toast.makeText(getActivity(),"HATA.",Toast.LENGTH_LONG).show();
            }
        });
    }//Kampanya


}
