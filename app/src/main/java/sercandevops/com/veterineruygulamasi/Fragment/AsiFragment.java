package sercandevops.com.veterineruygulamasi.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.timessquare.CalendarPickerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sercandevops.com.veterineruygulamasi.Models.AsiModel;
import sercandevops.com.veterineruygulamasi.R;
import sercandevops.com.veterineruygulamasi.RestApi.BaseURL;
import sercandevops.com.veterineruygulamasi.RestApi.ManagerAll;
import sercandevops.com.veterineruygulamasi.Utils.GetSharedPreferences;


public class AsiFragment extends Fragment {

    View view;
    private CalendarPickerView calendarPickerView;
    private DateFormat format;
    private Calendar nextYear;
    private Date today;

    private List<AsiModel> asiList;
    private List<Date> dataList;

    String id;
    private GetSharedPreferences getSharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_asi, container, false);
            Tanimlama();
            getAsi();
            clickToCalendar();
        return view;
    }

    public void Tanimlama()
    {
        calendarPickerView = (CalendarPickerView)view.findViewById(R.id.calendarPickerview);
        format = new SimpleDateFormat("dd/MM/yyyy");
        nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR,1);
        today = new Date();

        calendarPickerView.init(today,nextYear.getTime());

        asiList = new ArrayList<>();
        dataList = new ArrayList<>();

        getSharedPreferences = new GetSharedPreferences(getActivity());
        id = getSharedPreferences.getSession().getString("id",null);


    }


    public void getAsi()
    {
        Call<List<AsiModel>> req = ManagerAll.getInstance().getAsilar(id);
        req.enqueue(new Callback<List<AsiModel>>() {
            @Override
            public void onResponse(Call<List<AsiModel>> call, Response<List<AsiModel>> response) {
                if(response.body().get(0).isTf())
                {
                    asiList  = response.body();
                    for(int i=0; i< asiList.size(); i++)
                    {

                        String dataString = response.body().get(i).getAsitarih().toString();
                        try{
                            Date date = format.parse(dataString);
                            dataList.add(date);

                        }catch (ParseException E)
                        {
                            E.printStackTrace();
                        }
                    }
                    calendarPickerView.init(today,nextYear.getTime()).withHighlightedDates(dataList);

                }else{
                    Toast.makeText(getContext(),"no basa",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<AsiModel>> call, Throwable t) {
                Toast.makeText(getContext(),"hata",Toast.LENGTH_LONG).show();
            }
        });


    }//func

    public void clickToCalendar()
    {
        calendarPickerView.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                for(int i=0;i<dataList.size(); i++)
                {
                    if(date.toString().equals(dataList.get(i).toString()))
                    {
                        //Toast.makeText(getContext(),"dogru",Toast.LENGTH_LONG).show();
                        openAsiAlertDialog(asiList.get(i).getPetisim().toString(),
                                            asiList.get(i).getAsitarih().toString(),
                                           asiList.get(i).getAsiisim().toString(),
                                            asiList.get(i).getPetresim().toString());
                    }
                }
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });
    }

    public void openAsiAlertDialog(String petIsmi,String tarih,String asiIsmi,String resimUrl)
    {
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.asitakiplayout,null);

        TextView petIsimText = view.findViewById(R.id.tv_petIsimText);
        TextView petAsiTakipBilgiText = view.findViewById(R.id.tv_petAsiTakipbilgiText);
        CircleImageView asiTakipCircleView = (CircleImageView) view.findViewById(R.id.img_asiTakipCircleImageView);

        petIsimText.setText(petIsmi);
        petAsiTakipBilgiText.setText(petIsmi+" isimli petinizin "+tarih+" tarihinde "+asiIsmi+" isimli aşısı yapılacaktır.");
        Picasso.get().load(BaseURL.URL+resimUrl).into(asiTakipCircleView);



        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setView(view);
        alert.setCancelable(true);

        final AlertDialog alertDialog = alert.create();


        alert.show();
    }

}
