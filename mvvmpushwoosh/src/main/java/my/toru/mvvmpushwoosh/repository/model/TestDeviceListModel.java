package my.toru.mvvmpushwoosh.repository.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import my.toru.mvvmpushwoosh.TagInfoModel;

/**
 * Created by toruchoi on 13/10/2017.
 */

public class TestDeviceListModel {
    @SerializedName("TestDevices")
    private List<TagInfoModel> testDeviceList;

    public List<TagInfoModel> getTestDeviceList() {
        return testDeviceList;
    }
}
