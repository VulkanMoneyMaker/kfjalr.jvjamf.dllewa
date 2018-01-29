package kfjalr.jvjamf.dllewa;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;


import kfjalr.jvjamf.dllewa.wheel.src.kankan.wheel.widget.OnWheelScrollListener;
import kfjalr.jvjamf.dllewa.wheel.src.kankan.wheel.widget.WheelView;

import java.util.ArrayList;
import java.util.Random;

public class StartGameActivity extends AppCompatActivity implements OnWheelScrollListener {
    private static final String TAG = StartGameActivity.class.getSimpleName();

    private ArrayList<Integer> mListImages;
    private ArrayList<WheelView> mListWheel;
    private ArrayList<Boolean> mStateWheels; // false - not finished, true - finished

    private AlertDialog mDialogWin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        setContentView(R.layout.activity_game);

        mListWheel = new ArrayList<>();
        mStateWheels = new ArrayList<>();
        TypedArray wheels = getResources().obtainTypedArray(R.array.wheel_items);
        for (int i = 0; i < wheels.length(); ++i) {
            WheelView view = findViewById(wheels.getResourceId(i, 0));
            mListWheel.add(view);
            mStateWheels.add(false);
        }
        wheels.recycle();
        initSlots();

        Button playButton = findViewById(R.id.btn_play);
        playButton.setOnClickListener(__ -> {
            initStateWheels();
            generateRandomScrollWheels();
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        for (WheelView view : mListWheel) {
            view.addScrollingListener(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        for (WheelView view : mListWheel) {
            view.removeScrollingListener(this);
        }
    }

    private void initSlots() {
        for (WheelView view : mListWheel) {
            iniWheel(view, getListImages());
        }
    }

    private ArrayList<Integer> getListImages(){
        if (mListImages == null) {
            final ArrayList<Integer> list = new ArrayList<>();
            TypedArray images = getResources().obtainTypedArray(R.array.slots_images);
            for (int i = 0; i < images.length(); ++i) {
                list.add(images.getResourceId(i, 0));
            }
            images.recycle();
            this.mListImages = list;
        }
        return mListImages;
    }

    private View mControlsView;

    private void iniWheel(WheelView wheelView, ArrayList<Integer> list) {
        wheelView.setViewAdapter(new GameAdapter(this, list));
        wheelView.setCurrentItem((int) (Math.random() * 10.0d));
        wheelView.setVisibleItems(4);
        wheelView.setCyclic(true);
        wheelView.setEnabled(false);
    }

    private void generateRandomScrollWheels() {
        Random random = new Random();
        for (WheelView view : mListWheel) {
            view.scroll(((int) ((Math.random() * ((double) random.nextInt(30))) + 20.0d)) - 350,
                    random.nextInt(3000) + 2000);
        }
    }

    @SuppressLint("InflateParams")
    private AlertDialog initDialogWin() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater factory = LayoutInflater.from(this);
        final View view = factory.inflate(R.layout.item_dialog_win, null);
        builder.setView(view);

        AlertDialog dialog = builder.create();
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        return dialog;
    }


    private void showDialogWin() {
        if (mDialogWin == null) {
            mDialogWin = initDialogWin();
        }
        if (!mDialogWin.isShowing()) {
            mDialogWin.show();
        }
    }

    private void generateWin() {
        Random random = new Random();
        int value = random.nextInt(10 + 1); // [1;10]
        Log.d(TAG, "Random win value - " + value);
        if (value % 2 == 0) { // Если четное то выводим диалог
            showDialogWin();
        }
    }

    private void initStateWheels() {
        for (int i = 0; i < mStateWheels.size(); ++i){
            mStateWheels.set(i, false);
        }
    }

    private boolean checkAllWheelsFinished() {
        return !mStateWheels.contains(false);
    }

    @Override
    public void onScrollingStarted(WheelView wheel) {
        Log.d(TAG, "onScrollingStarted - " + String.valueOf(wheel.getCurrentItem()));
    }

    @Override
    public void onScrollingFinished(WheelView wheel) {
        Log.d(TAG, "onScrollingFinished - " + String.valueOf(wheel.getCurrentItem()));

        mStateWheels.set(mListWheel.indexOf(wheel), true);
        if (checkAllWheelsFinished()) {
            generateWin();
        }
    }

    @NonNull
    public static Intent getGameActivityIntent(Context context) {
        return new Intent(context, StartGameActivity.class);
    }
}
