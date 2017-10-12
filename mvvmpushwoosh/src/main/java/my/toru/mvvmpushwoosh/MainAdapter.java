package my.toru.mvvmpushwoosh;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import my.toru.mvvmpushwoosh.databinding.AdapterMainBinding;

/**
 * Created by toruchoi on 12/10/2017.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder>{
    private static final String TAG = MainAdapter.class.getSimpleName();

    private List<TagInfoModel> modelList;

    public void updateModelList(List<TagInfoModel> modelList) {
        if(this.modelList == null){
            this.modelList = new ArrayList<>();
        }
        this.modelList.addAll(modelList);
    }

    public MainAdapter(List<TagInfoModel> modelList) {
        this.modelList = modelList;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterMainBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.adapter_main, parent, false);
        return new MainViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        if(modelList == null) return;
        holder.getViewDataBinding().setMainModel(modelList.get(position));
    }

    @Override
    public int getItemCount() {
        if(modelList == null) return 0;
        return modelList.size();
    }

    public static class MainViewHolder extends RecyclerView.ViewHolder{

        private AdapterMainBinding viewDataBinding;

        public MainViewHolder(AdapterMainBinding binding) {
            super(binding.getRoot());
            viewDataBinding = binding;
            viewDataBinding.executePendingBindings();
        }

        public AdapterMainBinding getViewDataBinding() {
            return viewDataBinding;
        }
    }
}