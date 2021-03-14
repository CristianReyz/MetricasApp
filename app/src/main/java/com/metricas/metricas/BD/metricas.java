package com.metricas.metricas.BD;

public class metricas {
    private int idMetrica;
    private int ocurrenciasOperadores;
    private int ocurrenciasOperandos;
    private int cantidadOperadores;
    private int cantidadOperandos;
    private String longitud;
    private String vocabulario;
    private String volumen;
    private String dificultad;
    private String nivel;
    private String esfuerzo;
    private String tiempo;
    private String bugs;

    public int getIdMetrica() {
        return idMetrica;
    }

    public void setIdMetrica(int idMetrica) {
        this.idMetrica = idMetrica;
    }

    public int getOcurrenciasOperadores() {
        return ocurrenciasOperadores;
    }

    public void setOcurrenciasOperadores(int ocurrenciasOperadores) {
        this.ocurrenciasOperadores = ocurrenciasOperadores;
    }

    public int getOcurrenciasOperandos() {
        return ocurrenciasOperandos;
    }

    public void setOcurrenciasOperandos(int ocurrenciasOperandos) {
        this.ocurrenciasOperandos = ocurrenciasOperandos;
    }

    public int getCantidadOperadores() {
        return cantidadOperadores;
    }

    public void setCantidadOperadores(int cantidadOperadores) {
        this.cantidadOperadores = cantidadOperadores;
    }

    public int getCantidadOperandos() {
        return cantidadOperandos;
    }

    public void setCantidadOperandos(int cantidadOperandos) {
        this.cantidadOperandos = cantidadOperandos;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getVocabulario() {
        return vocabulario;
    }

    public void setVocabulario(String vocabulario) {
        this.vocabulario = vocabulario;
    }

    public String getVolumen() {
        return volumen;
    }

    public void setVolumen(String volumen) {
        this.volumen = volumen;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getEsfuerzo() {
        return esfuerzo;
    }

    public void setEsfuerzo(String esfuerzo) {
        this.esfuerzo = esfuerzo;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getBugs() {
        return bugs;
    }

    public void setBugs(String bugs) {
        this.bugs = bugs;
    }

    /*
    	ocurrenciasOperadores INTEGER UNSIGNED NOT NULL,
	ocurrenciasOperandos INTEGER UNSIGNED NOT NULL,
	cantidadOperadores INTEGER UNSIGNED NOT NULL,
	cantidadOperandos INTEGER UNSIGNED NOT NULL,
	longitud VARCHAR(10) NOT NULL,
	vocabulario VARCHAR(10) NOT NULL,
	volumen VARCHAR(10) NOT NULL,
	dificultad VARCHAR(10) NOT NULL,
	nivel VARCHAR(10) NOT NULL,
	esfuerzo VARCHAR(10) NOT NULL,
	tiempo VARCHAR(10) NOT NULL,
	bugs VARCHAR(10) NOT NULL,
**/
}
