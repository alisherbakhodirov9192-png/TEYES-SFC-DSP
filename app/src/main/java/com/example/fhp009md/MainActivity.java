
package com.example.fhp009md;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.content.pm.ActivityInfo;
import android.content.pm.ProviderInfo;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(Color.rgb(10, 15, 25));
        getWindow().setNavigationBarColor(Color.BLACK);

        ScrollView scroll = new ScrollView(this);
        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(22, 26, 22, 26);
        layout.setBackgroundColor(Color.rgb(10, 15, 25));
        scroll.addView(layout);
        setContentView(scroll);

        addText("CARROZZERIA SFC DSP", 24, true);
        addText("TEYES DSP DIAGNOSTICS", 14, true);
        addText("Faqat tekshiruv. Zavod EQ sozlamalari o'zgartirilmaydi.", 14, false);

        inspectPackage("com.syu.eq");
        inspectPackage("com.syu.ms");

        addText("TEKSHIRUV TUGADI", 18, true);
        addText("Natija ekranini saqlab yuboring.", 14, false);
    }

    private void inspectPackage(String packageName) {
        addText("------------------------------", 12, false);
        addText(packageName, 18, true);

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                packageName,
                PackageManager.GET_ACTIVITIES |
                PackageManager.GET_SERVICES |
                PackageManager.GET_RECEIVERS |
                PackageManager.GET_PROVIDERS
            );

            addText("HOLATI: O'RNATILGAN", 15, true);
            addText("Versiya: " + info.versionName, 13, false);

            int count = 0;

            if (info.activities != null) {
                for (ActivityInfo item : info.activities) {
                    if (item.exported) {
                        count++;
                        addText("Activity: " + item.name, 12, false);
                    }
                }
            }

            if (info.services != null) {
                for (ServiceInfo item : info.services) {
                    if (item.exported) {
                        count++;
                        addText("Service: " + item.name, 12, false);
                    }
                }
            }

            if (info.receivers != null) {
                for (ActivityInfo item : info.receivers) {
                    if (item.exported) {
                        count++;
                        addText("Receiver: " + item.name, 12, false);
                    }
                }
            }

            if (info.providers != null) {
                for (ProviderInfo item : info.providers) {
                    if (item.exported) {
                        count++;
                        addText("Provider: " + item.name, 12, false);
                    }
                }
            }

            addText("Ochiq komponentlar: " + count, 13, true);

        } catch (PackageManager.NameNotFoundException e) {
            addText("TOPILMADI yoki ko'rinmayapti", 14, true);
        } catch (Exception e) {
            addText("Xato: " + e.getClass().getSimpleName(), 12, false);
        }
    }

    private void addText(String value, int size, boolean bold) {
        TextView view = new TextView(this);
        view.setText(value);
        view.setTextColor(Color.WHITE);
        view.setTextSize(size);
        view.setPadding(8, 9, 8, 9);

        if (bold) {
            view.setTypeface(null, Typeface.BOLD);
        }

        layout.addView(view);
    }
}
