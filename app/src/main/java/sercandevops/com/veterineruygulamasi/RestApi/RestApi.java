package sercandevops.com.veterineruygulamasi.RestApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import sercandevops.com.veterineruygulamasi.Models.AnswerModel;
import sercandevops.com.veterineruygulamasi.Models.AsiModel;
import sercandevops.com.veterineruygulamasi.Models.AskQuestionModel;
import sercandevops.com.veterineruygulamasi.Models.DeleteAnswerModel;
import sercandevops.com.veterineruygulamasi.Models.KampanyaModel;
import sercandevops.com.veterineruygulamasi.Models.LoginModel;
import sercandevops.com.veterineruygulamasi.Models.PetModel;
import sercandevops.com.veterineruygulamasi.Models.RegisterPojo;

public interface RestApi {

    @FormUrlEncoded
    @POST("kayitol.php")
    public Call<RegisterPojo> registerUser(@Field("mailAdres")String mailAdres,@Field("kadi")String kadi,@Field("pass")String pass);


    @FormUrlEncoded
    @POST("girisyap.php")
    public Call<LoginModel> GirisYap(@Field("mailadres")String mailAdres, @Field("sifre")String pass);

    @FormUrlEncoded
    @POST("petlerim.php")
    public Call<List<PetModel>> getPets(@Field("mus_id")String mus_id);

    @FormUrlEncoded
    @POST("sorusor.php")
    public Call<AskQuestionModel> soruSor(@Field("id")String id, @Field("soru")String soru);

    @GET("cevaplar.php")
    public Call<List<AnswerModel>> getAnswers(@Query("mus_id") String mus_id);

    @GET("cevapsil.php")
    public Call<DeleteAnswerModel> deleteAnswer(@Query("soruid") String soruid, @Query("cevapid")String cevapid);

    @GET("kampanyalar.php")
    public Call<List<KampanyaModel>> getKampanya();

    @GET("asitakip.php")
    public Call<List<AsiModel>> getAsilar(@Query("mus_id") String mus_id);



}
