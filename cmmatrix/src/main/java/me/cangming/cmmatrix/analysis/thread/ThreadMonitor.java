package me.cangming.cmmatrix.analysis.thread;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import me.cangming.cmmatrix.MonitorData;
import me.cangming.cmmatrix.log.MLog;
import me.cangming.cmmatrix.metric.MetricListener;
import me.cangming.cmmatrix.metric.MetricMonitor;
import me.cangming.cmthreadhook.ThreadDetailInfo;
import me.cangming.cmthreadhook.ThreadHook;


/**
 * 创建时间: 2019/12/11
 * 类描述:
 *
 * @author cangming
 * @version 1.0
 */
public class ThreadMonitor extends MetricMonitor {

    public ThreadMonitor(MetricListener metricListener) {
        super(metricListener);
    }

    @Override
    public void start(Context context) {
        super.start(context);
        if (isInstalled(MonitorData.TYPE_METRIC_THREAD)) {
            ThreadHook.enableThreadHook();
            ThreadHook.setOnEventListenter(new ThreadHook.OnEventListenter() {
                @Override
                public void onThreadCreate(ThreadDetailInfo threadDetailInfo) {
                    printThreadDetail(threadDetailInfo);
                }
            });
        }
    }

    private void printThreadDetail(ThreadDetailInfo threadDetailInfo) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("threadCount:", ThreadHook.getThreadTotalCount());
            jsonObject.put("threadCreateTime:",threadDetailInfo.getCreateTime());
            jsonObject.put("threadDetail:",threadDetailInfo.getStackInfo());
            metricData(MonitorData.TYPE_METRIC_THREAD, jsonObject.toString());
        } catch (JSONException e) {
            if (e != null) {
                MLog.d("ThreadMonitor", "error", e);
            } else {
                MLog.d("ThreadMonitor", "error ");
            }
        }
    }

    @Override
    public void stop(Context context) {
        super.stop(context);

    }
}
