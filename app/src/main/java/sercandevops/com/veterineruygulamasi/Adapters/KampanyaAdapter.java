package sercandevops.com.veterineruygulamasi.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.List;

import sercandevops.com.veterineruygulamasi.Models.KampanyaModel;
import sercandevops.com.veterineruygulamasi.R;
import sercandevops.com.veterineruygulamasi.RestApi.BaseURL;

public class KampanyaAdapter extends RecyclerView.Adapter<KampanyaAdapter.ViewHolder> {


    List<KampanyaModel> liste;
    Context context;

    public KampanyaAdapter(List<KampanyaModel> liste, Context context) {
        this.liste = liste;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.kampanyaitemlayout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.tv_kampanyaBaslikText.setText(liste.get(i).getBaslik().toString());
        viewHolder.tv_kampanyaText.setText(liste.get(i).getText().toString());

        Picasso.get().load(BaseURL.URL+liste.get(i).getResim().toString()).into(viewHolder.img_KampanyaImge);
    }

    @Override
    public int getItemCount() {
        return liste.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_kampanyaBaslikText,tv_kampanyaText;
        ImageView img_KampanyaImge;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_kampanyaBaslikText = (TextView)itemView.findViewById(R.id.tv_kampanya_baslikTxt);
            tv_kampanyaText = (TextView)itemView.findViewById(R.id.tv_kampanyaText);
            img_KampanyaImge = (ImageView)itemView.findViewById(R.id.img_kampanyaImageview);

        }
    }//CLASS

}
