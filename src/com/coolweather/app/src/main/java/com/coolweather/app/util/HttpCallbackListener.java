package com.coolweather.app.util;

/**
 * Created by zhufan on 2016/2/23.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
