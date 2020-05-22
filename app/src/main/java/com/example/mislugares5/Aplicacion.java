package com.example.mislugares5;
import android.app.Application;
import android.content.SharedPreferences;
import com.example.mislugares5.LugaresLista;
import com.example.mislugares5.RepositorioLugares;
public class Aplicacion extends Application {
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
