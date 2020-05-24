package com.example.mislugares5.presentacion;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mislugares5.R;
import com.example.mislugares5.casos_uso.CasosUsoLugar;
import com.example.mislugares5.datos.RepositorioLugares;
import com.example.mislugares5.modelo.Lugar;
import com.example.mislugares5.presentacion.Aplicacion;

import java.text.DateFormat;
import java.util.Date;

public class EdicionLugarActivity extends AppCompatActivity
{
    private EditText nombre;
    private Spinner tipo;
    private EditText direccion;
    private EditText telefono;
    private EditText url;
    private EditText comentario;
    private int pos;
    private Lugar lugar;

    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edicion_lugares);
        Bundle extras = getIntent().getExtras();
        pos = extras.getInt("pos", 0);
        RepositorioLugares lugares = ((Aplicacion) getApplication()).lugares;
        CasosUsoLugar usoLugar = new CasosUsoLugar(this, lugares);
        Lugar lugar = lugares.elemento(pos);
        actualizaVistas(pos);
    }
    public void actualizaVistas(int pos)
    {
        //nombre
        TextView nombre = findViewById(R.id.nombre);
        nombre.setText(lugar.getNombre());
        //Logo
        ImageView logo_tipo = findViewById(R.id.logo_tipo);
        logo_tipo.setImageResource(lugar.getTipo().getRecurso());
        //tipo
        TextView tipo = findViewById(R.id.tipo);//Carga en mi variale
        tipo.setText(lugar.getTipo().getTexto());//Muestra el tipo
        //dirección
        TextView direccion = findViewById(R.id.direccion);//Carga el TextView en mi variable "direccion"
        direccion.setText(lugar.getDireccion());//Y lo muestra en e layout actual
        //Teléfono
        TextView telefono = findViewById(R.id.telefono);
        telefono.setText(Integer.toString(lugar.getTelefono()));
        //url
        TextView url = findViewById(R.id.url);
        url.setText(lugar.getUrl());
        // comentarios
        TextView comentario = findViewById(R.id.comentario);
        comentario.setText(lugar.getComentario());
    }
}
