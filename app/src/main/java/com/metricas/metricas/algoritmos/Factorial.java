package com.metricas.metricas.algoritmos;

public class Factorial {

    public int factorialRecursivo(int numero){
        int fminus1;
        if(numero == 0){
            return 1;
        }
        fminus1 = factorialRecursivo(numero-1);
        return fminus1*numero;
    }
    public String factorialTime(int operando) {
        long startTimeRecursivo = System.nanoTime();
        int recursivo = factorialRecursivo(operando);
        long endTimeRecursivo = System.nanoTime() - startTimeRecursivo;
        return ("\n\n\n\nEl factorial de " + operando + " f(" + operando + ") es: " + recursivo + "  " +
                "\nEl metodo Recursivo tardo: " + endTimeRecursivo + " nanosegundos");
    }
}
