package project.test.ru.coursevalute;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;


public class App extends Application {

    private static SharedPreferences preferences;

    public static SharedPreferences getSharedPreference() {
        return preferences;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        preferences = getApplicationContext().getSharedPreferences("cache",Context.MODE_PRIVATE);
    }
}
