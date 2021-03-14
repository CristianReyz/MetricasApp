package com.metricas.metricas.algoritmos;

public class Insercion {

    private void insercionIterativa(int vector[]){
        int posicion, auxiliar;
        for (int contador = 0; contador < vector.length; contador++) {
            posicion = contador;
            auxiliar = vector[contador];
            while ((posicion>0)&&(vector[posicion-1]>auxiliar)){
                vector[posicion] = vector[posicion - 1];
                posicion--;
            }
            vector[posicion] = auxiliar;
        }
    }

    public String insercionTime(int primerVector[]) {
        long startTimeIterativo = System.nanoTime();
        insercionIterativa(primerVector);
        long endTimeIterativo = System.nanoTime() - startTimeIterativo;
        return ("\n\n\n\nEl arreglo ordenado es: " +
                "\nEl metodo por Insercion tardo: " + endTimeIterativo + " nanosegundos");
    }
}
