package com.hosplanet.common.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by hyunwoo-Lee on 2015-12-18.
 */
public class NetworkUtil {
    public static final int NETWORK_STATUS_NOT_CONNECTED=0,NETWORK_STAUS_WIFI=1,NETWORK_STATUS_MOBILE=2;


    public static int getNetworkStatus(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if(null != networkInfo){
            if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI){
                return NETWORK_STAUS_WIFI;
            }else if(networkInfo.getType() == ConnectivityManager.TYPE_MOBILE){
                return NETWORK_STATUS_MOBILE;
            }
        }
        return NETWORK_STATUS_NOT_CONNECTED;
    }

}
