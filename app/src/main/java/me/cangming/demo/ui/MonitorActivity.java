package me.cangming.demo.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import me.cangming.cmmatrix.MMonitor;
import me.cangming.demo.R;
import me.cangming.demo.utils.HttpResult;
import me.cangming.demo.utils.LoginApi;
import me.cangming.demo.utils.RetrofitService;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * @date 创建时间：2019-12-28
 * @auther cangming
 * @Description 监控性能页面
 */
public class MonitorActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnAnrMonitor;
    private Button mBtnCrashMonitor;
    private Button mBtnHttpMonitor;
    private Button mBtnGcMonitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);
        initView();
    }


    private void initView() {
        mBtnAnrMonitor = (Button) findViewById(R.id.btn_anr_monitor);
        mBtnAnrMonitor.setOnClickListener(this);
        mBtnCrashMonitor = (Button) findViewById(R.id.btn_crash_monitor);
        mBtnCrashMonitor.setOnClickListener(this);
        mBtnHttpMonitor = (Button) findViewById(R.id.btn_http_monitor);
        mBtnHttpMonitor.setOnClickListener(this);
        mBtnGcMonitor = (Button) findViewById(R.id.btn_gc_monitor);
        mBtnGcMonitor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_anr_monitor:
                anrMonitor();
                break;
            case R.id.btn_crash_monitor:
                crashMonitor();
                break;
            case R.id.btn_http_monitor:
                httpMonitor();
                break;
            case R.id.btn_gc_monitor:
                gcMonitor();
                break;
        }
    }

    private void crashMonitor() {
        throw new RuntimeException("monitor crash");
    }

    private void httpMonitor() {
        JSONObject object = new JSONObject();
        try {
            object.put("data", "1");
            object.put("data1", "2");
            object.put("data2", "3");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MMonitor.getInstance().trackBusUserEvent("clickHttp", object);

        final String pd = MMonitor.getInstance().getParentId();


        RetrofitService.getProxy(LoginApi.class)
                .doLogin("", "")
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<HttpResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        JSONObject object = new JSONObject();
                        try {
                            object.put("data", "1");
                            object.put("data1", "2");
                            object.put("data2", "3");
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                        MMonitor.getInstance().trackBusUserEvent("afterclickHttp", object, pd);

                    }

                    @Override
                    public void onNext(HttpResult httpResult) {
                        JSONObject object = new JSONObject();
                        try {
                            object.put("data", "1");
                            object.put("data1", "2");
                            object.put("data2", "3");
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                        MMonitor.getInstance().trackBusUserEvent("afterclickHttp", object, pd);

                    }
                });
    }

    private void gcMonitor() {
        JSONObject object = new JSONObject();

        try {
            object.put("data", "1");
            object.put("data1", "2");
            object.put("data2", "3");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MMonitor.getInstance().trackBusUserEvent("clickGc", object);

        MMonitor.getInstance().setParentId(null);
        for (int i = 0; i < 50; i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gc_create_bitmap);
            Bitmap copy = bitmap.copy(bitmap.getConfig(), true);
        }
        System.gc();
    }

    private void anrMonitor() {
        JSONObject object = new JSONObject();
        try {
            object.put("data", "1");
            object.put("data1", "2");
            object.put("data2", "3");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MMonitor.getInstance().trackBusUserEvent("clickAnr", object);

        MMonitor.getInstance().setParentId(null);

        synchronized (MonitorActivity.this) {
            try {
                MonitorActivity.this.wait(20000);
            } catch (InterruptedException e) {
            }
        }
    }


}
