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

public class PetsAdapter extends RecyclerView.Adapter<PetsAdapter.ViewHolder>{


    List<PetModel> list;
    Context context;


    public PetsAdapter(List<PetModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.petlistitemlayout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.petlayoutcinsname.setText(list.get(i).getPetcins().toString());
        viewHolder.petlayoutpetname.setText(list.get(i).getPetisim().toString());
        viewHolder.petlayoutturname.setText(list.get(i).getPettur().toString());

        Picasso.get().load(BaseURL.URL +""+list.get(i).getPetresim()).into(viewHolder.petlayoutpetImage);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView petlayoutpetname,petlayoutcinsname,petlayoutturname;
        CircleImageView petlayoutpetImage;

            //itemview ile listview in her elemanı için layout ile oluşturdugum view tanımlaması işlemi gerçekleşiyor.
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            petlayoutpetImage = (CircleImageView)itemView.findViewById(R.id.petlayoutpetimage);
            petlayoutpetname = itemView.findViewById(R.id.tv_petlayoutname);
            petlayoutcinsname = itemView.findViewById(R.id.tv_petlayoutcinsname);
            petlayoutturname = itemView.findViewById(R.id.tv_petlayoutturname);

        }
    }
}
