package com.hosplanet.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.hosplanet.common.util.NetworkUtil;

/**
 * Created by hyunwoo-Lee on 2015-12-18.
 */
public class NetworkReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        int status = NetworkUtil.getNetworkStatus(context);
        Log.e("NETWORK RECEIVER","NETWORK RECEIVER");
        Log.i("intentAction", intent.getAction());
        Log.i("status",Integer.toString(status));
        if (!"android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            if(status == NetworkUtil.NETWORK_STATUS_NOT_CONNECTED){
                Toast.makeText(context, "네트워크 상태가 불안정 합니다.", Toast.LENGTH_LONG).show();
            }
        }

    }
}
