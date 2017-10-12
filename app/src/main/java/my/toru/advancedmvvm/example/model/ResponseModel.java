package my.toru.advancedmvvm.example.model;

import com.google.gson.annotations.SerializedName;

import my.toru.advancedmvvm.model.MvvmModel;

/**
 * Created by toruchoi on 11/10/2017.
 */

public class ResponseModel extends MvvmModel {
    private static final String TAG = ResponseModel.class.getSimpleName();

    @SerializedName("status_code")
    private String statusCode;

    @SerializedName("status_message")
    private String statusMessage;

    private MyTagModel response;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public MyTagModel getResponse() {
        return response;
    }

    public void setResponse(MyTagModel response) {
        this.response = response;
    }
}
