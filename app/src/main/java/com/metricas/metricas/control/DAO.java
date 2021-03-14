package com.metricas.metricas.control;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.metricas.metricas.BD.conexion;
import com.metricas.metricas.BD.mapeo;
import com.metricas.metricas.BD.metricas;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DAO {

    public static long registro;

    public void create(conexion conexion, metricas metrica){
        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(mapeo.cantidadOperadores,metrica.getCantidadOperadores());
        valores.put(mapeo.cantidadOperandos,metrica.getCantidadOperandos());
        valores.put(mapeo.ocurrenciasOperadores,metrica.getOcurrenciasOperadores());
        valores.put(mapeo.ocurrenciasOperandos,metrica.getOcurrenciasOperandos());
        valores.put(mapeo.longitud, metrica.getLongitud());
        valores.put(mapeo.vocabulario,metrica.getVocabulario());
        valores.put(mapeo.volumen,metrica.getVolumen());
        valores.put(mapeo.dificultad,metrica.getDificultad());
        valores.put(mapeo.nivel,metrica.getNivel());
        valores.put(mapeo.esfuerzo,metrica.getEsfuerzo());
        valores.put(mapeo.tiempo,metrica.getTiempo());
        valores.put(mapeo.bugs,metrica.getBugs());
        registro = db.insert(mapeo.tabla,mapeo.id,valores);
    }

    public static ArrayList <String> listaInformacion;
    ArrayList <metricas> listaMetricas;
    public void consultar(conexion conexion){
        SQLiteDatabase db = conexion.getReadableDatabase();
        metricas metrica = null;
        listaMetricas =new ArrayList<metricas>();
        //select * from
        Cursor cursor=db.rawQuery("SELECT * FROM "+mapeo.tabla,null);

        while (cursor.moveToNext()){
            metrica = new metricas();
            metrica.setIdMetrica(cursor.getInt(0));
            metrica.setCantidadOperadores(cursor.getInt(1));
            metrica.setCantidadOperandos(cursor.getInt(2));
            metrica.setOcurrenciasOperadores(cursor.getInt(3));
            metrica.setOcurrenciasOperandos(cursor.getInt(4));
            metrica.setLongitud(cursor.getString(5));
            metrica.setVocabulario(cursor.getString(6));
            metrica.setVolumen(cursor.getString(7));
            metrica.setDificultad(cursor.getString(8));
            metrica.setNivel(cursor.getString(9));
            metrica.setEsfuerzo(cursor.getString(10));
            metrica.setTiempo(cursor.getString(11));
            metrica.setBugs(cursor.getString(12));
            listaMetricas.add(metrica);

        }
        System.out.println("cursor: " + cursor);
        System.out.println("metricas: "+listaMetricas);
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion=new ArrayList<String>();
        for (int i = 0; i< listaMetricas.size(); i++){
            listaInformacion.add(listaMetricas.get(i).getIdMetrica()+" | " +listaMetricas.get(i).getCantidadOperadores()+" | "+
                    listaMetricas.get(i).getCantidadOperandos()+" | "+ listaMetricas.get(i).getOcurrenciasOperadores()+" | "
                    +listaMetricas.get(i).getOcurrenciasOperandos()+ " | "+listaMetricas.get(i).getLongitud()+" | "+
                    listaMetricas.get(i).getVocabulario()+" | "+ listaMetricas.get(i).getVolumen()+" | "+
                    listaMetricas.get(i).getDificultad()+" | "+ listaMetricas.get(i).getNivel()+" | "+listaMetricas.get(i).getEsfuerzo()+
                    " | "+listaMetricas.get(i).getTiempo()+ " | "+listaMetricas.get(i).getBugs());
        }

    }
}
