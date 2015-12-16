package com.hosplanet.hoplanet.api;

import java.util.List;

/**
 * Created by hyunwoo-Lee on 2015-12-16.
 */
public interface AsyncResponse {
    void processFinish(List<HospitalInfoApiBean> hospitalInfoApiBean);
}
