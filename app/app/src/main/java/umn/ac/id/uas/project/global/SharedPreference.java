package umn.ac.id.uas.project.global;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import umn.ac.id.uas.project.model.UserModel;

public class SharedPreference {
    public static String key = "umn.ac.id.mastercomp.user";

    public static UserModel getUser(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(key, context.MODE_PRIVATE);
        String user = sharedPreferences.getString("user", null);
        return new Gson().fromJson(user, UserModel.class);
    }

    public static String getToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(key, context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);
        return token;
    }

    public static void setUser(Context context, UserModel user) {
        SharedPreferences.Editor editor = context.getSharedPreferences(key, context.MODE_PRIVATE).edit();
        String userJson = new Gson().toJson(user);
        editor.putString("user", userJson);

        editor.commit();
    }

    public static void setToken(Context context, String token) {
        SharedPreferences.Editor editor = context.getSharedPreferences(key, context.MODE_PRIVATE).edit();
        editor.putString("token", token);

        editor.commit();
    }

    public static void logout(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(key, context.MODE_PRIVATE).edit();
        editor.remove("token");
        editor.remove("user");

        editor.commit();
    }
}
