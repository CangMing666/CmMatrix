package me.cangming.cmmatrix.metric;

import android.content.Context;

import me.cangming.cmmatrix.Monitor;


/**
 * 创建时间: 2019/12/11
 * 类描述:
 *
 * @author cangming
 * @version 1.0
 */
public class MetricMonitor {
    protected MetricListener metricListener;

    protected volatile int sampleInterval;


    public MetricMonitor(MetricListener metricListener) {
        this.metricListener = metricListener;
    }

    public static boolean isInstalled(int type) {
        return Monitor.isInstalled(type);
    }

    protected void metricData(int type, Object data) {
        if (metricListener != null) {
            metricListener.metric(type, data);
        }
    }

    public void start(Context context) {

    }

    public void stop(Context context) {

    }

    public void setForground(boolean isForground) {

    }


    public void notifyWork() {

    }
}
