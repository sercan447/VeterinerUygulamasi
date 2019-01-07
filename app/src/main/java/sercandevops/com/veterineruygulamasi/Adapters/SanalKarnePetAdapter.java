package sercandevops.com.veterineruygulamasi.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import sercandevops.com.veterineruygulamasi.Models.PetModel;
import sercandevops.com.veterineruygulamasi.R;
import sercandevops.com.veterineruygulamasi.RestApi.BaseURL;

public class SanalKarnePetAdapter extends RecyclerView.Adapter<SanalKarnePetAdapter.ViewHolder>{

    Context context;
    List<PetModel> petModelList;


    public SanalKarnePetAdapter(Context context, List<PetModel> petModelList) {
        this.context = context;
        this.petModelList = petModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.sanalkarnepetlayout,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.tv_SanalKarnePetler.setText(petModelList.get(i).getPetisim());

        viewHolder.tv_sanalKarneBilgiText.setText(petModelList.get(i).getPetisim().toString()+" türlü "
                        + petModelList.get(i).getPetcins().toString()
                            +" li petinize ait geçmiş aşıları görmek için tıklayınız");


        Picasso.get().load(BaseURL.URL+petModelList.get(i).getPetresim().toString()).into(viewHolder.img_asiTakipCircleImageview);
    }

    @Override
    public int getItemCount() {
        return petModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_SanalKarnePetler,tv_sanalKarneBilgiText;
        CircleImageView img_asiTakipCircleImageview;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_SanalKarnePetler = (TextView)itemView.findViewById(R.id.tv_SanalKarnePetler);
            tv_sanalKarneBilgiText = (TextView)itemView.findViewById(R.id.tv_sanalKarneBilgiText);
            img_asiTakipCircleImageview = (CircleImageView)itemView.findViewById(R.id.img_asiTakipCircleImageview2);


        }
    }//CLASS
}
