package my.toru.advancedmvvm.example.repository;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by toruchoi on 11/10/2017.
 */

public class ApiBaseRepository {
    private static final String TAG = ApiBaseRepository.class.getSimpleName();
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
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .writeTimeout(5000, TimeUnit.MILLISECONDS)
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
}
