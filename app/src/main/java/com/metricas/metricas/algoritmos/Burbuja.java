package com.metricas.metricas.algoritmos;

public class Burbuja {
    private int vector[] = {8,2,1,9,4,7};
    private void burbujaIterativa(){
        int auxiliar;
        for (int contador = 0; contador < (vector.length -1); contador++) {
            for (int contadorDos = 0; contadorDos < (vector.length - contador -1); contadorDos++) {
                if(vector[contadorDos] > vector[contadorDos+1]){
                    auxiliar = vector[contadorDos+1];
                    vector[contadorDos+1] = vector[contadorDos];
                    vector[contadorDos] = auxiliar;
                }
            }
        }
    }

    public String burbujaTime(){
            long startTimeIterativo = System.nanoTime();
            burbujaIterativa();
            long endTimeIterativo = System.nanoTime() - startTimeIterativo;
            return ("\nEl arreglo ordenado es: "+
                    "\nEl metodo Burbuja tardo: " + endTimeIterativo + " nanosegundos");
    }
}
