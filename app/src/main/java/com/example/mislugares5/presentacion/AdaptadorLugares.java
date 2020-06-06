package com.example.mislugares5.presentacion;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mislugares5.R;
import com.example.mislugares5.datos.RepositorioLugares;
import com.example.mislugares5.modelo.Lugar;

public class AdaptadorLugares extends
        RecyclerView.Adapter<AdaptadorLugares.ViewHolder>
{
    protected View.OnClickListener onClickListener;
    protected RepositorioLugares lugares;         // Lista de lugares a mostrar

    public AdaptadorLugares(RepositorioLugares lugares) {
        this.lugares = lugares;
    }

    //Creamos clase  ViewHolder, con los componenete de lugar a personalizar
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        //El viewHolder contiene los elementos a mostrar en la lista
        public TextView nombre, direccion;
        public ImageView foto;
        public RatingBar valoracion;
        //Constructor
        public ViewHolder(View itemView)//toda clase de la interfaz de android desciende de la clase View
        {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre);
            direccion = itemView.findViewById(R.id.direccion);
            foto = itemView.findViewById(R.id.foto);
            valoracion= itemView.findViewById(R.id.valoracion);
        }
        // Método para personalizar la vista
        public void personaliza(Lugar lugar)
        {
            nombre.setText(lugar.getNombre());
            direccion.setText(lugar.getDireccion());
            int id = R.drawable.otros;
            switch(lugar.getTipo()) {
                case RESTAURANTE:id = R.drawable.restaurante; break;
                case BAR:    id = R.drawable.bar;     break;
                case COPAS:   id = R.drawable.copas;    break;
                case ESPECTACULO:id = R.drawable.espectaculos; break;
                case HOTEL:   id = R.drawable.hotel;    break;
                case COMPRAS:  id = R.drawable.compras;   break;
                case EDUCACION: id = R.drawable.educacion;  break;
                case DEPORTE:  id = R.drawable.deporte;   break;
                case NATURALEZA: id = R.drawable.naturaleza; break;
                case GASOLINERA: id = R.drawable.gasolinera; break;  }
            foto.setImageResource(id);
            foto.setScaleType(ImageView.ScaleType.FIT_END);
            valoracion.setRating(lugar.getValoracion());
        }
    }// Class ViewHolder

    // Creamos el ViewHolder con la vista de un elemento sin personalizar
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // Inflamos la vista desde el xml
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.elemento_lista, parent, false);
        v.setOnClickListener(onClickListener);
        return new ViewHolder(v);
    }

    // Usando como base el ViewHolder y lo personalizamos
    @Override
    public void onBindViewHolder(ViewHolder holder, int posicion) {
        Lugar lugar = lugares.elemento(posicion);
        holder.personaliza(lugar);
    }
    // Indicamos el número de elementos de la lista
    @Override public int getItemCount() {
        return lugares.tamanyo();
    }
    //Setter de on click listener
    public void setOnItemClickListener(View.OnClickListener onClickListener)
    {
        this.onClickListener = onClickListener;
    }
}// class adaptadorLugares