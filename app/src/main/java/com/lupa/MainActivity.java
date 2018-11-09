/*
 * Copyright 2016 Anton Tananaev (anton@traccar.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lupa;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.lupa.R;
import android.os.PowerManager;
public class MainActivity extends AppCompatActivity {

    public static final String PREFERENCE_URL = "url";
    protected PowerManager.WakeLock mWakeLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            initContent();
        }
        final PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);//(Context.POWER_SERVICE);
        this.mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK,"My Tag");//PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag");
        this.mWakeLock.acquire();
    }

    private void initContent() {
        if (PreferenceManager.getDefaultSharedPreferences(this).contains(PREFERENCE_URL)) {
            getFragmentManager().beginTransaction().add(android.R.id.content, new MainFragment()).commit();
        } else {
            getFragmentManager().beginTransaction().add(android.R.id.content, new StartFragment()).commit();
        }
    }

}
