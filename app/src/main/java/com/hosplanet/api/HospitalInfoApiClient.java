package com.hosplanet.api;

import android.text.TextUtils;
import android.util.Log;

import com.hosplanet.common.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by hyunwoo-Lee on 2015-12-16.
 */
public class HospitalInfoApiClient {

    public JSONObject getHostPitalList(HospitalInfoApiBean hospitalInfoApiBean) throws Exception {
        List hostPitalList = null;
        HospitalInfoApiBean hBean = new HospitalInfoApiBean();


        String urlString = HospitalInfoApiBean.apiUrl;
        StringBuilder urlBuilder = new StringBuilder(urlString);

        appendString(urlBuilder, "ServiceKey", HospitalInfoApiBean.serviceKey ,"UTF-8" ,"?");
        appendString(urlBuilder, "_type", "json" ,"UTF-8" ,"&");
        appendString(urlBuilder, "pageNo", hospitalInfoApiBean.getPageNo() ,"UTF-8" ,"&");
        appendString(urlBuilder, "numOfRows", hospitalInfoApiBean.getNumOfRows() ,"UTF-8" ,"&");
        appendString(urlBuilder, "sidoCd", hospitalInfoApiBean.getSidoCd() ,"UTF-8" ,"&");
        appendString(urlBuilder, "sidoCdNm", hospitalInfoApiBean.getSidoCdNm() ,"UTF-8" ,"&");
        appendString(urlBuilder, "sgguCd", hospitalInfoApiBean.getSgguCd() ,"UTF-8" ,"&");
        appendString(urlBuilder, "sgguCdNm", hospitalInfoApiBean.getSgguCdNm() ,"UTF-8" ,"&");
        appendString(urlBuilder, "emdongNm", hospitalInfoApiBean.getEmdongNm() ,"UTF-8" ,"&");
        appendString(urlBuilder, "yadmNm", hospitalInfoApiBean.getYadmnm() ,"UTF-8" ,"&");
        appendString(urlBuilder, "zipCd", hospitalInfoApiBean.getZipCd() ,"UTF-8" ,"&");
        appendString(urlBuilder, "clCd", hospitalInfoApiBean.getClCd() ,"UTF-8" ,"&");
        appendString(urlBuilder, "dgsbjtCd", hospitalInfoApiBean.getDgsbjtCd() ,"UTF-8" ,"&");
        appendString(urlBuilder, "xPos", hospitalInfoApiBean.getxPos() ,"UTF-8" ,"&");
        appendString(urlBuilder, "yPos", hospitalInfoApiBean.getyPos() ,"UTF-8" ,"&");
        appendString(urlBuilder, "radius", hospitalInfoApiBean.getRadius() ,"UTF-8" ,"&");

        Log.i("URL", urlBuilder.toString());
        return HttpUtil.getHttpUrlData(urlBuilder.toString());
    }
    private void appendString(StringBuilder builder, String key, Object value, String charset, String prepend) throws UnsupportedEncodingException {
        String enCodeValue = null;
        if(value != null) {
            if (value instanceof Integer) {
                enCodeValue = Integer.toString(((Integer) value).intValue());
            } else if (value instanceof Double) {
                enCodeValue = Double.toString(((Double) value).doubleValue());
            } else {
                enCodeValue = (String) value;
            }
        }
        if(!TextUtils.isEmpty(enCodeValue)){
            builder.append(prepend + URLEncoder.encode(key, charset) + "=" + URLEncoder.encode(enCodeValue, charset));
        }
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


