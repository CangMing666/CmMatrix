package me.cangming.cmmatrix;

/**
 * 创建时间: 2019/12/11
 * 类描述:
 *
 * @author cangming
 * @version 1.0
 */
public class MonitorData {

    //metric
    public static final int TYPE_METRIC_MEM = 0;
    public static final int TYPE_METRIC_CPU = 1;
    public static final int TYPE_METRIC_FPS = 2;
    public static final int TYPE_METRIC_ANR = 3;
    public static final int TYPE_METRIC_BLOCK = 4;
    public static final int TYPE_METRIC_GC = 5;
    public static final int TYPE_METRIC_HTTP = 6;
    public static final int TYPE_METRIC_BATTERY = 7;
    public static final int TYPE_METRIC_CRASH = 8;
    public static final int TYPE_METRIC_THREAD = 9;

    public static String getTpye(int mType) {
        switch (mType) {
            case TYPE_METRIC_MEM:
                return "TYPE_METRIC_MEM";
            case TYPE_METRIC_CPU:
                return "TYPE_METRIC_CPU";
            case TYPE_METRIC_FPS:
                return "TYPE_METRIC_FPS";
            case TYPE_METRIC_ANR:
                return "TYPE_METRIC_ANR";
            case TYPE_METRIC_BLOCK:
                return "TYPE_METRIC_BLOCK";
            case TYPE_METRIC_GC:
                return "TYPE_METRIC_GC";
            case TYPE_METRIC_HTTP:
                return "TYPE_METRIC_HTTP";
            case TYPE_METRIC_BATTERY:
                return "TYPE_METRIC_BATTERY";
            case TYPE_METRIC_CRASH:
                return "TYPE_METRIC_CRASH";
            case TYPE_METRIC_THREAD:
                return "TYPE_METRIC_THREAD";
        }
        return "";
    }
}
