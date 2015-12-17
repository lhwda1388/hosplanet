package com.hosplanet.hoplanet.api;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyunwoo-Lee on 2015-12-16.
 */
public class HospitalInfoApiClient {

    public JSONObject getHostPitalList(HospitalInfoApiBean hospitalInfoApiBean) throws Exception {
        List hostPitalList = null;
        HospitalInfoApiBean hBean = new HospitalInfoApiBean();
        String serviceKey= "?"+URLEncoder.encode("ServiceKey","UTF-8")+"="+URLEncoder.encode(HospitalInfoApiBean.serviceKey,"UTF-8");
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
    public static HospitalInfoApiBean getJObjectFromHBean(JSONObject item) throws JSONException {

        HospitalInfoApiBean hBean = new HospitalInfoApiBean();

        Log.i("item", item.toString());

        hBean.setAddr(item.getString("addr"));
        hBean.setClCd(item.getString("clCd"));
        hBean.setClCdNm(item.getString("clCdNm"));
        hBean.setDistance(item.getInt("distance"));
        hBean.setDrTotCnt(item.getInt("drTotCnt"));
        hBean.setEmdongNm(item.getString("emdongNm"));
        hBean.setEstbDd(item.getString("estbDd"));
        hBean.setGdrCnt(item.getInt("gdrCnt"));
        hBean.setHospUrl(item.getString("hospUrl"));
        hBean.setIntnCnt(item.getInt("intnCnt"));
        hBean.setPostNo(item.getString("postNo"));
        hBean.setResdntCnt(item.getInt("resdntCnt"));
        hBean.setSdrCnt(item.getInt("sdrCnt"));
        hBean.setSgguCd(item.getString("sgguCd"));
        hBean.setSgguCdNm(item.getString("sgguCdNm"));
        hBean.setSidoCd(item.getString("sidoCd"));
        hBean.setSidoCdNm(item.getString("sidoCdNm"));
        hBean.setTelno(item.getString("telno"));
        hBean.setxPos(item.getDouble("XPos"));
        hBean.setyPos(item.getDouble("YPos"));
        hBean.setYadmnm(item.getString("yadmNm"));
        hBean.setYkiho(item.getString("ykiho").trim());

        return hBean;
    }
}


