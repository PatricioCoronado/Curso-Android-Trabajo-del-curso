package com.example.mislugares5.presentacion;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mislugares5.R;
import com.example.mislugares5.casos_uso.CasosUsoLugar;
import com.example.mislugares5.datos.RepositorioLugares;
import com.example.mislugares5.modelo.Lugar;
import java.text.DateFormat;
import java.util.Date;

public class VistaLugarActivity extends AppCompatActivity {
    private RepositorioLugares lugares;
    private CasosUsoLugar usoLugar;
    private int pos;
    private Lugar lugar;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_lugar);
        Bundle extras = getIntent().getExtras();
        pos = extras.getInt("pos", 0);
        lugares = ((Aplicacion) getApplication()).lugares;
        usoLugar = new CasosUsoLugar(this, lugares);
        lugar = lugares.elemento(pos);
        actualizaVistas(pos);
    }
    public void actualizaVistas(int pos) {
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
}

