package com.example.mislugares5;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class        PreferenciasFragment extends PreferenceFragment {
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferencias);
    }
}
