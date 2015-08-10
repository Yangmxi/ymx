
package com.statt.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapUtil {

    private static final String TAG = "BitmapUtil";

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
