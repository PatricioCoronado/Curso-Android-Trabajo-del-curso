package com.example.mislugares5.presentacion;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mislugares5.R;
import com.example.mislugares5.casos_uso.CasosUsoLugar;
import com.example.mislugares5.datos.LugaresBD;
import com.example.mislugares5.datos.RepositorioLugares;
import com.example.mislugares5.modelo.Lugar;
import com.example.mislugares5.modelo.TipoLugar;
import com.example.mislugares5.presentacion.Aplicacion;

import java.text.DateFormat;
import java.util.Date;

public class EdicionLugarActivity extends AppCompatActivity
{
    private AdaptadorLugaresBD adaptador;
    //private RepositorioLugares lugares;
    private LugaresBD lugares;
    private CasosUsoLugar usoLugar;
    private int pos;
    private int _id;
    private Lugar lugar;
    private EditText nombre;
    private Spinner tipo;
    private EditText direccion;
    private EditText telefono;
    private EditText url;
    private EditText comentario;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edicion_lugares);
        adaptador = ((Aplicacion) getApplication()).adaptador;
        lugares = ((Aplicacion) getApplication()).lugares;
        usoLugar = new CasosUsoLugar(this, lugares, adaptador);
        Bundle extras = getIntent().getExtras();
        //pos=0;
        pos = extras.getInt("pos", 0);
        //lugar = lugares.elemento(pos);
        lugar =  adaptador.lugarPosicion (pos);
        //Spinner
        tipo = findViewById(R.id.tipo);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, TipoLugar.getNombres());
        adaptador.setDropDownViewResource(android.R.layout.
                simple_spinner_dropdown_item);
        //Pone los valores
        tipo.setAdapter(adaptador);
        tipo.setSelection(lugar.getTipo().ordinal());
        //
        actualizaVistas();
    }
    //Menú
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu)
    {
        getMenuInflater().inflate(R.menu.edicion_lugar, menu);
        return true;
    }
    @Override public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.accion_cancelar:
                return true;
            case R.id.accion_guardar:
                lugar.setNombre(nombre.getText().toString());
                lugar.setTipo(TipoLugar.values()[tipo.getSelectedItemPosition()]);
                lugar.setDireccion(direccion.getText().toString());
                lugar.setTelefono(Integer.parseInt(telefono.getText().toString()));
                lugar.setUrl(url.getText().toString());
                lugar.setComentario(comentario.getText().toString());
                //usoLugar.guardar(pos, lugar);
                int _id = adaptador.idPosicion(pos);//Lee la posición en la tabla de la B de Datos.
                usoLugar.guardar(_id, lugar);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void actualizaVistas()
    {
        //Teléfono
        telefono = findViewById(R.id.telefono);
        telefono.setText(Integer.toString(lugar.getTelefono()));
        //nombre
        nombre = findViewById(R.id.nombre);
        nombre.setText(lugar.getNombre());
        //dirección
        direccion = findViewById(R.id.direccion);//Carga el TextView en mi variable "direccion"
        direccion.setText(lugar.getDireccion());//Y lo muestra en e layout actual
        //url
        url = findViewById(R.id.url);
        url.setText(lugar.getUrl());
        // comentarios
        comentario = findViewById(R.id.comentario);
        comentario.setText(lugar.getComentario());
    }
}
