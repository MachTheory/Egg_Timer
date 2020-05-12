package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    TextView textTimer;
    SeekBar seekBar;
    Boolean isActive = false;
    Button button;
    CountDownTimer countDownTimer;

    public void resetTimer(){
        textTimer.setText("00:30");
        seekBar.setProgress(300);
        seekBar.setEnabled(true);
        isActive = false;
        countDownTimer.cancel();
        button.setText("Start");

    }


    public void startTimer(View view){

        if(isActive){
            resetTimer();
        } else {

            isActive = true;
            seekBar.setEnabled(false);
            button = findViewById(R.id.button);
            button.setText("Stop");


            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {

                public void onTick(long milliUntilEnd) {

                    updateTimer((int) milliUntilEnd / 1000);
                    Log.i("timer", Integer.toString((int) milliUntilEnd / 1000));
                }

                public void onFinish() {

                    mediaPlayer.start();
                    Log.i("Timer", "Done!");
                    //figure out how to get restart w/o crashing
                }
            }.start();
        }

    }

    public void updateTimer(int secondsLeft){

        int min = secondsLeft / 60;
        int seconds = secondsLeft - (min * 60);
        String secondsString = Integer.toString(seconds);

        if (seconds < 9){
            secondsString = "0" + secondsString;
        }

        textTimer.setText(String.format("%s:%s", Integer.toString(min), secondsString));




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textTimer = (TextView)findViewById(R.id.textTimer);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setMax(600);
        seekBar. setProgress(300);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });





    }
}
