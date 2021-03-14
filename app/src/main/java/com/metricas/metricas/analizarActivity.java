package com.metricas.metricas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.metricas.metricas.BD.conexion;
import com.metricas.metricas.control.*;

public class analizarActivity extends AppCompatActivity {

    Button fibonacci, almacenar,factorial,burbuja,insercion,quickSort,calcular;
    TextView areaTextoCodigoPrincipal, mostrarMetricas;
    public static conexion conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analizar);

        conexion = new conexion(this,"metricas",null,1);


        areaTextoCodigoPrincipal = findViewById(R.id.mostrarCodigo);
        mostrarMetricas = findViewById(R.id.mostrarMetricas);

        areaTextoCodigoPrincipal.setMovementMethod(new ScrollingMovementMethod());
        mostrarMetricas.setMovementMethod(new ScrollingMovementMethod());


        fibonacci= (Button) findViewById(R.id.botonFibonacci);
        fibonacci.setOnClickListener(v -> { cargarFibonacci();
            Toast.makeText(getApplicationContext(),"Fibonacci cargado",Toast.LENGTH_SHORT).show();
        });

        factorial= (Button) findViewById(R.id.botonFactorial);
        factorial.setOnClickListener(e->{
            cargarFactorial();
            Toast.makeText(getApplicationContext(),"Factorial cargado",Toast.LENGTH_SHORT).show();
        });

        burbuja= (Button) findViewById(R.id.botonBurbuja);
        burbuja.setOnClickListener(e->{
            cargarBurbuja();
            Toast.makeText(getApplicationContext(),"Burbuja cargado",Toast.LENGTH_SHORT).show();
        });

        insercion= (Button) findViewById(R.id.botonInsercion);
        insercion.setOnClickListener(e->{
            cargarInsercion();
            Toast.makeText(getApplicationContext(),"Insercion cargado",Toast.LENGTH_SHORT).show();
        });

        quickSort= (Button) findViewById(R.id.botonQuicksort);
        quickSort.setOnClickListener(e->{
            cargarQuickSort();
            Toast.makeText(getApplicationContext(),"Quicksort cargado",Toast.LENGTH_SHORT).show();
        });

        calcular= (Button) findViewById(R.id.botonCalcularCodigo);
        calcular.setOnClickListener(e->setCalcular(conexion));
    }

    private void setCalcular(conexion conexion){
        ProcesarCadena.codigo = areaTextoCodigoPrincipal.getText().toString();
        mostrarMetricas.setText(null);
        ProcesarCadena procesarCadena = new ProcesarCadena(mostrarMetricas);
        procesarCadena.buscarReservadas();
        procesarCadena.buscarLogicos();
        procesarCadena.buscarAritmeticos();
        procesarCadena.buscarVariables();
        procesarCadena.parametros(conexion);
        Toast.makeText(getApplicationContext(),"Registro: "+DAO.registro,Toast.LENGTH_SHORT).show();
    }
   // @SuppressLint("SetTextI18n")
    private void cargarFibonacci() {
        String fiboCode =
                "package com.metricas.metricas.algoritmos;\n" +
                        "\n" +
                        "public class Fibonacci {\n" +
                        "\n" +
                        "    private int fibonacciIterativo(int numero){\n" +
                        "        int menosDos;\n" +
                        "        int menosUno = 0;\n" +
                        "        int actual = 1;\n" +
                        "        if (numero > 1) {\n" +
                        "            for (int contador = 1; contador < numero ; contador++) {\n" +
                        "\n" +
                        "                menosDos = menosUno;\n" +
                        "\n" +
                        "                menosUno = actual;\n" +
                        "\n" +
                        "                actual = menosDos + menosUno;\n" +
                        "            }\n" +
                        "            return actual;\n" +
                        "        } else if (numero == 1) {\n" +
                        "            return 1;\n" +
                        "        } else if (numero == 0) {\n" +
                        "            return 0;\n" +
                        "        } else {\n" +
                        "            System.out.println(\"Debes ingresar un tamaño mayor o igual a 1\");\n" +
                        "            return -1;\n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        "    public String fiboTime(int operador) {\n" +
                        "        long startTimeOptimizado = System.nanoTime();\n" +
                        "        int optimizado = fibonacciIterativo(operador);\n" +
                        "        long endTimeOptimizado = System.nanoTime() - startTimeOptimizado;\n" +
                        "        return (\"\\nEl fibonacci de \"+operador+\" f(\"+operador+\") es: \"+optimizado+\"  \" +\n" +
                        "                    \"\\nEl metodo Optimizado tardo: \"+endTimeOptimizado+\" nanosegundos\");\n" +
                        "    }\n" +
                        "}\n";
        areaTextoCodigoPrincipal.setText(fiboCode);
    }

    private void cargarFactorial(){
        String codigoFactorial = "package com.metricas.metricas.algoritmos;\n" +
                "\n" +
                "public class Factorial {\n" +
                "\n" +
                "    public int factorialRecursivo(int numero){\n" +
                "        int fminus1;\n" +
                "        if(numero == 0){\n" +
                "            return 1;\n" +
                "        }\n" +
                "        fminus1 = factorialRecursivo(numero-1);\n" +
                "        return fminus1*numero;\n" +
                "    }\n" +
                "    public String factorialTime(int operando) {\n" +
                "        long startTimeRecursivo = System.nanoTime();\n" +
                "        int recursivo = factorialRecursivo(operando);\n" +
                "        long endTimeRecursivo = System.nanoTime() - startTimeRecursivo;\n" +
                "        return (\"\\n\\n\\n\\nEl factorial de \" + operando + \" f(\" + operando + \") es: \" + recursivo + \"  \" +\n" +
                "                \"\\nEl metodo Recursivo tardo: \" + endTimeRecursivo + \" nanosegundos\");\n" +
                "    }\n" +
                "}\n";
        areaTextoCodigoPrincipal.setText(codigoFactorial);
    }

    private void cargarBurbuja(){
        String burbujaCode = "package com.metricas.metricas.algoritmos;\n" +
                "\n" +
                "public class Burbuja {\n" +
                "    private int vector[] = {8,2,1,9,4,7};\n" +
                "    private void burbujaIterativa(){\n" +
                "        int auxiliar;\n" +
                "        for (int contador = 0; contador < (vector.length -1); contador++) {\n" +
                "            for (int contadorDos = 0; contadorDos < (vector.length - contador -1); contadorDos++) {\n" +
                "                if(vector[contadorDos] > vector[contadorDos+1]){\n" +
                "                    auxiliar = vector[contadorDos+1];\n" +
                "                    vector[contadorDos+1] = vector[contadorDos];\n" +
                "                    vector[contadorDos] = auxiliar;\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public String burbujaTime(){\n" +
                "            long startTimeIterativo = System.nanoTime();\n" +
                "            burbujaIterativa();\n" +
                "            long endTimeIterativo = System.nanoTime() - startTimeIterativo;\n" +
                "            return (\"\\nEl arreglo ordenado es: \"+\n" +
                "                    \"\\nEl metodo Burbuja tardo: \" + endTimeIterativo + \" nanosegundos\");\n" +
                "    }\n" +
                "}\n";
        areaTextoCodigoPrincipal.setText(burbujaCode);
    }

    private void cargarInsercion(){
        String insercionCode ="package com.metricas.metricas.algoritmos;\n" +
                "\n" +
                "public class Insercion {\n" +
                "\n" +
                "    private void insercionIterativa(int vector[]){\n" +
                "        int posicion, auxiliar;\n" +
                "        for (int contador = 0; contador < vector.length; contador++) {\n" +
                "            posicion = contador;\n" +
                "            auxiliar = vector[contador];\n" +
                "            while ((posicion>0)&&(vector[posicion-1]>auxiliar)){\n" +
                "                vector[posicion] = vector[posicion - 1];\n" +
                "                posicion--;\n" +
                "            }\n" +
                "            vector[posicion] = auxiliar;\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public String insercionTime(int primerVector[]) {\n" +
                "        long startTimeIterativo = System.nanoTime();\n" +
                "        insercionIterativa(primerVector);\n" +
                "        long endTimeIterativo = System.nanoTime() - startTimeIterativo;\n" +
                "        return (\"\\n\\n\\n\\nEl arreglo ordenado es: \" +\n" +
                "                \"\\nEl metodo por Insercion tardo: \" + endTimeIterativo + \" nanosegundos\");\n" +
                "    }\n" +
                "}\n";
        areaTextoCodigoPrincipal.setText(insercionCode);
    }

    private void cargarQuickSort(){
        String quickSortCode = "package com.metricas.metricas.algoritmos;\n" +
                "public class QuickSort {\n" +
                "    private void quickSortRecursivo(int vector[], int izquierda, int derecha){\n" +
                "        int contador = izquierda;\n" +
                "        int segundoContador = derecha;\n" +
                "        int temporal;\n" +
                "        int posicion = vector[(izquierda+derecha)/2];\n" +
                "        while (contador <= segundoContador){\n" +
                "            while (vector[contador] < posicion) contador++;\n" +
                "            while (vector[segundoContador] > posicion) segundoContador--;\n" +
                "            if(contador <= segundoContador){\n" +
                "                temporal = vector[contador];\n" +
                "                vector[contador] = vector[segundoContador];\n" +
                "                vector[segundoContador] = temporal;\n" +
                "                contador++; segundoContador--;\n" +
                "            }\n" +
                "        }\n" +
                "        if(izquierda < segundoContador) quickSortRecursivo(vector,izquierda,segundoContador);\n" +
                "        if(contador < derecha) quickSortRecursivo(vector,contador,derecha);\n" +
                "    }\n" +
                "\n" +
                "    public String quickTime(int original[]) {\n" +
                "        long startTimeRecursivo = System.nanoTime();\n" +
                "        quickSortRecursivo(original, 0, original.length - 1);\n" +
                "        long endTimeRecursivo = System.nanoTime() - startTimeRecursivo;\n" +
                "        return (\"\\nEl arreglo ordenado es: \" +\n" +
                "                \"\\nEl metodo QuickSort tardo: \" + endTimeRecursivo + \" nanosegundos\");\n" +
                "    }\n" +
                "\n" +
                "}\n";
        areaTextoCodigoPrincipal.setText(quickSortCode);
    }


    /*  <Button
        android:id="@+id/botonAlmacenar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="INSERTAR EN BD"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintHorizontal_bias="0.832"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.576" />*/
}