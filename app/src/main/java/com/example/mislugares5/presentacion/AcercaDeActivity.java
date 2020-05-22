    package com.example.mislugares5.presentacion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mislugares5.R;

    public class AcercaDeActivity extends Activity {
    /*
        public TextView textoNombre;
    */
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acercade);
        /*
        textoNombre=findViewById(R.id.TextView02);
         .........Parte para probar el intercambio de resultados.........
        //Prepara el paquete par recibir datos en extras
        Bundle extras = getIntent().getExtras();
        //Lee un string y un entero
        String nombre = extras.getString("usuario");
        int i = extras.getInt("dni");
        //Escribe el nombre
        textoNombre.setText(nombre);
        //Crea un intent para devolver resultados
        String resultado="aquí van todos los datos";//Esto es lo que se devuelve
        Intent intent = new Intent();
        intent.putExtra("resultado",resultado);
        //Devuelve resultados
        setResult(RESULT_OK, intent);
        */

    }
}