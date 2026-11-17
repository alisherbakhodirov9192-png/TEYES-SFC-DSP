
package com.example.fhp009md;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.SeekBar;
import android.media.AudioManager;
import android.media.audiofx.Equalizer;
import android.media.audiofx.PresetReverb;

public class MainActivity extends Activity {

    private Equalizer equalizer;
    private PresetReverb reverb;
    private AudioManager audioManager;
    private TextView status;

    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        audioManager = (AudioManager)
            getSystemService(AUDIO_SERVICE);

        getWindow().setStatusBarColor(Color.BLACK);
        getWindow().setNavigationBarColor(Color.BLACK);

        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(25, 25, 25, 25);
        layout.setBackgroundColor(Color.rgb(10,15,25));

        setContentView(layout);

        addText("CARROZZERIA SFC DSP", 25);

        addText("TEYES CC3L AUDIO CONTROL", 15);

        status = addText("Tizim tayyor", 16);

        addButton("STUDIO", () -> studio());

        addButton("HALL", () -> hall());

        addButton("OFF", () -> off());

        addText("MASTER VOLUME", 18);

        SeekBar volume = new SeekBar(this);

        int max = audioManager.getStreamMaxVolume(
            AudioManager.STREAM_MUSIC);

        volume.setMax(max);

        volume.setProgress(
            audioManager.getStreamVolume(
                AudioManager.STREAM_MUSIC));

        volume.setOnSeekBarChangeListener(
            new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(
                SeekBar seekBar, int value, boolean user) {

                if (user) {
                    audioManager.setStreamVolume(
                        AudioManager.STREAM_MUSIC,
                        value, 0);
                }
            }

            public void onStartTrackingTouch(SeekBar s) {}

            public void onStopTrackingTouch(SeekBar s) {}
        });

        layout.addView(volume);

        initializeEffects();
    }

    private void initializeEffects() {
        try {
            equalizer = new Equalizer(0, 0);
            equalizer.setEnabled(false);

            reverb = new PresetReverb(0, 0);
            reverb.setEnabled(false);

            status.setText("Audio effektlar yaratildi");

        } catch (Exception e) {
            status.setText(
                "Audio effekt xatosi: " +
                e.getClass().getSimpleName());
        }
    }

    private void studio() {
        try {
            if (equalizer == null) {
                initializeEffects();
            }

            if (equalizer == null) return;

            if (reverb != null) {
                reverb.setEnabled(false);
            }

            short bands = equalizer.getNumberOfBands();

            for (short i = 0; i < bands; i++) {
                equalizer.setBandLevel(i, (short) 0);
            }

            equalizer.setEnabled(true);

            status.setText("STUDIO rejimi yoqildi");

        } catch (Exception e) {
            status.setText("STUDIO ishlamadi: " +
                e.getClass().getSimpleName());
        }
    }

    private void hall() {
        try {
            if (reverb == null) {
                initializeEffects();
            }

            if (reverb == null) return;

            if (equalizer != null) {
                equalizer.setEnabled(false);
            }

            reverb.setPreset(
                PresetReverb.PRESET_LARGEHALL);

            reverb.setEnabled(true);

            status.setText("HALL rejimi yoqildi");

        } catch (Exception e) {
            status.setText("HALL ishlamadi: " +
                e.getClass().getSimpleName());
        }
    }

    private void off() {
        try {
            if (equalizer != null) {
                equalizer.setEnabled(false);
            }

            if (reverb != null) {
                reverb.setEnabled(false);
            }

            status.setText("Effektlar o'chirildi");

        } catch (Exception e) {
            status.setText("O'chirish xatosi");
        }
    }

    private TextView addText(String text, int size) {
        TextView view = new TextView(this);

        view.setText(text);
        view.setTextColor(Color.WHITE);
        view.setTextSize(size);
        view.setGravity(Gravity.CENTER);
        view.setPadding(10, 15, 10, 15);

        layout.addView(view);

        return view;
    }

    private void addButton(String name, Runnable action) {
        Button button = new Button(this);

        button.setText(name);
        button.setTextSize(18);

        layout.addView(button);

        button.setOnClickListener(v -> action.run());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (equalizer != null) {
            equalizer.release();
        }

        if (reverb != null) {
            reverb.release();
        }
    }
}
