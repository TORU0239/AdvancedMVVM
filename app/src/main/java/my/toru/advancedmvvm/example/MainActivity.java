package my.toru.advancedmvvm.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import my.toru.advancedmvvm.R;
import my.toru.advancedmvvm.example.model.MyTagModel;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        final MenuItem searchMenuItem =  menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.w(TAG, "query:" + query);

                HashMap<String, RequestModel> tagQueryModel = new HashMap<>();
                RequestModel requestModel = new RequestModel();
                requestModel.setApplication("APP_ID");
                requestModel.setHwid("97476d339300fb63");
                tagQueryModel.put("request",requestModel);

                disposable.add(viewModel.getTagsModel(tagQueryModel)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .map(new Function<ResponseModel<MyTagModel>, MyTagModel>() {
                            @Override
                            public MyTagModel apply(ResponseModel<MyTagModel> myTagModelResponseModel) throws Exception {
                                return myTagModelResponseModel.getResponse();
                            }
                        })
                        .subscribe(new Consumer<MyTagModel>() {
                            @Override
                            public void accept(MyTagModel myTagModel) throws Exception {

                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                throwable.printStackTrace();
                            }
                        }, new Action() {
                            @Override
                            public void run() throws Exception {}
                        })
                );


                if( ! searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                searchMenuItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    private void initViewModel(){
        viewModel = new ExampleViewModel();
    }

    private void bindViewModel(){
        disposable = new CompositeDisposable();
    }

    private void unbindViewModel(){
        if(!disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}