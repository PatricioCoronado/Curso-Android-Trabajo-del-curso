package com.example.mislugares5;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.preference.PreferenceManager;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity //Así soporte Toolbar
{
    public Button bAcercaDe;
    public Button bSalir;
    public Button bPreferencias;
    public Button bMostrar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Código del programador
        //Acción del boton MOSTRAR........................
        bMostrar=findViewById(R.id.button01);
        bMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                mostrarPreferencias();
            }
        });
        //Acción del boton ACERCADE........................
        bAcercaDe = findViewById(R.id.button03);
        bAcercaDe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                lanzarAcercaDe(null);
            }
        });
        //Acción del boton SALIR............................
        bSalir=findViewById(R.id.button04);
        bSalir.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                lanzarSalir(null);
            }
       });
        //Acción del boton PREFERENCIAS......................
        bPreferencias=findViewById(R.id.button02);
        bPreferencias.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                lanzarPreferecias();
            }
        });
        //TOOLBAR.............................................
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //FLOATING BUTTON.....................................
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    } // Fin onCreate---------------------------------------------------------------------

    //MENU......................................................
    //Metodo para añadir el menú de menu_scrolling a esta actividad
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }
    //Método para leer la opción elegida en el menú y ejecutar el código asociado
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        boolean objetivoOK=false;
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id)
        {
            case R.id.action_settings:
                objetivoOK=true;
                lanzarPreferecias();
            break;
            case R.id.acercaDe:
                objetivoOK=true;
                lanzarAcercaDe(null);
            break;
        }
        if (objetivoOK) return true;
        return super.onOptionsItemSelected(item);
    }
    //METODOS LANZADOS POR LOS BOTONES---------------------------------------
    public void lanzarAcercaDe(View view) //Botón ACERCADE..................
    {//Lanza una activity
        //Crea un intent para lanzar la actividad
        Intent i = new Intent(this, AcercaDeActivity.class);
        /* .....Parte para enviar información a la activity lanzada........
        i.putExtra("usuario", "Patricio Coronado");
        i.putExtra("dni", 01116607);
        startActivityForResult(i, 1234);//Para recibir resultados
        //startActivity(i);//Para no recibir resultados
        */
        startActivity(i);//Lanza la actividad
    }
    /* //Método  para leer resultados devueltos por alguna actividad lanzada
    @Override protected void onActivityResult(int requestCode, int resultCode,
                                              Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1234 && resultCode == RESULT_OK)
        {

            String res = data.getExtras().getString("resultado");
            bAcercaDe.setText(res);
        }

    }
    */
    //Mostrar las preferencias para que el usuario las cambie
    public void lanzarPreferecias()//Botón PREFERENCIAS................
    //Lanza una nueva activity
    {
        Intent i = new Intent(this,PreferenciasActivity.class);
        startActivity(i);
    }
    public void mostrarPreferencias()
    //Muestra como acceder a las preferencias guardadas en el dispositivo
    {
        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences(this);
        String s = pref.getString("email","@");
        //String s = "notificaciones: "+ pref.getBoolean("notificaciones",true)
        //        +", máximo a listar: " + pref.getString("maximo","?");

        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
    //Método del botón Salir
    public void lanzarSalir(View view)
    {
        finish();
    }
}
