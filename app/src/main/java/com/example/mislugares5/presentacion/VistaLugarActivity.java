package com.example.mislugares5.presentacion;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mislugares5.R;
import com.example.mislugares5.casos_uso.CasosUsoActividades;
import com.example.mislugares5.casos_uso.CasosUsoLugar;
import com.example.mislugares5.datos.LugaresBD;
import com.example.mislugares5.datos.RepositorioLugares;
import com.example.mislugares5.modelo.Lugar;
import java.text.DateFormat;
import java.util.Date;

public class VistaLugarActivity extends AppCompatActivity
{
    public int _id = -1;
    private AdaptadorLugaresBD adaptador;
    //private RepositorioLugares lugares;
    private LugaresBD lugares;
    private CasosUsoLugar usoLugar;
    private  CasosUsoActividades usoActividad;
    private int pos;
    private Lugar lugar;
    //private Object Menu;
    final static int RESULTADO_EDITAR = 1;

    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_lugar);
        adaptador = ((Aplicacion) getApplication()).adaptador;
        lugares = ((Aplicacion) getApplication()).lugares;
        Bundle extras = getIntent().getExtras();
        //
        //pos = extras.getInt("pos", 0);
        if (extras != null) pos = extras.getInt("pos", 0);
        else                pos = 0;
        _id = adaptador.idPosicion(pos);
        //
        lugares = ((Aplicacion) getApplication()).lugares;
        usoLugar = new CasosUsoLugar(this, lugares,adaptador);
        usoActividad = new CasosUsoActividades(this);
        //lugar = lugares.elemento(pos);
        lugar = adaptador.lugarPosicion (pos);
        actualizaVistas();
    }









    //Menú
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu)
    {
        getMenuInflater().inflate(R.menu.vista_lugar, menu);
        return true;
    }
    //Respuesta al resultado del menú
    @Override public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.accion_compartir:
                usoLugar.compartir(lugar);
                return true;
            case R.id.accion_llegar:
                usoLugar.verMapa(lugar);
                return true;
            case R.id.accion_editar://Lanza la actividad que edita el lugar
                usoLugar.editar(pos,RESULTADO_EDITAR);
                return true;
            case R.id.accion_borrar:
                confirmarBorradoLugar();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //Recepción de la respuesta de EdicionLugarActivity
    @Override
        protected void onActivityResult(int requestCode, int resultCode,Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULTADO_EDITAR)
        {
            lugar = lugares.elemento(_id);
            pos = adaptador.idPosicion(_id);
            //pos = adaptador.posicionId(_id);
            actualizaVistas();
            findViewById(R.id.scrollView1).invalidate();
        }
    }

   public void confirmarBorradoLugar()
    {
        new AlertDialog.Builder(this)
                .setTitle("Se va a borrar un lugar")
                .setMessage("¿Realmente quiere hacerlo?")
                .setPositiveButton("Borralo yaaaa", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int whichButton)
                    {
                        usoLugar.borrar(pos);
                    }
                }).setNegativeButton("¡No por Dios no!", null).show();
    }
    public void actualizaVistas() {
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
        if (lugar.getDireccion().isEmpty()) {//Si direccion es cadena vacia..
            findViewById(R.id.direccion).setVisibility(View.GONE);//La hace invisible
        } else
            {//Si el string no está vacio...
                findViewById(R.id.direccion).setVisibility(View.VISIBLE);
                TextView direccion = findViewById(R.id.direccion);//Carga el TextView en mi variable "direccion"
                direccion.setText(lugar.getDireccion());//Y lo muestra en e layout actual
            }
        //Teléfono
        if (lugar.getTelefono() == 0) {
            findViewById(R.id.telefono).setVisibility(View.GONE);
        } else {
            findViewById(R.id.telefono).setVisibility(View.VISIBLE);
            TextView telefono = findViewById(R.id.telefono);
            telefono.setText(Integer.toString(lugar.getTelefono()));
        }
        //url
        if(lugar.getUrl().isEmpty())//Si no hay url
            findViewById(R.id.url).setVisibility(View.GONE);
        else {
            findViewById(R.id.url).setVisibility(View.VISIBLE);
            TextView url = findViewById(R.id.url);
            url.setText(lugar.getUrl());
        }
        // comentarios
        if(lugar.getComentario().isEmpty())
            findViewById(R.id.comentario).setVisibility(View.GONE);
        else
        {
            findViewById(R.id.comentario).setVisibility(View.VISIBLE);
            TextView comentario = findViewById(R.id.comentario);
            comentario.setText(lugar.getComentario());
        }
        TextView fecha = findViewById(R.id.fecha);
        fecha.setText(DateFormat.getDateInstance().format(
                new Date(lugar.getFecha())));
        TextView hora = findViewById(R.id.hora);
        hora.setText(DateFormat.getTimeInstance().format(
                new Date(lugar.getFecha())));
        RatingBar valoracion = findViewById(R.id.valoracion);
        valoracion.setRating(lugar.getValoracion());
        //Crea un listener para el RatingBar
        valoracion.setOnRatingBarChangeListener
        (
                 new RatingBar.OnRatingBarChangeListener()
                 {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar,float valor, boolean fromUser)
                    {
                        lugar.setValoracion(valor);
                    }
                }
        );
    }

    public void verMapa(View view) {
        usoLugar.verMapa(lugar);
    }

    public void llamarTelefono(View view) {
        usoLugar.llamarTelefono(lugar);
    }

    public void verPgWeb(View view) {
        usoLugar.verPgWeb(lugar);
    }




}

