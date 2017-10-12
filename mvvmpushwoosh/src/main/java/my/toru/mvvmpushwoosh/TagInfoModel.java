package my.toru.mvvmpushwoosh;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by toruchoi on 12/10/2017.
 */

public class TagInfoModel extends BaseObservable{
    private String name;
    private String type;
    private String pushtoken;
    private String description;

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Bindable
    public String getPushtoken() {
        return pushtoken;
    }

    public void setPushtoken(String pushtoken) {
        this.pushtoken = pushtoken;
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}