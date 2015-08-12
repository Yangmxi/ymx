
package com.statt.json;

import java.util.Map;

import org.json.JSONObject;

import android.os.AsyncTask;

import com.alibaba.fastjson.JSON;
import com.statt.util.JsonStrUtil;
import com.statt.util.LogUtil;
import com.statt.util.URLClientUtil;

public class RequestTask extends AsyncTask<Void, Integer, Map<String, Object>> {

    private static final String TAG = "RequestTask";
    private Map<String, Object> mRes;
    private String mAddress;
    private JSONObject mParam;

    public RequestTask(JSONObject mParam, String address) {
        super();
        this.mParam = mParam;
        this.mAddress = address;
    }

    @Override
    protected Map<String, Object> doInBackground(Void... params) {
        String param;
        if (mParam == null) {
            param = "";
        } else {
            param = JsonStrUtil.jsonToStr(mParam.toString());
        }
        String temp = URLClientUtil.AccessWebUtil(param, mAddress);
        Map<String, Object> map = JSON.parseObject(temp.toString(), Map.class);
        LogUtil.Log(TAG, "Return json :" + map);
        return map;
    }

    @Override
    protected void onPostExecute(Map<String, Object> result) {
        mRes = result;
    }

    /**
     * Return the json request result.
     *      object = JSON.parseArray(o.get("obj").toString(), GET_OBJECT_CLASS.class);
     * @return
     */
    public Map<String, Object> getResult() {
        return mRes;
    }
}
