package com.example.findthedifferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.util.Log;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private Button button3;
    private final String main = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.buttonlvl1);
        button1.setOnClickListener(view -> {
            Intent intent = new Intent(this, LevelActivity.class);
            intent.putExtra("level", "1");
            startActivity(intent);
        });
        Log.d(main,"Кнопка 1 создана");

        button2 = findViewById(R.id.buttonlvl2);
        button2.setOnClickListener(view -> {
            Intent intent = new Intent(this, LevelActivity.class);
            intent.putExtra("level", "2");
            startActivity(intent);
        });
        Log.d(main,"Кнопка 2 создана");
        button3 = findViewById(R.id.buttonlvl3);
        button3.setOnClickListener(view -> {
            Intent intent = new Intent(this, LevelActivity.class);
            intent.putExtra("level", "3");
            startActivity(intent);
        });
        Log.d(main,"Кнопка 3 создана");

    }

    @Override
    protected void onResume() {
        super.onResume();
        AppDatabase appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database").build();
        LevelDao levelDao = appDatabase.levelDao();
        levelDao.getLevel(1).observe(this, level -> {
            if(level != null){
                String completed1 = getString(R.string.textCompleted);
                String textBut1 = getString(R.string.textLevelCompleted, 1, completed1);
                button1.setText(textBut1);
            }
        });
        levelDao.getLevel(2).observe(this, level -> {
            if(level != null){
                String completed2 = getString(R.string.textCompleted);
                String textBut2 = getString(R.string.textLevelCompleted, 2, completed2);
                button2.setText(textBut2);
            }
        });
        levelDao.getLevel(3).observe(this, level -> {
            if(level != null){
                String completed3 = getString(R.string.textCompleted);
                String textBut3 = getString(R.string.textLevelCompleted, 3, completed3);
                button3.setText(textBut3);
            }
        });
    }
}