package com.jm.jmmanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jm.jutils.R;
import com.jm.jutils_core.VoiceUtils;

public class VoiceActivity extends AppCompatActivity {


    TextView tv_info;
    SeekBar seek_bar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_voice_info);

        tv_info=findViewById(R.id.tv_info);
        seek_bar=findViewById(R.id.seek_bar);

        tv_info.setMovementMethod(ScrollingMovementMethod.getInstance());
        tv_info.setText(
                "\n\n 当前音量:\n\t\t" + VoiceUtils.getVolume(this)

        );

        seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                VoiceUtils.setVolume(VoiceActivity.this,i);
                tv_info.setText(
                        "\n\n 当前音量:\n\t\t" + VoiceUtils.getVolume(VoiceActivity.this)

                );

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public static void showActivity(Context context){

        Intent intent=new Intent();
        intent.setClass(context, VoiceActivity.class);
        context.startActivity(intent);
    }
}
