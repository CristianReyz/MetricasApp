package com.metricas.metricas.control;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.metricas.metricas.BD.conexion;
import com.metricas.metricas.BD.mapeo;
import com.metricas.metricas.BD.metricas;
import java.util.ArrayList;

public class DAO {

    public static long registro;
    public static ArrayList <String[]> listaInformacion;
    public static ArrayList <metricas> listaMetricas;

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

    metricas metrica;

    public void consultar(conexion conexion){
        SQLiteDatabase db = conexion.getReadableDatabase();
        listaMetricas =new ArrayList<metricas>();
        //select * from
        @SuppressLint("Recycle") Cursor cursor=db.rawQuery("SELECT * FROM "+mapeo.tabla,null);
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
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion= new ArrayList<>();
        for (int i = 0; i< listaMetricas.size(); i++){
            listaInformacion.add(new String[]{
                    String.valueOf(listaMetricas.get(i).getIdMetrica()),
                    String.valueOf(listaMetricas.get(i).getCantidadOperadores()),
                    String.valueOf(listaMetricas.get(i).getCantidadOperandos()),
                    String.valueOf(listaMetricas.get(i).getOcurrenciasOperadores()),
                    String.valueOf(listaMetricas.get(i).getOcurrenciasOperandos()),
                    listaMetricas.get(i).getLongitud(),
                    listaMetricas.get(i).getVocabulario(),
                    listaMetricas.get(i).getVolumen(),
                    listaMetricas.get(i).getDificultad(),
                    listaMetricas.get(i).getNivel(),
                    listaMetricas.get(i).getEsfuerzo(),
                    listaMetricas.get(i).getTiempo(),
                    listaMetricas.get(i).getBugs()
            });
        }
    }
}
