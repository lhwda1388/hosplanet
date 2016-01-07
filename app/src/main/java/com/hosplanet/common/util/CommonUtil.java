package com.hosplanet.common.util;

import android.text.TextUtils;

/**
 * Created by hyunwoo-Lee on 2015. 12. 20..
 */
public class CommonUtil {
    public static String mapApiKey = "249484233034131062";

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
}
