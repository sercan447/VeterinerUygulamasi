package sercandevops.com.veterineruygulamasi.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;
import sercandevops.com.veterineruygulamasi.Adapters.SanalKarneGecmisAdapter;
import sercandevops.com.veterineruygulamasi.Models.AsiModel;
import sercandevops.com.veterineruygulamasi.R;
import sercandevops.com.veterineruygulamasi.RestApi.ManagerAll;
import sercandevops.com.veterineruygulamasi.Utils.GetSharedPreferences;


public class AsiDetayFragment extends Fragment {

    View v;
    private String musId;
    private String petId;
    private GetSharedPreferences getSharedPreferences;
    RecyclerView recyclerView;
    private List<AsiModel> asimodelList;
    SanalKarneGecmisAdapter sanalKarneGecmisAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v =  inflater.inflate(R.layout.fragment_asi_detay, container, false);
        Tanimlamalar();
        gecmisAsi();
        return v;
    }

    public void Tanimlamalar()
    {
        petId = getArguments().getString("petId").toString();
        getSharedPreferences = new GetSharedPreferences(getActivity());
        musId = getSharedPreferences.getSession().getString("id",null);
        recyclerView = v.findViewById(R.id.recyclerAsiDetay);
         RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);

         recyclerView.setLayoutManager(layoutManager);

         asimodelList = new ArrayList<>();

    }


    public void gecmisAsi()
    {
        retrofit2.Call<List<AsiModel>> req = ManagerAll.getInstance().asiGecmisi(musId,petId);
            req.enqueue(new Callback<List<AsiModel>>() {
                @Override
                public void onResponse(retrofit2.Call<List<AsiModel>> call, Response<List<AsiModel>> response) {
                    if(response.isSuccessful())
                    {
                        if(response.body().get(0).isTf())
                        {
                            asimodelList = response.body();
                            sanalKarneGecmisAdapter = new SanalKarneGecmisAdapter(getContext(),asimodelList);

                            recyclerView.setAdapter(sanalKarneGecmisAdapter);

                            Toast.makeText(getContext(),"Petinze ait "+asimodelList.size()+" adet geçmişte aşınızın bulunmaktadır.",Toast.LENGTH_LONG).show();
                        }else
                        {
                            Toast.makeText(getContext(),"Petinize ait geçmiş aşı bulunmamaktadır.",Toast.LENGTH_LONG).show();
                        }
                    }else
                    {

                    }
                    Log.i("gecmisasilar",response.body().get(0).toString());
                }

                @Override
                public void onFailure(retrofit2.Call<List<AsiModel>> call, Throwable t) {
                    Log.i("gecmisasilar","hatalandı"+t.getMessage().toString());
                }
            });

    }

}
