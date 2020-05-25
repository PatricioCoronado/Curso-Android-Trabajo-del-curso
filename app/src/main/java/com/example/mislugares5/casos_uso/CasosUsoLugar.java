package com.example.mislugares5.casos_uso;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.example.mislugares5.datos.RepositorioLugares;
import com.example.mislugares5.modelo.Lugar;
import com.example.mislugares5.presentacion.EdicionLugarActivity;
import com.example.mislugares5.presentacion.VistaLugarActivity;

public class CasosUsoLugar
{
    private Activity actividad;
    private RepositorioLugares lugares;

    public CasosUsoLugar(Activity actividad, RepositorioLugares lugares) {
        this.actividad = actividad;
        this.lugares = lugares;
    }
    // OPERACIONES BÁSICAS
    public void mostrar(int pos)
    {
        Intent i = new Intent(actividad, VistaLugarActivity.class);
        i.putExtra("pos", pos);
        actividad.startActivity(i);
    }
    public void borrar(final int id)
    {
        lugares.borrar(id);
        actividad.finish();
    }
    public void editar(int pos, int codidoSolicitud)
    {
        Intent i = new Intent(actividad, EdicionLugarActivity.class);
        i.putExtra("pos", pos);
        actividad.startActivityForResult(i, codidoSolicitud);
        //Código solicitud es una firma para que al devolver el control se sepa quien lo hace
    }
    public void guardar(int id, Lugar nuevoLugar)
    {
        lugares.actualiza(id,nuevoLugar);
    }

/*
    public void editar(int pos, int codidoSolicitud) {
        Intent i = new Intent(actividad, EdicionLugarActivity.class);
        i.putExtra("pos", pos);
        actividad.startActivityForResult(i, codidoSolicitud);


    }
    */
}
