package me.cangming.cmmatrix.analysis.memory.gc;


import me.cangming.cmmatrix.metric.MetricInfo;

/**
 * 创建时间: 2019/12/4
 * 类描述:
 * <p>
 * 收集logcat中的GC日志
 *
 * @author cangming
 * @version 1.0
 */
public class GcInfo extends MetricInfo {
    //logcat捕捉GC日志
    //Dalvik/Art
    //adb shell logcat com.zmsoft.kds | grep -e GC_ -e AllocSpace

    boolean isArt;
    String gcString;

    public GcInfo(boolean isArt, String gcString) {
        this.isArt = isArt;
        this.gcString = gcString;
    }

}
