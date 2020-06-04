package com.example.mislugares5.presentacion;

import android.database.Cursor;

import com.example.mislugares5.datos.LugaresBD;
import com.example.mislugares5.datos.RepositorioLugares;
import com.example.mislugares5.modelo.Lugar;

public class AdaptadorLugaresBD extends AdaptadorLugares
{

    protected Cursor cursor;

    public AdaptadorLugaresBD(RepositorioLugares lugares, Cursor cursor)
    {
        super(lugares);
        this.cursor = cursor;
    }

    public Cursor getCursor() {
        return cursor;
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }

    public Lugar lugarPosicion(int posicion) {
        cursor.moveToPosition(posicion);
        return LugaresBD.extraeLugar(cursor);
    }

    public int idPosicion(int posicion) {
        cursor.moveToPosition(posicion);
        if (cursor.getCount()>0) return cursor.getInt(0);
        else                     return -1;

        //return cursor.getInt(0);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int posicion) {
        Lugar lugar = lugarPosicion(posicion);
        holder.personaliza(lugar);
        holder.itemView.setTag(new Integer(posicion));
    }

    @Override public int getItemCount() {
        return cursor.getCount();
    }
}
