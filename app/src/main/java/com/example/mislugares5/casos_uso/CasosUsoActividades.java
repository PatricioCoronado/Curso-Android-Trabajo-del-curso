package com.example.mislugares5.casos_uso;
import android.app.Activity;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;

import com.example.mislugares5.presentacion.AcercaDeActivity;
import com.example.mislugares5.presentacion.EdicionLugarActivity;
import com.example.mislugares5.presentacion.MainActivity;

import static androidx.core.content.ContextCompat.startActivity;

public class CasosUsoActividades
    {
    private Activity actividad;
    public CasosUsoActividades (Activity actividad)
    {
        this.actividad=actividad;
    }

    public void lanzarAcercaDe()
    {
        //Crea un intent para lanzar la actividad
        Intent i = new Intent(this.actividad,AcercaDeActivity.class);
        actividad.startActivity(i);//Lanza la actividad
    }

}


