
package com.example.fhp009md;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.*;
import android.graphics.Typeface;

public class MainActivity extends Activity {

    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(Color.rgb(10,15,25));
        getWindow().setNavigationBarColor(Color.BLACK);

        ScrollView scroll = new ScrollView(this);
        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(20,25,20,25);
        layout.setBackgroundColor(Color.rgb(10,15,25));

        scroll.addView(layout);
        setContentView(scroll);

        title("CARROZZERIA SFC DSP");
        subtitle("UNIVERSAL TEYES EDITION");

        section("SOUND FIELD CONTROL");

        String[] effects = {
            "OFF", "STUDIO", "CLUB",
            "HALL", "DOME", "CONCERT"
        };

        Spinner sfc = new Spinner(this);
        sfc.setAdapter(new ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            effects
        ));
        layout.addView(sfc);

        section("EQUALIZER");

        String[] bands = {
            "60 Hz","120 Hz","250 Hz","500 Hz",
            "1 kHz","2 kHz","4 kHz","8 kHz",
            "16 kHz"
        };

        for (String band : bands) {
            TextView label = text(band);
            layout.addView(label);

            SeekBar seek = new SeekBar(this);
            seek.setMax(24);
            seek.setProgress(12);
            layout.addView(seek);
        }

        section("BASS CONTROL");

        addSlider("Bass Boost");
        addSlider("Subwoofer Level");
        addSlider("Stereo Width");
        addSlider("3D Sound");

        section("PRESETS");

        String[] presets = {
            "Custom", "Carrozzeria",
            "Studio", "Club", "Concert"
        };

        Spinner preset = new Spinner(this);
        preset.setAdapter(new ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            presets
        ));
        layout.addView(preset);

        subtitle("TEYES UNIVERSAL DSP • VERSION 0.2");
    }

    void title(String value) {
        TextView t = text(value);
        t.setTextSize(25);
        t.setTypeface(null, Typeface.BOLD);
        t.setGravity(Gravity.CENTER);
        layout.addView(t);
    }

    void section(String value) {
        TextView t = text(value);
        t.setTextSize(17);
        t.setTypeface(null, Typeface.BOLD);
        t.setPadding(0,25,0,12);
        layout.addView(t);
    }

    void subtitle(String value) {
        TextView t = text(value);
        t.setTextSize(12);
        t.setGravity(Gravity.CENTER);
        layout.addView(t);
    }

    TextView text(String value) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextColor(Color.WHITE);
        t.setPadding(8,8,8,8);
        return t;
    }

    void addSlider(String name) {
        layout.addView(text(name));
        SeekBar bar = new SeekBar(this);
        bar.setMax(100);
        bar.setProgress(50);
        layout.addView(bar);
    }
}
