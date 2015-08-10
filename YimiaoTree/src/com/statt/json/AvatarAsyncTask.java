
package com.statt.json;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.json.JSONObject;

import android.R.integer;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.statt.util.FormFile;
import com.statt.util.JsonStrUtil;
import com.statt.util.LogUtil;
import com.statt.util.SocketHttpRequester;
import com.statt.util.URLClientUtil;
import com.statt.widget.RoundImageView;

/**
 * Download or upload avatar from service port
 * @author ymx
 *
 */
public class AvatarAsyncTask extends AsyncTask<Void, integer, String> {

    private static final String TAG = "AvatarAysncTask";
    private RoundImageView mAvatar;
    private File mFile;
    // download or upload Image from service port
    private boolean mIsDownload;
    private JSONObject mParamsDownload;
    private Map<String, String> mParamsUpload;
    private String mAddress;
    private Context mContext;
    private String mKey;

    /**
     * Upload Constructor
     * @param context
     * @param file
     * @param params
     * @param avatar
     */

    public AvatarAsyncTask(Context context, File file, Map<String, String> params,
            RoundImageView avatar, String address, String key) {
        super();
        this.mKey = key;
        this.mFile = file;
        this.mParamsUpload = params;
        this.mIsDownload = false;
        init(context, avatar, address);
    }

    /**
     * Download constructor
     * @param context
     * @param params
     * @param avatar
     * @param address
     */
    public AvatarAsyncTask(Context context, JSONObject params, RoundImageView avatar, String address) {
        super();
        this.mParamsDownload = params;
        this.mIsDownload = true;
        init(context, avatar, address);
    }

    private void init(Context context, RoundImageView avatar, String address) {
        this.mContext = context;
        this.mAvatar = avatar;
        this.mAddress = address;
    }

    @Override
    protected String doInBackground(Void... params) {
        if (mIsDownload) {
            String temp = URLClientUtil.AccessWebUtil(JsonStrUtil.jsonToStr(mParamsDownload.toString()), mAddress);
            Map<String, Object> o = JSON.parseObject(temp.toString(), Map.class);
            LogUtil.Log(TAG, "返回json :" + o);
            if (!temp.equals("")) {
                URL src = JSON.parseObject(o.get("obj").toString(), URL.class);
                return src.toString();
                //TODO get error code and show Toast
            } else {
                //TODO show Toast : can not connect internet
                return null;
            }
        } else {
            FormFile formfile = new FormFile(mFile.getName(), mFile, "UserHeadPortrait", "application/octet-stream");
            try {
                boolean res = SocketHttpRequester.post(mAddress, mParamsUpload, formfile);
                LogUtil.Log(TAG, "boolean = " + res);
                if (res) {
                    return "设置成功";
                } else {
                    return "设置失败";
                }
            } catch (Exception ex) {
                LogUtil.Log(TAG, "exception: " + ex.toString());
            }

        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        if (mIsDownload) {
            Bitmap bitmap = getBitmapFromURL(result);
            mAvatar.setImageBitmap(bitmap);
        } else {
            Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
        }
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            LogUtil.Log(TAG, "URL is" + src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            LogUtil.Log(TAG, "Bitmap from URL returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.Log(TAG, e.getMessage());
            return null;
        }
    }

}
