package umn.ac.id.uas.project.retrofit;

import com.google.gson.Gson;

public class ApiErrorHandler {
    private String message;

    public String getMessage() {
        return message;
    }

    public static String getErrorMessage(String jsonError) {
        Gson gson = new Gson();
        String message = gson.fromJson(jsonError, ApiErrorHandler.class).getMessage();
        return message;
    }
}
