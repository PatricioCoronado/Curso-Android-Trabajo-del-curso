package com.example.mislugares5.datos;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mislugares5.modelo.GeoPunto;
import com.example.mislugares5.modelo.Lugar;
import com.example.mislugares5.modelo.TipoLugar;
// Cuando se crea la un objeto de la clase se crea la base de datos SQLiteOpenHelper
public class LugaresBD extends SQLiteOpenHelper implements RepositorioLugares {
//Como implementa la interfaz RepositorioLugares tiene que implementar todos sus métodos
    Context contexto;//Contexto donde se crea el objeto
    //Constructor
    public LugaresBD(Context contexto)
    { //La base de datos se llamará "lugares"
        super(contexto, "lugares", null, 1); //Constructor
        this.contexto = contexto; //Establece el contexto "tampoco lo entiendo muy bien"
    }
    //onCreate es un método de todos los objetos
    //en onCreate se ejecuta una vez al instanciar el objeto creamos una tabla
    @Override public void onCreate(SQLiteDatabase bd)
    {
        // con execSQL ejecuta un comando SQL para crear la tabla
        bd.execSQL("CREATE TABLE lugares " +
                "("+"_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "nombre TEXT, " +
                "direccion TEXT, " +
                "longitud REAL, " +
                "latitud REAL, " +
                "tipo INTEGER, " +
                "foto TEXT, " +
                "telefono INTEGER, " +
                "url TEXT, " +
                "comentario TEXT, " +
                "fecha BIGINT, " +
                "valoracion REAL)");
        //añade lugares
        bd.execSQL("INSERT INTO lugares VALUES (null, "+
                "'Escuela Politécnica Superior de Gandía', "+
                "'C/ Paranimf, 1 46730 Gandia (SPAIN)', -0.166093, 38.995656, "+
                TipoLugar.EDUCACION.ordinal() + ", '', 962849300, "+
                "'http://www.epsg.upv.es', "+
                "'Uno de los mejores lugares para formarse.', "+
                System.currentTimeMillis() +", 3.0)");
        bd.execSQL("INSERT INTO lugares VALUES (null, 'Al de siempre', "+
                "'P.Industrial Junto Molí Nou - 46722, Benifla (Valencia)', "+
                " -0.190642, 38.925857, " +  TipoLugar.BAR.ordinal() + ", '', "+
                "636472405, '', "+"'No te pierdas el arroz en calabaza.', " +
                System.currentTimeMillis() +", 3.0)");
        bd.execSQL("INSERT INTO lugares VALUES (null, 'androidcurso.com', "+
                "'ciberespacio', 0.0, 0.0,"+TipoLugar.EDUCACION.ordinal()+", '', "+
                "962849300, 'http://androidcurso.com', "+
                "'Amplia tus conocimientos sobre Android.', "+
                System.currentTimeMillis() +", 5.0)");
        bd.execSQL("INSERT INTO lugares VALUES (null,'Barranco del Infierno',"+
                "'Vía Verde del río Serpis. Villalonga (Valencia)', -0.295058, "+
                "38.867180, "+TipoLugar.NATURALEZA.ordinal() + ", '', 0, "+
                "'http://sosegaos.blogspot.com.es/2009/02/lorcha-villalonga-via-verde-del-"+
                "rio.html', 'Espectacular ruta para bici o andar', "+
                System.currentTimeMillis() +", 4.0)");
        bd.execSQL("INSERT INTO lugares VALUES (null, 'La Vital', "+
                "'Avda. La Vital,0 46701 Gandia (Valencia)',-0.1720092,38.9705949,"+
                TipoLugar.COMPRAS.ordinal() + ", '', 962881070, "+
                "'http://www.lavital.es', 'El típico centro comercial', "+
                System.currentTimeMillis() +", 2.0)");
    }//onCreate

    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion,
                                    int newVersion) {
    }

    //@Override public Lugar elemento(int id){return null;}
    //Le pasan un id y devuelve el lugar si lo encuentra. si no lanza una excepción
    @Override public Lugar elemento(int id)
    {
        //Sobre cursor se lee el resultado del query
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM lugares WHERE _id = "+id, null);
        //getReadableDatabase será una clase static (digo yo)
        try
        {
            if (cursor.moveToNext())
                return extraeLugar(cursor);//¿Devuelve una función que devuelve un lugar? ¡leches!
            else
                throw new SQLException("Error al acceder al elemento _id = "+id);
        }
        catch (Exception e)
        {
            throw e;
        }
        finally
        {
            if (cursor!=null) cursor.close();
        }
    }//LugaresBD.elemento()
    //Le pasan un cursor apuntando a un lugar, lo lee y lo devuelve
    public static Lugar extraeLugar(Cursor cursor)
    {
        Lugar lugar = new Lugar();
        lugar.setNombre(cursor.getString(1));
        lugar.setDireccion(cursor.getString(2));
        lugar.setPosicion(new GeoPunto(cursor.getDouble(3),
                cursor.getDouble(4)));
        lugar.setTipo(TipoLugar.values()[cursor.getInt(5)]);
        lugar.setFoto(cursor.getString(6));
        lugar.setTelefono(cursor.getInt(7));
        lugar.setUrl(cursor.getString(8));
        lugar.setComentario(cursor.getString(9));
        lugar.setFecha(cursor.getLong(10));
        lugar.setValoracion(cursor.getFloat(11));
        return lugar;
    }//LugaresBD.extraeLugar()



    @Override
    public void anyade(Lugar lugar) {

    }

    @Override
    public int nuevo() {
        return 0;
    }

    @Override
    public void borrar(int id) {

    }

    @Override
    public int tamanyo() {
        return 0;
    }

    //@Override public void actualiza(int id, Lugar lugar) {}
    //Sobreescribe los datos del lugar pasado como argumento con UPDATE "tabla"
    // SET campo1=valor1 ... campo1=valor1 WHERE id = pos;
    @Override public void actualiza(int id, Lugar lugar)
    {
        //Utiliza el método static getWritableDatabase para ejecutar un query
        getWritableDatabase().execSQL("UPDATE lugares SET" +
                "   nombre = '" + lugar.getNombre() +
                "', direccion = '" + lugar.getDireccion() +
                "', longitud = " + lugar.getPosicion().getLongitud() +
                " , latitud = " + lugar.getPosicion().getLatitud() +
                " , tipo = " + lugar.getTipo().ordinal() +
                " , foto = '" + lugar.getFoto() +
                "', telefono = " + lugar.getTelefono() +
                " , url = '" + lugar.getUrl() +
                "', comentario = '" + lugar.getComentario() +
                "', fecha = " + lugar.getFecha() +
                " , valoracion = " + lugar.getValoracion() +
                " WHERE _id = " + id);
    }


    public Cursor extraeCursor() {
        String consulta = "SELECT * FROM lugares";
        SQLiteDatabase bd = getReadableDatabase();
        return bd.rawQuery(consulta, null);
    }


}
