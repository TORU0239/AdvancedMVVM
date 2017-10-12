package my.toru.advancedmvvm.example;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import my.toru.advancedmvvm.example.model.MyTagModel;
import my.toru.advancedmvvm.example.model.RequestModel;
import my.toru.advancedmvvm.example.model.ResponseModel;
import my.toru.advancedmvvm.example.repository.ApiBaseRepository;

import my.toru.library.viewmodel.MvvmViewModel;
import retrofit2.Retrofit;

/**
 * Created by toruchoi on 11/10/2017.
 *
 * ViewModel has only Business Logic, and View can be updated automatically due to change of Model.
 * Commander Pattern and Observer Pattern
 *
 */

public class ExampleViewModel implements MvvmViewModel {
    private static final String TAG = ExampleViewModel.class.getSimpleName();

    // Consumption of API
    public Observable<ResponseModel<MyTagModel>> getTagsModel(HashMap<String, RequestModel> queryModel){
        Retrofit retrofit = ApiBaseRepository.getInstance("https://cp.pushwoosh.com/json/1.3/").getRetrofit();

        ApiBaseRepository.IPushwooshService service = retrofit.create(ApiBaseRepository.IPushwooshService.class);
        return service.getTagService(queryModel)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public void onGoToSearch(String hwid){

    }
}