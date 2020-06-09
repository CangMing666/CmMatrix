package me.cangming.demo;

import android.app.Application;

import me.cangming.cmmatrix.MMonitor;
import me.cangming.cmmatrix.MonitorData;

public class APP extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initMonitor();
    }

    private void initMonitor() {
        MMonitor.Builder.newBuilder().context(this)
                .appInfo("me.cangming.apm", "1.0.0")
                .appKey("200020", "guce5uq2mbp0t7rn1eg7yrnd7gt0yg4e")
                .uploadService(MMonitor.TEST_URL, MMonitor.TEST_PROT, "/sdcard/kds/upload", (short) 200)
                .isDebugEnalbe(BuildConfig.DEBUG)
                .MLog(BuildConfig.DEBUG, "cangming", null)
                .install(new int[]{
                        MonitorData.TYPE_METRIC_ANR,
                        MonitorData.TYPE_METRIC_BLOCK,
                        MonitorData.TYPE_METRIC_GC,
                        MonitorData.TYPE_METRIC_HTTP,
                        MonitorData.TYPE_METRIC_THREAD,
                })
                .build();

        //开始监控
        MMonitor.getInstance().start();

        MMonitor.getInstance().setUserId("000001", "abcdefghigk");
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        MMonitor.getInstance().onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        MMonitor.getInstance().onTrimMemory(level);
    }
}
