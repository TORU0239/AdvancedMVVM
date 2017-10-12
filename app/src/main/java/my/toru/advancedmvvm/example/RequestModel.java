package my.toru.advancedmvvm.example;

import my.toru.advancedmvvm.model.MvvmModel;

/**
 * Created by toruchoi on 11/10/2017.
 */

public class RequestModel extends MvvmModel {
    private static final String TAG = RequestModel.class.getSimpleName();

    private String application;
    private String hwid;

    @Override
    public String toString() {
        return "request:: application : " + application + ", hwid : " + hwid;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getHwid() {
        return hwid;
    }

    public void setHwid(String hwid) {
        this.hwid = hwid;
    }
}