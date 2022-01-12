package com.example.findthedifferences;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class LevelActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;
    private FrameLayout layout;
    private final ListSquare listSquare = new ListSquare();
    private List<Boolean> answers = new ArrayList<>();
    private String level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        level = getIntent().getStringExtra("level");

        for (int i = 0; i < 5; i++) answers.add(false);

        layout = findViewById(R.id.layout);
        imageView = findViewById(R.id.imageLvl);
        textView = findViewById(R.id.textTotalTheDiferences);

        if (level.equals("1"))
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.level1));
        else if (level.equals("2"))
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.level2));
        else if (level.equals("3"))
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.level3));
        Log.d("Ресурс", "Выбран ресурс с соответствующим уровнем");
        imageView.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                Log.d("" + pxToDp(x), "" + pxToDp(y));
                int res = listSquare.isIn(level, pxToDp(x), pxToDp(y));
                if (res >= 0 && !answers.get(res)) {
                    answers.set(res, true);
                    int all = 0;
                    String sub;
                    for (int i = 0; i < 5; i++) if (!answers.get(i)) all++;
                    sub = getString(R.string.iterationTheDifferences, all);
                    textView.setText(sub);
                    addView(x, y);
                    if (all == 0){
                        AppDatabase appDatabase = Room.databaseBuilder(getApplicationContext(),
                                AppDatabase.class, "database").build();
                        LevelDao levelDao = appDatabase.levelDao();
                        Runnable task = () -> levelDao.insert(new Level(Integer.parseInt(level),1));
                        Thread thread = new Thread(task);
                        thread.start();

                        finish();
                        Log.d("FindTheDifferences","Уровень пройден");
                    }
                }
            }

            return true;
        });
    }


    private void addView(float x, float y) {
        Bitmap bitMap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        bitMap = bitMap.copy(bitMap.getConfig(), true);
        Canvas canvas = new Canvas(bitMap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1.0f);
        canvas.drawCircle((x * 100.0f) / (1.0f * imageView.getWidth()), (y * 100.0f) / (1.0f * imageView.getHeight()), 5, paint);
        ImageView view = new ImageView(this);
        view.setImageBitmap(bitMap);
        view.setElevation(10);
        layout.addView(view);
    }

    float dpToPx(int dp) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return (int) ((dp * displayMetrics.density) + 0.5);
    }

    int pxToDp(float px) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return (int) ((px / displayMetrics.density) + 0.5);
    }
}