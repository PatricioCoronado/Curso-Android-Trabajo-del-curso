package com.example.mislugares5.presentacion;
import android.app.Application;

import com.example.mislugares5.datos.LugaresLista;
import com.example.mislugares5.datos.RepositorioLugares;
public class Aplicacion extends Application
{
    //Los atributos de la clase son los objetos a los que queremos acceder
    // En este caso un repositorio de Lugares
    public RepositorioLugares lugares = new LugaresLista();
    @Override public void onCreate() {
        super.onCreate();
    }
    //Getter.....................................................
    //Para leer el repositorio de lugares
    //RepositorioLugares misLugares = ((Aplicacion) getApplication()).getLugares();
    public RepositorioLugares getLugares() {
        return lugares;
    }
}
