package sercandevops.com.veterineruygulamasi.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sercandevops.com.veterineruygulamasi.Models.AnswerModel;
import sercandevops.com.veterineruygulamasi.Models.DeleteAnswerModel;
import sercandevops.com.veterineruygulamasi.R;
import sercandevops.com.veterineruygulamasi.RestApi.ManagerAll;

public class AnswerAdapter extends  RecyclerView.Adapter<AnswerAdapter.ViewHolder>{

    List<AnswerModel> list;
    Context context;

    public AnswerAdapter(List<AnswerModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.cevapitemlayout,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.tv_cevapInSoru.setText(list.get(i).getSoru().toString());
        viewHolder.tv_cevapInCevap.setText(list.get(i).getCevap().toString());


        viewHolder.btn_cvpsil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG","tıklandı "+i);
                delete(list.get(i).getSoruid().toString(),list.get(i).getCevapid().toString());
                deleteToList(i);
            }
        });

    }

    public void delete(String soruid,String cevapid)
    {
        final Call<DeleteAnswerModel> req = ManagerAll.getInstance().deleteAnswer(soruid,cevapid);
        req.enqueue(new Callback<DeleteAnswerModel>() {
            @Override
            public void onResponse(Call<DeleteAnswerModel> call, Response<DeleteAnswerModel> response) {
                    if(response.body().isTf())
                    {
                        Log.i("TAG","başarı");

                    }else{
                        Log.i("TAG","başarısız");
                    }
            }

            @Override
            public void onFailure(Call<DeleteAnswerModel> call, Throwable t) {
                Log.i("TAG","hata"+t.getMessage());
            }
        });
    }//func


    public void deleteToList(int position)
    {
        list.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_cevapInSoru,tv_cevapInCevap;
        Button btn_cvpsil;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_cevapInCevap = (TextView) itemView.findViewById(R.id.tv_cevapInCevap);
            tv_cevapInSoru =  (TextView) itemView.findViewById(R.id.tv_cevapInSoru);
            btn_cvpsil = (Button) itemView.findViewById(R.id.btn_cvpsil);

        }
    }//CLASSS
}
