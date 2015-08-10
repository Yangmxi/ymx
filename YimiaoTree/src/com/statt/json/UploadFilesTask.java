
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

public class UploadFilesTask extends AsyncTask<URL, Integer, String> {

    protected String doInBackground(URL... urls) {
        int count = urls.length;
        long totalSize = 0;
        for (int i = 0; i < count; i++) {

        }
        return "s";
    }

    protected void onProgressUpdate(Integer... progress) {
    }

    protected void onPostExecute(String result) {

    }
}
