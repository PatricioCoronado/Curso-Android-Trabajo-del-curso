package com.example.mislugares5.modelo;

import com.example.mislugares5.R;

public enum TipoLugar
{
    //Lista de enumerados con 2 atributos
    OTROS("Otros", R.drawable.otros),
    RESTAURANTE("Restaurante",R.drawable.restaurante),
    BAR("Bar",R.drawable.bar),
    COPAS("Copas",R.drawable.copas),
    ESPECTACULO("Espectáculo",R.drawable.espectaculos),
    HOTEL("Hotel",R.drawable.hotel),
    COMPRAS("Compras",R.drawable.compras),
    EDUCACION("Educación",R.drawable.educacion),
    DEPORTE("Deporte",R.drawable.deporte),
    NATURALEZA("Naturaleza",R.drawable.naturaleza),
    GASOLINERA("Gasolinera",R.drawable.gasolinera);
    //Declaración de los atributos
    private final String texto;
    private final int recurso;
    //Constructor
    private TipoLugar(String texto, int recurso)
    {
        this.texto=texto;
        this.recurso=recurso;
    }
    //Getters
    public String getTexto(){return this.texto;}
    public int getRecurso(){return recurso;}
}
