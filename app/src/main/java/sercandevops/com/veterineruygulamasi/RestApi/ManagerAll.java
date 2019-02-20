package sercandevops.com.veterineruygulamasi.RestApi;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import sercandevops.com.veterineruygulamasi.Models.AnswerModel;
import sercandevops.com.veterineruygulamasi.Models.AsiModel;
import sercandevops.com.veterineruygulamasi.Models.AskQuestionModel;
import sercandevops.com.veterineruygulamasi.Models.DeleteAnswerModel;
import sercandevops.com.veterineruygulamasi.Models.KampanyaModel;
import sercandevops.com.veterineruygulamasi.Models.LoginModel;
import sercandevops.com.veterineruygulamasi.Models.PetModel;
import sercandevops.com.veterineruygulamasi.Models.RegisterPojo;

public class ManagerAll extends BaseManager {

        private static ManagerAll ourInstance = new ManagerAll();

        public static synchronized ManagerAll getInstance()
        {
            return ourInstance;
        }


        public Call<RegisterPojo> kayitOl(String mail,String kadi,String parola)
        {
         Call<RegisterPojo> x = getRestApi().registerUser(mail,kadi,parola);
        return x;
        }

    public Call<LoginModel> GirisYap(String mail, String parola)
    {
        Call<LoginModel> x = getRestApi().GirisYap(mail,parola);
        return x;
    }

    public Call<List<PetModel>> Petlerim(String mus_id)
    {
        Call<List<PetModel>> x = getRestApi().getPets(mus_id);
        return x;
    }

    public Call<AskQuestionModel> SoruSor(String id, String text)
    {
        Call<AskQuestionModel> x = getRestApi().soruSor(id,text);
        return x;
    }

    public Call<List<AnswerModel>> getAnswers(String mus_id)
    {
        Call<List<AnswerModel>> x = getRestApi().getAnswers(mus_id);
        return x;
    }

    public Call<DeleteAnswerModel> deleteAnswer(String soruid, String cevapid)
    {
        Call<DeleteAnswerModel> x = getRestApi().deleteAnswer(soruid,cevapid);
        return x;
    }

    public Call<List<KampanyaModel>> getKampanya()
    {
        Call<List<KampanyaModel>> x = getRestApi().getKampanya();
        return x;
    }

    public Call<List<AsiModel>> getAsilar(String mus_id)
    {
        Call<List<AsiModel>> x = getRestApi().getAsilar(mus_id);
        return x;
    }

    public Call<List<AsiModel>> asiGecmisi(String musId, String petId)
    {
        Call<List<AsiModel>> x = getRestApi().getAsGecmisi(musId,petId);
        return x;
    }
}
