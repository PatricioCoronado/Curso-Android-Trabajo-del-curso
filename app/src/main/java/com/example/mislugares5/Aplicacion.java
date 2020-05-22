package com.example.mislugares5;
import android.app.Application;
import android.content.SharedPreferences;

import com.example.mislugares5.LugaresLista;
import com.example.mislugares5.RepositorioLugares;

public class Aplicacion extends Application {

    public RepositorioLugares lugares = new LugaresLista();
    @Override public void onCreate() {
        super.onCreate();
    }

    public RepositorioLugares getLugares() {
        return lugares;
    }
}