package my.toru.advancedmvvm.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import my.toru.advancedmvvm.R;
import my.toru.advancedmvvm.example.model.RequestModel;
import my.toru.advancedmvvm.example.model.ResponseModel;


/*
 * Activity is equivalent to View.
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private CompositeDisposable disposable;
    private ExampleViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewModel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bindViewModel();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindViewModel();
    }

    private void initViewModel(){
        viewModel = new ExampleViewModel();
    }

    private void bindViewModel(){
        HashMap<String, RequestModel> query = new HashMap<>();
        RequestModel requestModel = new RequestModel();
        requestModel.setApplication("FCB57-748C5");
        requestModel.setHwid("97476d339300fb63");
        query.put("request",requestModel);

        disposable = new CompositeDisposable();
        disposable.add(viewModel.getTagsModel(query)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Consumer<ResponseModel>() {
                            @Override
                            public void accept(ResponseModel responseModel) throws Exception {
                                Log.w(TAG, "accept: " + responseModel.toString());
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                throwable.printStackTrace();
                            }
                        }, new Action() {
                            @Override
                            public void run() throws Exception {

                            }
                        }));
    }

    private void unbindViewModel(){
        if(!disposable.isDisposed()){
            disposable.dispose();
        }
    }
}
