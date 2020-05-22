package com.example.mislugares5.presentacion;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.example.mislugares5.R;

public class        PreferenciasFragment extends PreferenceFragment {
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferencias);
    }
}
