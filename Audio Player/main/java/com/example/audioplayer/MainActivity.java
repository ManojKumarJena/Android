package com.example.audioplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton play_pause,next,previous;
    MediaPlayer mediaPlayer;
    SeekBar seekBar;
    TextView startTime,endTime;
    double timeElapsed=0;
    int flag=0;
    int flag2=0;
    Handler handler=new Handler();
    View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //4ACFFF

        play_pause=(ImageButton)findViewById(R.id.play);
        next=(ImageButton)findViewById(R.id.next);
        previous=(ImageButton)findViewById(R.id.previous);
        startTime=(TextView)findViewById(R.id.startTime);
        endTime=(TextView)findViewById(R.id.endTime);
        seekBar=(SeekBar)findViewById(R.id.seekBar);
        play_pause.setOnClickListener(this);
        next.setOnClickListener(this);
        previous.setOnClickListener(this);


        setAudio();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                startTime.setText(getDuration(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                flag2=1;

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                    mediaPlayer.seekTo(seekBar.getProgress());
                    flag2=0;

            }
        });




    }


    @Override
    public void onClick(View view) {
        v=view;

        switch (view.getId())
        {
            case R.id.play:
                if(mediaPlayer.isPlaying())
                {
                    play_pause.setBackgroundResource(R.drawable.play_button);
                    mediaPlayer.pause();
                    flag=0;
                }
                else
                {
                    mediaPlayer.start();
                    seekBar.setMax(mediaPlayer.getDuration());
                    play_pause.setBackgroundResource(R.drawable.pause_button);
                    endTime.setText(getDuration(mediaPlayer.getDuration()));
                    flag=1;
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    handler.postDelayed(updateSeekBar,50);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mediaPlayer.pause();
                            play_pause.setBackgroundResource(R.drawable.play_button);
                            flag=0;
                        }
                    });


                }
                break;
        }


    }

    Runnable updateSeekBar=new Runnable() {
        @Override
        public void run() {
            if (flag2==0)
            {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                startTime.setText(getDuration(mediaPlayer.getCurrentPosition()));
            }

            handler.postDelayed(this,50);

        }
    };

    private String getDuration(long time) {
        String str="";
        long min=time/60000;
        long sec=(time%60000)/1000;
        str+=min+" m:"+sec+" s";
        return str;
    }
    public void setAudio()
    {
        mediaPlayer=MediaPlayer.create(this,R.raw.audio);
        mediaPlayer.setLooping(false);
    }
}
