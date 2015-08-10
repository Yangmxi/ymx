
package com.statt.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.alibaba.fastjson.JSON;

public class JsonStrUtil {

    private static final String TAG = "JsonStrUtil";

    // post请求body
    public static String jsonToStr(Object obj) {
        String txt = JSON.toJSON(obj).toString();
        LogUtil.Log(TAG, "请求传参转码前：" + txt);
        try {
            txt = URLEncoder.encode(txt, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        LogUtil.Log(TAG, "请求传参转码后：" + txt);
        String str = BaseCode64.encode(txt.getBytes());

        LogUtil.Log(TAG, "请求传参jiami 转码后：" + str);

        return str;
    }

    // post请求body
    public static String jsonToStrLogin(Object obj) {
        String txt = JSON.toJSON(obj).toString();
        LogUtil.Log(TAG, "请求传参：" + txt);
        return BaseCode64.encode(txt.getBytes());
    }

}
