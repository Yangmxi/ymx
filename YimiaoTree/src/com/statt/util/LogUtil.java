
package com.statt.util;

public class LogUtil {

    public static void Log(String tag, String msg) {
        if (DefineUtil.DEBUG) {
            android.util.Log.e(tag, msg);
        }
    }

}
