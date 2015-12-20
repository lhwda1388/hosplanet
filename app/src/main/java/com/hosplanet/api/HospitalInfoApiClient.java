package com.hosplanet.api;

import android.text.TextUtils;
import android.util.Log;

import com.hosplanet.common.util.CommonUtil;
import com.hosplanet.common.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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

        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + HospitalInfoApiBean.serviceKey);
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
    public static HospitalInfoApiBean getJObjectFromHBean(JSONObject item) throws JSONException, ClassNotFoundException {

        HospitalInfoApiBean hBean = new HospitalInfoApiBean();
        Class hBeanCls = hBean.getClass();
        int j=1;
        Iterator<String> keys = item.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            //Log.i("VALUE::" , "key = " + key + ",value = " + item.get(key));
            try{
                Field[] fields = hBeanCls.getDeclaredFields();
                for (Field field : fields){
                    field.setAccessible(true);
                    if(key.equals(field.getName())) {
                        Method[] methods = hBeanCls.getMethods();
                        for(Method method : methods){
                            if(method.getName().substring(0, 3).equals("set")){
                                if(method.getName().toUpperCase().equals(("set"+field.getName()).toUpperCase())){
                                    method.setAccessible(true);

                                    try {
                                        method.invoke(hBean, new Object[]{item.get(key)});
                                    }catch(java.lang.IllegalArgumentException e){
                                        method.invoke(hBean, new Object[]{item.getString(key)});
                                    }

                                    method.setAccessible(false);
                                    break;
                                }
                            }
                        }
                        break;
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return hBean;
    }
}


