package com.example.mislugares5.presentacion;
import android.app.Application;

import com.example.mislugares5.datos.LugaresBD;
import com.example.mislugares5.datos.LugaresLista;
import com.example.mislugares5.datos.RepositorioLugares;
//Application resulta engañoso el nombre. En realidad es una clase
// para contener variables globales
public class Aplicacion extends Application
{
    //Los atributos de la clase son los objetos que queremos que sean globales
    // En este caso un repositorio de Lugares
    //public RepositorioLugares lugares = new LugaresLista();
    //El adaptador que rellenará el RecyclerView también es global
    //public AdaptadorLugares adaptador = new AdaptadorLugares(lugares);

    public LugaresBD lugares;
    public AdaptadorLugaresBD adaptador;
    //
    @Override public void onCreate()
    {
        super.onCreate();
        lugares = new LugaresBD(this);
        adaptador= new AdaptadorLugaresBD(lugares, lugares.extraeCursor());
    }
    /*
    @Override public void onCreate() {
        super.onCreate();
    }
    //Getter.....................................................
    //Devuelve el repositorio de lugares
    public RepositorioLugares getLugares()
    {
        return lugares;
    }
  */

}
