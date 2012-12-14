package net.dirong.turbo.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.net.ConnectivityManager.TYPE_WIFI;

public class ConnectivityHelper {

    private ConnectivityManager connectivityManager;

    public ConnectivityHelper(Context ctx) {
        connectivityManager = (ConnectivityManager) ctx
                .getSystemService(CONNECTIVITY_SERVICE);
    }

    public boolean isConnected() {
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public boolean onWiFi() {
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return isConnected() && networkInfo.getType() == TYPE_WIFI;
    }

}