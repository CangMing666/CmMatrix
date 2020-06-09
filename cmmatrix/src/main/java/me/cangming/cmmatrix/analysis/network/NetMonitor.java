package me.cangming.cmmatrix.analysis.network;

import android.content.Context;

import me.cangming.cmmatrix.Monitor;
import me.cangming.cmmatrix.MonitorData;
import me.cangming.cmmatrix.metric.MetricListener;
import me.cangming.cmmatrix.metric.MetricMonitor;
import me.cangming.cmmatrix.analysis.network.http.okhttp.NetWorkInterceptor;
import okhttp3.OkHttpClient;

/**
 * 创建时间: 2019/12/11
 * 类描述:
 *
 * @author cangming
 * @version 1.0
 */
public class NetMonitor extends MetricMonitor {

    public NetMonitor(MetricListener metricListener) {
        super(metricListener);
    }

    public static void beforeOkHttpBuild(OkHttpClient.Builder builder) {
        if (builder != null && isInstalled(MonitorData.TYPE_METRIC_HTTP)) {
            builder.addInterceptor(new NetWorkInterceptor(new MetricListener() {
                @Override
                public void metric(int type, Object data) {
                    Monitor.metricData(MonitorData.TYPE_METRIC_HTTP, data);
                }
            }));
        }
    }

    public void start(Context context) {

    }

    public void stop(Context context) {

    }
}
