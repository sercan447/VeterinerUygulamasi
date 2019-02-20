package sercandevops.com.veterineruygulamasi.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import sercandevops.com.veterineruygulamasi.Models.AsiModel;
import sercandevops.com.veterineruygulamasi.R;
import sercandevops.com.veterineruygulamasi.RestApi.BaseURL;

public class SanalKarneGecmisAdapter extends RecyclerView.Adapter<SanalKarneGecmisAdapter.MyViewHolder>{

    Context context;
    List<AsiModel> liste;

    public SanalKarneGecmisAdapter(Context context, List<AsiModel> liste) {
        this.context = context;
        this.liste = liste;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.sanalkarnegecmislayout,viewGroup,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.tv_GecmisSanalKarneAsiIsmi2.setText(liste.get(i).getAsiisim().toString()+" aşısı yapılmıştır.");
        myViewHolder.tv_GecmisSanalKarneBilgiText.setText(liste.get(i).getPetisim().toString().toUpperCase()+ " isimli "+
                "petiiniz ait türüne "+ liste.get(i).getAsitarih()+" tarihin de "+liste.get(i).getAsiisim()+ " aşısı yapılmıştır.");

        Picasso.get().load(BaseURL.URL+liste.get(i).getPetresim()).into(myViewHolder.img_GecmisAsiTakipCircleImageview2);
    }

    @Override
    public int getItemCount() {
        return liste.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView tv_GecmisSanalKarneAsiIsmi2,tv_GecmisSanalKarneBilgiText;
        ImageView img_GecmisAsiTakipCircleImageview2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_GecmisSanalKarneAsiIsmi2 = itemView.findViewById(R.id.tv_GecmisSanalKarneAsiIsmi2);
            tv_GecmisSanalKarneBilgiText = itemView.findViewById(R.id.tv_GecmisSanalKarneBilgiText);
            img_GecmisAsiTakipCircleImageview2 = (ImageView)itemView.findViewById(R.id.img_GecmisAsiTakipCircleImageview2);

        }


    }//INNER CLASS
}
