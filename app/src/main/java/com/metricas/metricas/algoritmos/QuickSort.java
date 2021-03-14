package com.metricas.metricas.algoritmos;
public class QuickSort {
    private void quickSortRecursivo(int vector[], int izquierda, int derecha){
        int contador = izquierda;
        int segundoContador = derecha;
        int temporal;
        int posicion = vector[(izquierda+derecha)/2];
        while (contador <= segundoContador){
            while (vector[contador] < posicion) contador++;
            while (vector[segundoContador] > posicion) segundoContador--;
            if(contador <= segundoContador){
                temporal = vector[contador];
                vector[contador] = vector[segundoContador];
                vector[segundoContador] = temporal;
                contador++; segundoContador--;
            }
        }
        if(izquierda < segundoContador) quickSortRecursivo(vector,izquierda,segundoContador);
        if(contador < derecha) quickSortRecursivo(vector,contador,derecha);
    }

    public String quickTime(int original[]) {
        long startTimeRecursivo = System.nanoTime();
        quickSortRecursivo(original, 0, original.length - 1);
        long endTimeRecursivo = System.nanoTime() - startTimeRecursivo;
        return ("\nEl arreglo ordenado es: " +
                "\nEl metodo QuickSort tardo: " + endTimeRecursivo + " nanosegundos");
    }

}
