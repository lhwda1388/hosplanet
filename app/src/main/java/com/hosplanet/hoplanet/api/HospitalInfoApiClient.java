package com.hosplanet.hoplanet.api;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by hyunwoo-Lee on 2015-12-16.
 */
public class HospitalInfoApiClient {

    public JSONObject getHostPitalList(HospitalInfoApiBean hospitalInfoApiBean) throws Exception {
        List hostPitalList = null;
        HospitalInfoApiBean hBean = new HospitalInfoApiBean();
        String serviceKey= "?"+URLEncoder.encode("ServiceKey","UTF-8")+"="+HospitalInfoApiBean.serviceKey;
        hBean.setAddr("json");
        String urlString = hBean.getApiUrl()+serviceKey+"&_type=json";
        StringBuilder urlBuilder = new StringBuilder(urlString);

        Log.i("URL", urlBuilder.toString());
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
        JSONObject jObject = new JSONObject(getStringFromInputStream(in));

        return jObject;
    }
    private String getStringFromInputStream(InputStream in){
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();
        String line;
        try{
            br = new BufferedReader(new InputStreamReader(in));
            while((line = br.readLine()) != null){
                sb.append(line);
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if(br != null){
                try{
                    br.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}


