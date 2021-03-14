package com.metricas.metricas.control;

import com.metricas.metricas.BD.conexion;
import com.metricas.metricas.BD.metricas;

public class service {

    public void crear(conexion conexion, int operadores, int operandos, int ocurrenciasOperadores,
                      int ocurrenciasOperandos, String longitud, String vocabulario, String volumen,
                      String dificultad, String nivel, String esfuerzo,String tiempo, String bugs){
        DAO dao = new DAO();
        metricas metricas = new metricas();
        metricas.setCantidadOperadores(operadores);
        metricas.setCantidadOperandos(operandos);
        metricas.setOcurrenciasOperadores(ocurrenciasOperadores);
        metricas.setOcurrenciasOperandos(ocurrenciasOperandos);
        metricas.setLongitud(longitud);
        metricas.setVocabulario(vocabulario);
        metricas.setVolumen(volumen);
        metricas.setDificultad(dificultad);
        metricas.setNivel(nivel);
        metricas.setEsfuerzo(esfuerzo);
        metricas.setTiempo(tiempo);
        metricas.setBugs(bugs);
        dao.create(conexion,metricas);
    }

    public void consultar(conexion conexion){
        DAO dao = new DAO();
        dao.consultar(conexion);
    }
}
