package project.test.ru.coursevalute.interactor;


import android.os.Handler;
import android.os.Looper;

public class MainThread {

    private static MainThread sMainThread;

    private Handler mHandler;

    private MainThread() {
        mHandler = new Handler(Looper.getMainLooper());
    }


    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }

    public static MainThread getInstance() {
        if (sMainThread == null) {
            sMainThread = new MainThread();
        }

        return sMainThread;
    }
}
