package me.cangming.demo.utils;

import android.util.Log;

/**
 * Dummy logger for replace logcat
 */
public class LogUtils {

    public static int log(String tag, String msg) {
        Log.e(tag, msg + " hook");
        return 0;
    }
}
