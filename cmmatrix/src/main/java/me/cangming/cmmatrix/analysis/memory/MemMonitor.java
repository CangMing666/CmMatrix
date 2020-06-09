package me.cangming.cmmatrix.analysis.memory;

import android.content.Context;

import me.cangming.cmmatrix.MonitorData;
import me.cangming.cmmatrix.analysis.memory.data.Memory;
import me.cangming.cmmatrix.analysis.memory.gc.GcInfo;
import me.cangming.cmmatrix.constants.Constants;
import me.cangming.cmmatrix.log.MLog;
import me.cangming.cmmatrix.metric.MetricListener;
import me.cangming.cmmatrix.metric.MetricMonitor;
import me.cangming.cmmatrix.utils.ContentLisenter;
import me.cangming.cmmatrix.utils.DeviceUtils;
import me.cangming.cmmatrix.utils.ShellUtils;


/**
 * 创建时间: 2019/12/11
 * 类描述:
 *
 * @author cangming
 * @version 1.0
 */
public class MemMonitor extends MetricMonitor {

    private Thread workDamondThread;

    private Thread workGcDamondThread;

    public MemMonitor(MetricListener metricListener) {
        super(metricListener);
    }

    @Override
    public void start(final Context context) {
        if (isInstalled(MonitorData.TYPE_METRIC_MEM)) {
            workDamondThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (workDamondThread) {
                        while (!Thread.interrupted()) {
                            synchronized (workDamondThread) {
                                try {
                                    workDamondThread.wait(1000);
                                } catch (InterruptedException e) {
                                }
                            }

                            //收集mem数据
                            metricData(MonitorData.TYPE_METRIC_MEM, Memory.getAppMemInfo(context));
                        }
                    }
                }
            });
            workDamondThread.start();
        }
        if (isInstalled(MonitorData.TYPE_METRIC_GC)) {
            workGcDamondThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (workGcDamondThread) {
                        while (!Thread.interrupted()) {
                            synchronized (workGcDamondThread) {
                                try {
                                    workGcDamondThread.wait(1000);
                                } catch (InterruptedException e) {
                                }
                            }
                            //收集Gc数据
                            ShellUtils.execCommand(new String[]{"logcat", "-v", "time"}, false, new ContentLisenter() {
                                @Override
                                public void content(String content) {
                                    if (content.contains("GC")
                                            && content.contains("paused")
                                            && content.contains("freed")
                                            && content.contains(String.valueOf(android.os.Process.myPid()))
                                            && !content.contains(MLog.customTagPrefix)
                                            && !content.contains("gcString")
                                            && !content.contains("isArt")) {
                                        metricData(MonitorData.TYPE_METRIC_GC, new GcInfo(DeviceUtils.getIsArtInUse(), content.replace("\\", "")));
                                    }
                                }

                                @Override
                                public void error(Exception e, String s) {
                                    if (e != null) {
                                        MLog.d("GC", "error", e);
                                    } else {
                                        MLog.d("GC", "error " + s);
                                    }
                                }
                            });


                        }
                    }
                }
            });
            workGcDamondThread.start();

            MLog.d(Constants.TAG,"TYPE_METRIC_GC workGcDamondThread is Start");
        }

    }

    @Override
    public void notifyWork() {
        if (workDamondThread != null) {
            synchronized (workDamondThread) {
                workDamondThread.notify();
            }
        }

        if (workGcDamondThread != null) {
            synchronized (workGcDamondThread) {
                workGcDamondThread.notify();
            }
        }
    }

    @Override
    public void stop(Context context) {
        if (workDamondThread != null) {
            synchronized (workDamondThread) {
                workDamondThread.notify();
            }
            workDamondThread.interrupt();
            workDamondThread = null;
        }

        if (workGcDamondThread != null) {
            synchronized (workGcDamondThread) {
                workGcDamondThread.notify();
            }
            workGcDamondThread.interrupt();
            workGcDamondThread = null;
        }

    }
}
