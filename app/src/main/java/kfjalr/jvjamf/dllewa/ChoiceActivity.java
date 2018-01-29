package kfjalr.jvjamf.dllewa;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import kfjalr.jvjamf.dllewa.utils.InternetUtils;

public class ChoiceActivity extends AppCompatActivity {
    private static final String TAG = ChoiceActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        setContentView(R.layout.activity_choice);

        findViewById(R.id.btn_choice_play).setOnClickListener(__ -> {
            openNextScreenGame();
        });

        findViewById(R.id.btn_choice_feedback).setOnClickListener(__ -> {
            openFeedBackActivity();
        });
    }

    private void openNextScreenGame() {
        if (InternetUtils.checkNetworkConnection(this)) {
            openScreenMainActivity();
        } else {
            openScreenGameActivity();
        }
    }

    private void openScreenMainActivity() {
        Log.d(TAG, "openScreenMainActivity");
        startActivity(MainActivity.getMainActivityIntent(this));
        finish();
    }

    private void openScreenGameActivity() {
        Log.d(TAG, "openScreenMainActivity");
        startActivity(StartGameActivity.getGameActivityIntent(this));
        finish();
    }

    private void openFeedBackActivity() {
        Log.d(TAG, "openFeedBackActivity");
        startActivity(FeedbackActivity.getFeedbackActivityIntent(this));
    }

    @NonNull
    public static Intent getChoiceActivityIntent(Context context) {
        return new Intent(context, ChoiceActivity.class);
    }
}
