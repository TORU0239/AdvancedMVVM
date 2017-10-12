package my.toru.mvvmpushwoosh;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import my.toru.mvvmpushwoosh.repository.ApiBaseRepository;
import my.toru.mvvmpushwoosh.repository.model.ResponseModel;
import my.toru.mvvmpushwoosh.repository.model.TagListRequestModel;
import retrofit2.Retrofit;

/**
 * Created by toruchoi on 12/10/2017.
 */

public class MainViewModel extends BaseObservable{

    // this part update View due to change of Model.
    private ObservableField<List<TagInfoModel>> tagInfoModelList;

    public MainViewModel(ObservableField<List<TagInfoModel>> tagInfoModelList) {
        this.tagInfoModelList = tagInfoModelList;
    }

    public View.OnClickListener fabListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Fab", Toast.LENGTH_SHORT).show();

            Retrofit retrofit = ApiBaseRepository.getInstance("https://cp.pushwoosh.com/json/1.3/").getRetrofit();
            ApiBaseRepository.IPushwooshService service = retrofit.create(ApiBaseRepository.IPushwooshService.class);

            HashMap<String, TagListRequestModel> tagQueryModel = new HashMap<>();
            TagListRequestModel requestModel = new TagListRequestModel();
            requestModel.setApplication("FCB57-748C5");
            requestModel.setAuth("hv0RjV5zrQWniE6V3VzJgv3fLr7AkpGOMGEblVANvsrNMhZLaBNpqIM8s1FVAoCkfyIJakVkV5gEiPXDBkeP");
            tagQueryModel.put("request",requestModel);
            service.getTagListService(tagQueryModel)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .map(new Function<ResponseModel<List<TagInfoModel>>, List<TagInfoModel>>() {
                        @Override
                        public List<TagInfoModel> apply(ResponseModel<List<TagInfoModel>> listResponseModel) throws Exception {
                            return listResponseModel.getResponse();
                        }
                    })
                    .subscribe(new Consumer<List<TagInfoModel>>() {
                        @Override
                        public void accept(List<TagInfoModel> tagInfoModels) throws Exception {
                            tagInfoModelList.set(tagInfoModels);
                        }
                    });
        }
    };
}