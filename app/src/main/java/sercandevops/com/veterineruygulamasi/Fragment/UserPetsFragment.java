package sercandevops.com.veterineruygulamasi.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sercandevops.com.veterineruygulamasi.Activities.MainActivity;
import sercandevops.com.veterineruygulamasi.Adapters.PetsAdapter;
import sercandevops.com.veterineruygulamasi.Models.PetModel;
import sercandevops.com.veterineruygulamasi.R;
import sercandevops.com.veterineruygulamasi.RestApi.ManagerAll;
import sercandevops.com.veterineruygulamasi.Utils.ChangeFragments;
import sercandevops.com.veterineruygulamasi.Utils.GetSharedPreferences;

public class UserPetsFragment extends Fragment {


    View view;
    private RecyclerView petlisterecylerview;
    private PetsAdapter petsAdapter;
    private List<PetModel> petlist;
    private ChangeFragments changeFragments;
    private String mus_id;
    private GetSharedPreferences getSharedPreferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_user_pets, container, false);

        Tanimalama();
        getPets(mus_id);


        return view;
    }

    public void Tanimalama()
    {
        petlist = new ArrayList<>();
        petlisterecylerview = view.findViewById(R.id.recyclerview_userpetsFrag);
        RecyclerView.LayoutManager mng = new GridLayoutManager(getContext(),1);
        petlisterecylerview.setLayoutManager(mng);

        changeFragments = new ChangeFragments(getContext());

        getSharedPreferences = new GetSharedPreferences(getActivity());
        mus_id = getSharedPreferences.getSession().getString("id",null);


    }


    public void getPets(String mus_id)
    {
        Call<List<PetModel>> req = ManagerAll.getInstance().Petlerim(mus_id);
        req.enqueue(new Callback<List<PetModel>>() {
            @Override
            public void onResponse(Call<List<PetModel>> call, Response<List<PetModel>> response) {
                Log.i("TAG","PETLER GELDİ");
                Log.i("TAG",response.body().toString());

                if(response.body().get(0).isTf())
                {
                    petlist = response.body();
                    petsAdapter = new PetsAdapter(petlist,getContext());

                    petlisterecylerview.setAdapter(petsAdapter);


                }else
                    {
                        Toast.makeText(getActivity(),"Sistme kayıtlı petiniz bulunmamaktadır.",Toast.LENGTH_LONG).show();
                        changeFragments.change(new HomeFragment());
                    }
            }

            @Override
            public void onFailure(Call<List<PetModel>> call, Throwable t) {
                Log.i("TAG","PETLER GELMEDI");
            }
        });
    }


}
