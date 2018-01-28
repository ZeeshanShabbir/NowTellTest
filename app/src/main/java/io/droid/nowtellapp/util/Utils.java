package io.droid.nowtellapp.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;

/**
 * Created by Zeeshan on 1/24/2018.
 */

public class Utils {
    /**
     * To check if String is null or empty
     *
     * @param message
     * @return
     */
    public static boolean isNullOrEmpty(String message) {
        return message == null || message.trim().isEmpty();
    }

    /**
     * Returns encoded auth header
     *
     * @return
     */
    public static String getAuthorizationToken() {
        String userpass = "betatest : j6vbV7EcnxCwheyM";
        return "Basic " + Base64.encodeToString(userpass.getBytes(), Base64.DEFAULT);
    }


    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
