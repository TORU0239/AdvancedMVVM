package my.toru.advancedmvvm.example.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by toruchoi on 12/10/2017.
 */

public class MyTagModel {
    private TagResponse result;

    public static class TagResponse{
        @SerializedName("Language")
        String language;

        @SerializedName("Last Application Open")
        String lastApplicationOpen;

        @SerializedName("Application Version")
        String appVersion;

        @SerializedName("Country")
        String country;

        @SerializedName("City")
        String city;

        @SerializedName("First Install")
        String firstInstall;

        @SerializedName("Name")
        String name;

        @SerializedName("Age")
        String age;
    }
}
