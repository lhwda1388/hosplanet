package com.hosplanet.common.util;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;

import com.hosplanet.R;

/**
 * Created by hyunwoo-Lee on 2015. 12. 20..
 */
public class CommonUtil {
    public static Context contextObj = null;
    public static String mapApiKey = getString(R.string.mapApiKey);


    public static String sNullChk(String str, String defaultVal){
        String retVal = "";
        if(TextUtils.isEmpty(str)){
            retVal = defaultVal;
        }else{
            retVal = str;
        }
        return retVal;
    }

    public static Integer iNullChk(int value, int defaultVal){
        int retVal = 0;

        if(TextUtils.isEmpty(String.valueOf(value))){
            retVal = defaultVal;
        }else{
            retVal = value;
        }
        return retVal;
    }
    public static Double dNullChk(double value, double defaultVal){
        double retVal = 0;
        if(TextUtils.isEmpty(String.valueOf(value))){
            retVal = defaultVal;
        }else{
            retVal = value;
        }
        return retVal;
    }
    public static String capString(String str){
        String res = str.substring(0,1).toUpperCase() + str.substring(1).toLowerCase();
        return res;
    }

    public static String getString(int id){

        return contextObj.getString(id);
    }

}
