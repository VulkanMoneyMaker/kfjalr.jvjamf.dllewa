package kfjalr.jvjamf.dllewa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;



import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = SplashActivity.class.getSimpleName();

    private static final int TIME_SPLASH_SEC = 3;

    private Disposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_splash);

        mDisposable = Observable.timer(TIME_SPLASH_SEC, TimeUnit.SECONDS)
                .subscribe(__ -> openChoiceActivity(), Throwable::printStackTrace);
    }

    @Override
    public void onDestroy() {
        if (mDisposable != null) mDisposable.dispose();
        super.onDestroy();
    }

    private void openChoiceActivity() {
        Log.d(TAG, "openChoiceActivity");
        startActivity(ChoiceActivity.getChoiceActivityIntent(this));
        finish();
    }
}
