package com.metricas.metricas.algoritmos;

public class Fibonacci {

    private int fibonacciIterativo(int numero){
        int menosDos;
        int menosUno = 0;
        int actual = 1;
        if (numero > 1) {
            for (int contador = 1; contador < numero ; contador++) {

                menosDos = menosUno;

                menosUno = actual;

                actual = menosDos + menosUno;
            }
            return actual;
        } else if (numero == 1) {
            return 1;
        } else if (numero == 0) {
            return 0;
        } else {
            System.out.println("Debes ingresar un tamaño mayor o igual a 1");
            return -1;
        }
    }

    public String fiboTime(int operador) {
        long startTimeOptimizado = System.nanoTime();
        int optimizado = fibonacciIterativo(operador);
        long endTimeOptimizado = System.nanoTime() - startTimeOptimizado;
        return ("\nEl fibonacci de "+operador+" f("+operador+") es: "+optimizado+"  " +
                    "\nEl metodo Optimizado tardo: "+endTimeOptimizado+" nanosegundos");
    }
}
