package com.hosplanet.api;

import android.util.Log;

import com.hosplanet.common.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.List;

/**
 * Created by hyunwoo-Lee on 2015-12-16.
 */
public class HospitalInfoApiClient {

    public JSONObject getHostPitalList(HospitalInfoApiBean hospitalInfoApiBean) throws Exception {
        List hostPitalList = null;
        HospitalInfoApiBean hBean = new HospitalInfoApiBean();

        String serviceKey= "?"+URLEncoder.encode("ServiceKey","UTF-8")+"="+URLEncoder.encode(HospitalInfoApiBean.serviceKey,"UTF-8");
        String urlString = HospitalInfoApiBean.apiUrl+serviceKey+"&_type=json";
        StringBuilder urlBuilder = new StringBuilder(urlString);
        Log.i("URL", urlBuilder.toString());
        return HttpUtil.getHttpUrlData(urlBuilder.toString());
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


