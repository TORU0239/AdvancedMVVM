package my.toru.advancedmvvm.example.repository;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import my.toru.advancedmvvm.example.model.RequestModel;
import my.toru.advancedmvvm.example.model.ResponseModel;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by toruchoi on 11/10/2017.
 */

public class ApiBaseRepository {
    private static final String TAG = ApiBaseRepository.class.getSimpleName();
    private static int TIMEOUT = 5000;
    private static ApiBaseRepository retrofitApiBase;

    private Retrofit retrofit;

    private ApiBaseRepository(String baseUrl){
        init(baseUrl);
    }

    public static ApiBaseRepository getInstance(String baseUrl){
        if(retrofitApiBase == null){
            retrofitApiBase = new ApiBaseRepository(baseUrl);
        }
        return retrofitApiBase;
    }

    private void init(String baseUrl){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public interface IPushwooshService {
        @POST("getTags")
        Observable<ResponseModel> getTagService(@Body Map<String, RequestModel> query);
    }
}