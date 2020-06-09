package me.cangming.cmmatrix.metric;

import java.util.concurrent.atomic.AtomicBoolean;


/**
 * 每隔一个时间段做一次sample操作
 */
public abstract class AbstractSampler {

    private static final int DEFAULT_SAMPLE_INTERVAL = 300;

    protected AtomicBoolean mShouldSample = new AtomicBoolean(false);

    //每隔interval时间dump一次信息
    protected long mSampleInterval;

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            doSample();

            if (mShouldSample.get()) {
                HandlerThreadFactory.getDoDumpThreadHandler()
                        .postDelayed(mRunnable, mSampleInterval);
            }
        }
    };
    private long sampleDelay;

    public AbstractSampler(long sampleInterval) {
        if (0 == sampleInterval) {
            sampleInterval = DEFAULT_SAMPLE_INTERVAL;
        }
        mSampleInterval = sampleInterval;
    }

    public void start() {
        if (mShouldSample.get()) {
            return;
        }
        mShouldSample.set(true);

        HandlerThreadFactory.getDoDumpThreadHandler().removeCallbacks(mRunnable);
        HandlerThreadFactory.getDoDumpThreadHandler().postDelayed(mRunnable,
                getSampleDelay());
    }

    private long getSampleDelay() {
        return sampleDelay;
    }

    public void setSampleDelay(long sampleDelay) {
        this.sampleDelay = sampleDelay;
    }

    public void stop() {
        if (!mShouldSample.get()) {
            return;
        }
        mShouldSample.set(false);
        HandlerThreadFactory.getDoDumpThreadHandler().removeCallbacks(mRunnable);
    }

    public abstract void doSample();

    public void setSampleInterval(long sampleInterval) {
        mSampleInterval = sampleInterval;
    }
}
