package com.hosplanet.hoplanet.api;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by hyunwoo-Lee on 2015-12-16.
 */
public class HospitalInfoAsyncTask extends AsyncTask<HospitalInfoApiBean,Void,JSONObject>{
    public AsyncResponse delegate = null;
    public HospitalInfoAsyncTask(AsyncResponse asyncResponse){
        delegate = asyncResponse;
    }

    @Override
    protected JSONObject doInBackground(HospitalInfoApiBean... params) {
        HospitalInfoApiClient client = new HospitalInfoApiClient();
        HospitalInfoApiBean param = params[0];
        JSONObject h = null;
        try{
            h = client.getHostPitalList(param);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return h;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        try {
            delegate.processFinish(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
