package com.example.mislugares5.datos;

import com.example.mislugares5.modelo.Lugar;

public interface RepositorioLugares
{
    Lugar elemento(int id);//Devuelve un lugar a partir de un entero que será la posición
    void anyade(Lugar lugar);//Pone un lugar el el repositorio
    int nuevo(); //Añade un elemento en blanco devuelve la posición (imagino)
    void borrar(int id);
    int tamanyo();//Devuelve el tamaño del repositorio. Número de elementos
    void actualiza(int id, Lugar lugar);//Modifica un elemento
}
