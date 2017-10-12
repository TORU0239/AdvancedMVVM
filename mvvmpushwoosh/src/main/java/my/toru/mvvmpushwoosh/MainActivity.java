package my.toru.mvvmpushwoosh;

import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import java.util.List;

import my.toru.mvvmpushwoosh.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private ActivityMainBinding binding;

    private ObservableField<List<TagInfoModel>> tagInfoModelList = new ObservableField<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        tagInfoModelList.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                Log.w(TAG, "onPropertyChanged: size: " + tagInfoModelList.get().size());
                ((MainAdapter)(binding.rcvMain.getAdapter())).updateModelList(tagInfoModelList.get());
                binding.rcvMain.getAdapter().notifyDataSetChanged();
            }
        });
        binding.setViewModel(new MainViewModel(tagInfoModelList));
        binding.rcvMain.setLayoutManager(new LinearLayoutManager(this));
        MainAdapter adapter = new MainAdapter(tagInfoModelList.get());
        binding.rcvMain.setAdapter(adapter);
    }
}