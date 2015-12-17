package com.hosplanet.hoplanet.api;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by hyunwoo-Lee on 2015-12-16.
 */
public interface AsyncResponse {
    void processFinish(JSONObject jsonObject)throws Exception;
}
