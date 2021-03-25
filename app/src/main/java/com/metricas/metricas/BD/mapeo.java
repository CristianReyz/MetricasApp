package com.metricas.metricas.BD;

public class mapeo {

    public static final String tabla = "metricas";
    public static final String id = "idMetrica";
    public static final String ocurrenciasOperadores = "ocurrenciasOperadores";
    public static final String ocurrenciasOperandos = "ocurrenciasOperandos";
    public static final String cantidadOperadores = "cantidadOperadores";
    public static final String cantidadOperandos = "cantidadOperandos";
    public static final String longitud = "longitud";
    public static final String vocabulario = "vocabulario";
    public static final String volumen = "volumen";
    public static final String dificultad = "dificultad";
    public static final String nivel = "nivel";
    public static final String esfuerzo = "esfuerzo";
    public static final String tiempo = "tiempo";
    public static final String bugs = "bugs";

    public static final String crearTabla = "CREATE TABLE metricas (idMetrica INTEGER PRIMARY KEY AUTOINCREMENT, ocurrenciasOperadores INTEGER," +
            "ocurrenciasOperandos INTEGER, cantidadOperadores INTEGER, cantidadOperandos INTEGER," +
            "longitud TEXT, vocabulario TEXT, volumen TEXT, dificultad TEXT, nivel TEXT, esfuerzo TEXT," +
            "tiempo TEXT, bugs TEXT)";

}
