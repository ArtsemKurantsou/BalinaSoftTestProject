package com.kurantsou.balinasofttestproject.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.kurantsou.balinasofttestproject.model.UserCredentials;

/**
 * Created by artem on 08.08.2017.
 */

public class SharedPreferencesUtil {

    private static final String TAG = "SharedPreferencesUtil";

    private static final String APP_PREFS_FILE_NAME = "BalinaSoftTestProjectPrefs";

    private static final String USER_CREDENTIALS = "userCredentials";
    private static final String TOKEN = "autorize_token";

    public static UserCredentials getSavedCredentials(Context context) {
        SharedPreferences prefs = getSharedPreferences(context);

        String savedCredentials = prefs.getString(USER_CREDENTIALS, null);

        if (savedCredentials == null || savedCredentials.isEmpty())
            return null;

        try {
            return new Gson().fromJson(savedCredentials, UserCredentials.class);
        } catch (JsonSyntaxException e) {
            Log.e(TAG, "getSavedCredentials: ", e);
            return null;
        }
    }
    public static void saveCredentials(Context context, UserCredentials credentials){
        getSharedPreferences(context)
                .edit()
                .putString(USER_CREDENTIALS, new Gson().toJson(credentials))
                .apply();
    }

    public static String getToken(Context context){
        return getSharedPreferences(context)
                .getString(TOKEN, null);
    }

    public static void saveToken(Context context, String token){
        getSharedPreferences(context)
                .edit()
                .putString(TOKEN, token)
                .apply();
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getApplicationContext().getSharedPreferences(APP_PREFS_FILE_NAME, Context.MODE_PRIVATE);
    }
}
