package com.hosplanet.hoplanet.api;

import android.os.AsyncTask;

import org.json.JSONException;

import java.util.List;

/**
 * Created by hyunwoo-Lee on 2015-12-16.
 */
public class HospitalInfoAsyncTask extends AsyncTask<HospitalInfoApiBean,Void,List<HospitalInfoApiBean>>{
    public AsyncResponse delegate = null;
    public HospitalInfoAsyncTask(AsyncResponse asyncResponse){
        delegate = asyncResponse;
    }

    @Override
    protected List<HospitalInfoApiBean> doInBackground(HospitalInfoApiBean... params) {
        HospitalInfoApiClient client = new HospitalInfoApiClient();
        HospitalInfoApiBean param = params[0];
        List<HospitalInfoApiBean> h = null;
        try{
            h = client.getHostPitalList(param);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return h;
    }

    @Override
    protected void onPostExecute(List<HospitalInfoApiBean> hospitalInfoApiBeans) {
        delegate.processFinish(hospitalInfoApiBeans);
    }
}
