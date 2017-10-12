package my.toru.mvvmpushwoosh.repository.model;


import my.toru.library.model.MvvmModel;

/**
 * Created by toruchoi on 11/10/2017.
 */

public class TagListRequestModel extends MvvmModel {
    private static final String TAG = TagListRequestModel.class.getSimpleName();

    private String application;
    private String auth;

    @Override
    public String toString() {
        return "request:: application : " + application + ", auth : " + auth;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}