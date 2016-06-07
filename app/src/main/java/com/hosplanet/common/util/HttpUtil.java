package com.hosplanet.common.util;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by hyunwoo-Lee on 2015-12-18.
 */
public class HttpUtil {

    public static JSONObject getHttpUrlData(String argUrl){
        URL url = null;
        HttpURLConnection urlConnection = null;
        InputStream in = null;
        JSONObject jObject = null;
        try {
            url = new URL(argUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream());
            jObject = new JSONObject(getStringFromInputStream(in));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally{
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            urlConnection.disconnect();

        }


        return jObject;
    }
    public static String getStringFromInputStream(InputStream in){
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


    public static void appendString(StringBuilder builder, String key, Object value, String charset, String prepend) throws UnsupportedEncodingException {
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
}
