package com.example.mislugares5.casos_uso;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.example.mislugares5.datos.LugaresBD;
import com.example.mislugares5.datos.RepositorioLugares;
import com.example.mislugares5.modelo.GeoPunto;
import com.example.mislugares5.modelo.Lugar;
import com.example.mislugares5.presentacion.AdaptadorLugaresBD;
import com.example.mislugares5.presentacion.Aplicacion;
import com.example.mislugares5.presentacion.EdicionLugarActivity;
import com.example.mislugares5.presentacion.VistaLugarActivity;

public class CasosUsoLugar
{
    private Activity actividad;
    //private RepositorioLugares lugares;
    private LugaresBD lugares;
    private AdaptadorLugaresBD adaptador;

    //public CasosUsoLugar(Activity actividad, RepositorioLugares lugares) {
    public CasosUsoLugar(Activity actividad, LugaresBD lugares, AdaptadorLugaresBD adaptador)
    {
        this.actividad = actividad;
        this.lugares = lugares;
        this.adaptador = adaptador;
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
        adaptador.setCursor(lugares.extraeCursor());
        adaptador.notifyDataSetChanged();
        actividad.finish();
        /*
        lugares.borrar(id);
        actividad.finish();
        */
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
        adaptador.setCursor(lugares.extraeCursor());
        adaptador.notifyDataSetChanged();
    }

    public void compartir(Lugar lugar) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_TEXT,
                lugar.getNombre() + " - " + lugar.getUrl());
        actividad.startActivity(i);
    }

    public void llamarTelefono(Lugar lugar) {
        actividad.startActivity(new Intent(Intent.ACTION_DIAL,
                Uri.parse("tel:" + lugar.getTelefono())));
    }

    public void verPgWeb(Lugar lugar) {
        actividad.startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse(lugar.getUrl())));
    }

    public final void verMapa(Lugar lugar)
    {
        double lat = lugar.getPosicion().getLatitud();
        double lon = lugar.getPosicion().getLongitud();
        Uri uri = lugar.getPosicion() != GeoPunto.SIN_POSICION
                ? Uri.parse("geo:" + lat + ',' + lon)
                : Uri.parse("geo:0,0?q=" + lugar.getDireccion());
        actividad.startActivity(new Intent("android.intent.action.VIEW", uri));
    }
    public void actualizaPosLugar(int pos, Lugar lugar)
    {
        int id = adaptador.idPosicion(pos);
        guardar(id, lugar);  //
    }
    //public void nuevo() { return; }

    public void nuevo() {
        int id = lugares.nuevo();
        GeoPunto posicion = ((Aplicacion) actividad.getApplication()).posicionActual;
        if (!posicion.equals(GeoPunto.SIN_POSICION)) {
            Lugar lugar = lugares.elemento(id);
            lugar.setPosicion(posicion);
            lugares.actualiza(id, lugar);
        }
        Intent i = new Intent(actividad, EdicionLugarActivity.class);
        i.putExtra("_id", id);
        actividad.startActivity(i);
    }




}
