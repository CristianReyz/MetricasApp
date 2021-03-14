package com.metricas.metricas.control;

public class Calcular {
    private double ocurrenciasOperadores;
    private double ocurrenciasOperandos;
    private double operadores;
    private double operandos;

    public double longitud(){
        return (getOcurrenciasOperadores()+getOcurrenciasOperandos());
    }

    public double vocabulario(){
        return (getOperadores()+getOperandos());
    }

    //double roundDbl = Math.round(d*100.0)/100.0;
    public double volumen(double longitud, double vocabulario){
        double volumen =(longitud*(log(vocabulario)));
        return Math.round(volumen*100.0)/100.0;
    }

    public double dificultad(){
        double dificultad = ((getOperadores()/2)*(getOcurrenciasOperandos()/getOperandos()));
        return Math.round(dificultad*100)/100.0;
    }

    public double nivel(double dificultad){
        double nivel = (1/dificultad);
        return Math.round(nivel*100.0)/100.0;
    }

    public double esfuerzo(double dificultad, double volumen){
        double esfuerzo = dificultad*volumen;
        return Math.round(esfuerzo*100.0)/100.0;
    }

    public double tiempo(double esfuerzo){
        double tiempo = esfuerzo/18;
        return Math.round(tiempo*100.0)/100.0;
    }

    public double bugs(double esfuerzo){
        double bugs = Math.pow(esfuerzo,0.66)/3000;
        return Math.round(bugs * 100.0)/100.0;
    }

    private static Double log(double num) {
        return Math.log10(num)/Math.log10(2);
    }

    /*  print("\ta) Longitud del programa: N = N1+N2 = %d+%d =%d" % (N1, N2, N))
        print("\tb) Vocabulario: n = n1+n2 = %d+%d =%d" % (len(o1), len(o2), n))
        print("\tc) Volumen: V = N*log2(n) = %d*log2(%d)=%f" % (N, len(o1) + len(o2), V))
        print("\td) Dificultad: D = (n1/2)*(N2/n2) = (%d/2)*(%d/%d) = %f" % (len(o1), N2, len(o2), D))
        print("\te) Nivel: L = 1/D = 1/%f = %f" % (D, L))
        print("\tf) Esfuerzo: E = D*V = (%f)*(%f) = %f" % (D, V, E))
        print("\tg) Tiempo: T = E/18 = %f/18 = %f" % (E, T))
        print("\th) Bugs: B = (E^2/3)/3000 = (%f^2/3)/3000=%f" % (E, B))  */

    public double getOcurrenciasOperadores() {
        return ocurrenciasOperadores;
    }

    public void setOcurrenciasOperadores(double ocurrenciasOperadores) {
        this.ocurrenciasOperadores = ocurrenciasOperadores;
    }

    public double getOcurrenciasOperandos() {
        return ocurrenciasOperandos;
    }

    public void setOcurrenciasOperandos(double ocurrenciasOperandos) {
        this.ocurrenciasOperandos = ocurrenciasOperandos;
    }

    public double getOperadores() {
        return operadores;
    }

    public void setOperadores(double operadores) {
        this.operadores = operadores;
    }

    public double getOperandos() {
        return operandos;
    }

    public void setOperandos(double operandos) {
        this.operandos = operandos;
    }
}
