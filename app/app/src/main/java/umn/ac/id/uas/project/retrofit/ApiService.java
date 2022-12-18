package umn.ac.id.uas.project.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    static String LOCAL_BASE_API_URL = "http://10.0.2.2:8000/api/";
    static String LOCAL_BASE_URL = "http://10.0.2.2:8000/";

    static String PRODUCTION_BASE_API_URL = "https://rakitpc.atras.my.id/public/api/";
    static String PRODUCTION_BASE_URL = "https://rakitpc.atras.my.id/public/";

    static String BASE_API_URL = PRODUCTION_BASE_API_URL;
    static String BASE_URL = PRODUCTION_BASE_URL;

    public static ApiEndpoint endpoint() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiEndpoint.class);
    }

    public static String getBaseApiUrl() {
        return BASE_API_URL;
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }
}
