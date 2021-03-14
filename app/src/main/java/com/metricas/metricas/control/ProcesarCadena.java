package com.metricas.metricas.control;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import com.metricas.metricas.BD.conexion;
import com.metricas.metricas.BD.mapeo;
import com.metricas.metricas.BD.metricas;
import com.metricas.metricas.analizarActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcesarCadena {
    private TextView resultados;
    public static String codigo;
    ArrayList<String> matchComentarios = new ArrayList<>();
    ArrayList<String> matchAllComentarios = new ArrayList<>();
    ArrayList<String> matchCadenas = new ArrayList<>();
    ArrayList<String> matchAllCadenas = new ArrayList<>();
    ArrayList<String> matchOperadoresAritmeticos = new ArrayList<>();
    ArrayList<String> matchAllOperadoresAritmeticos = new ArrayList<>();
    ArrayList<String> matchReservadas = new ArrayList<>();
    ArrayList<String> matchAllReservadas = new ArrayList<>();
    ArrayList<String> matchOperadoresLogicos = new ArrayList<>();
    ArrayList<String> matchAllOperadoresLogicos = new ArrayList<>();
    ArrayList<String> matchVariablesSinCortar = new ArrayList<>();
    ArrayList<String> matchAllVariablesSinCortar = new ArrayList<>();
    ArrayList<String> matchCortadas = new ArrayList<>();
    ArrayList<String> matchAllCortadas = new ArrayList<>();
    ArrayList<String> variablesCortadas = new ArrayList<>();

    conexion conexion;

    private final String [] bibliotecaPalabrasReservadas = {"package","public","private","default","protected","class","import","void",
            "int","return","double","Double","String","long","Int","Integer","new","equals",
            "args","main","static","final","extends","abstract","assert","boolean","break","byte","enum",
            "interface","char","const","goto","implements","this","throws","throw","synchronized","transient","true",
            "false","short","volatile","instanceof","native","super","switch","null","while\\(","for\\(","if\\(","else\\{","try\\{","do\\{"};

    public ProcesarCadena(TextView resultados) {
        this.resultados = resultados;
    }

    public void buscarComentarios(){
        final Pattern comentarios = Pattern.compile("(\\/\\*(\\s*|.*?)*\\*\\/)|(\\/\\/.*)");
        Matcher matcherComentarios = comentarios.matcher(codigo);
        if(matcherComentarios.find()){
            do{
                String grupoCapturado = matcherComentarios.group();
                matchAllComentarios.add(grupoCapturado);
                if(!matchComentarios.contains(grupoCapturado)){
                    matchComentarios.add(grupoCapturado);
                }
            }while(matcherComentarios.find());
        }
    }

    public void buscarCadenas(){
        final Pattern cadenasTexto = Pattern.compile("(\"[\\d\\w\\s\\.\\:\\+\\{\\[\\]%]+\")");
        Matcher matcherCadenasTexto = cadenasTexto.matcher(codigo);
        if(matcherCadenasTexto.find()){
            do{
                String grupoCapturado = matcherCadenasTexto.group();
                matchAllCadenas.add(grupoCapturado);
                if(!matchCadenas.contains(grupoCapturado)){
                    matchCadenas.add(grupoCapturado);
                }
            }while(matcherCadenasTexto.find());
        }
    }

    public void buscarAritmeticos(){
        //regex para encontrar coincidencias
        final Pattern operadoresAritmeticos = Pattern.compile("(\\+)|(-)|(\\*)|(/)|(%)|(=)");
        //preparando matcher
        Matcher matcherOperadoresAritmeticos = operadoresAritmeticos.matcher(codigo);
        //si hay match se ejecuta
        if(matcherOperadoresAritmeticos.find()){
            resultados.append("Aritmeticas encontradas:\n");
            //mientras siga encontrando matches
            do{
                //divide y captura en arraylist para contar repetidos
                String grupoCapturado = matcherOperadoresAritmeticos.group();
                matchAllOperadoresAritmeticos.add(grupoCapturado);
                //se guarda en un arraylist solo una vez
                if(!matchOperadoresAritmeticos.contains(grupoCapturado)){
                    matchOperadoresAritmeticos.add(grupoCapturado);
                }
            }while(matcherOperadoresAritmeticos.find());

            //almacenando frecuencias en un hash para después contarlas
            HashMap<String, Integer> mapaDeFrecuencias = new HashMap<>();
            for (String palabra : matchAllOperadoresAritmeticos) {
                if (mapaDeFrecuencias.containsKey(palabra)) {
                    mapaDeFrecuencias.put(palabra, mapaDeFrecuencias.get(palabra) + 1);
                } else {
                    mapaDeFrecuencias.put(palabra, 1);
                }
            }
            // Imprimir mapa en JText
            int contador = 0;
            for (HashMap.Entry<String, Integer> entry : mapaDeFrecuencias.entrySet()) {
                resultados.append("\t"+(contador+1)+"\t"+entry.getKey()+"\t"+ entry.getValue()+"\n");
                contador++;
            }

            resultados.append("cantidad de operandos (n1): "+matchOperadoresAritmeticos.size());
            resultados.append("\ncantidad total (N1): "+matchAllOperadoresAritmeticos.size());
        }
    }

    @SuppressLint("SetTextI18n")
    public void buscarReservadas(){
        resultados.append("\n\nPalabras reservadas encontradas:\n");
        for (String bibliotecaPalabrasReservada : bibliotecaPalabrasReservadas) {
            Pattern palabrasReservadas = Pattern.compile("(" + bibliotecaPalabrasReservada + ")");
            Matcher matcherReservadas = palabrasReservadas.matcher(codigo);
            if (matcherReservadas.find()) {
                do {
                    String grupoCapturado = matcherReservadas.group();
                    matchAllReservadas.add(grupoCapturado);
                    if (!matchReservadas.contains(grupoCapturado)) {
                        matchReservadas.add(grupoCapturado);
                    }
                } while (matcherReservadas.find());
            }
        }
        //almacenando frecuencias en un hash para después contarlas
        HashMap<String, Integer> mapaDeFrecuencias = new HashMap<>();
        for (String palabra : matchAllReservadas) {
            if (mapaDeFrecuencias.containsKey(palabra)) {
                mapaDeFrecuencias.put(palabra, mapaDeFrecuencias.get(palabra) + 1);
            } else {
                mapaDeFrecuencias.put(palabra, 1);
            }
        }
        // Imprimir mapa en JText
        int contador = 0;
        for (HashMap.Entry<String, Integer> entry : mapaDeFrecuencias.entrySet()) {
            resultados.append("\t"+(contador+1)+"\t"+entry.getKey()+"\t"+ entry.getValue()+"\n");
            contador++;
        }

        resultados.append("cantidad de operandos (n1): "+matchReservadas.size());
        resultados.append("\ncantidad total (N1): "+matchAllReservadas.size());
    }

    public void buscarLogicos(){
        final Pattern operadoresLogicos = Pattern.compile("(<)|(>)|(==)|(!=)|(>=)|(<=)|(&&)|(not)");
        Matcher matcherOperadoresLogicos = operadoresLogicos.matcher(codigo);
        if(matcherOperadoresLogicos.find()){
            resultados.append("\n\nLogicos encontrados:\n");
            do{
                String grupoCapturado = matcherOperadoresLogicos.group();
                matchAllOperadoresLogicos.add(grupoCapturado);
                if(!matchOperadoresLogicos.contains(grupoCapturado)){
                    matchOperadoresLogicos.add(grupoCapturado);
                }
            }while(matcherOperadoresLogicos.find());
            //almacenando frecuencias en un hash para después contarlas
            HashMap<String, Integer> mapaDeFrecuencias = new HashMap<>();
            for (String palabra : matchAllOperadoresLogicos) {
                if (mapaDeFrecuencias.containsKey(palabra)) {
                    mapaDeFrecuencias.put(palabra, mapaDeFrecuencias.get(palabra) + 1);
                } else {
                    mapaDeFrecuencias.put(palabra, 1);
                }
            }
            // Imprimir mapa en JText
            int contador = 0;
            for (HashMap.Entry<String, Integer> entry : mapaDeFrecuencias.entrySet()) {
                resultados.append("\t"+(contador+1)+"\t"+entry.getKey()+"\t"+ entry.getValue()+"\n");
                contador++;
            }

            resultados.append("cantidad de operandos (n1): "+matchOperadoresLogicos.size());
            resultados.append("\ncantidad total (N1): "+matchAllOperadoresLogicos.size());
        }
    }

    public void buscarVariables(){
        final Pattern variables = Pattern.compile("" +
                "(int+\\s\\w*[^\\s;*\\{\\(=+-/\\d\\[\\]\\)\"\\}\\.])|" +
                "(long+\\s\\w*[^\\s;*\\{\\(=+-/\\d\\[\\]\\)\"\\}\\.])|" +
                "(double+\\s\\w*[^\\s;*\\{\\(=+-/\\d\\[\\]\\)\"\\}\\.])|" +
                "(String+\\s\\w*[^\\s;*\\{\\(=+-/\\d\\[\\]\\)\"\\}\\.])|" +
                "(void+\\s\\w*[^\\s;*\\{\\(=+-/\\d\\[\\]\\)\"\\}\\.])|" +
                "(J.+\\s\\w*[^\\s;*\\{\\(=+-/\\d\\[\\]\\)\"\\}\\.])"+
                "(\\]+\\s\\w*[^\\s;*\\{\\(=+-/\\d\\[\\]\\)\"\\}\\.])");
        Matcher matcherVariables = variables.matcher(codigo);
        if(matcherVariables.find()){
            resultados.append("\n\nOperandos:\n");
            do{
                String grupoCapturado = matcherVariables.group();
                matchAllVariablesSinCortar.add(grupoCapturado);
                String [] variableSimple = grupoCapturado.split(" ");
                variablesCortadas.add(variableSimple[1]);
                if(!matchVariablesSinCortar.contains(grupoCapturado)){
                    matchVariablesSinCortar.add(grupoCapturado);
                }
            }while(matcherVariables.find());
            buscarVariablesCortadas();
        }
    }

    private void buscarVariablesCortadas(){
        for(String auxiliar : variablesCortadas){
            final Pattern cortadas = Pattern.compile("(" + auxiliar + ")");
            Matcher matcherCortadas = cortadas.matcher(codigo);
            if(matcherCortadas.find()){
                do{
                    String grupoCapturado = matcherCortadas.group();
                    matchAllCortadas.add(grupoCapturado);
                    if(!matchCortadas.contains(grupoCapturado)){
                        matchCortadas.add(grupoCapturado);
                    }
                }while(matcherCortadas.find());
            }
        }

        //almacenando frecuencias en un hash para después contarlas
        HashMap<String, Integer> mapaDeFrecuencias = new HashMap<>();
        for (String palabra : matchAllCortadas) {
            if (mapaDeFrecuencias.containsKey(palabra)) {
                mapaDeFrecuencias.put(palabra, mapaDeFrecuencias.get(palabra) + 1);
            } else {
                mapaDeFrecuencias.put(palabra, 1);
            }
        }
        // Imprimir mapa en JText
        int contador = 0;
        for (HashMap.Entry<String, Integer> entry : mapaDeFrecuencias.entrySet()) {
            resultados.append("\t"+(contador+1)+"\t"+entry.getKey()+"\t"+ entry.getValue()+"\n");
            contador++;
        }

            /*for (int i = 0; i < matchVariables.size(); i++) {
                salida.append("\t"+(i+1)+"\t"+variablesCortadas.get(i)+"\t\n");
            } */
        resultados.append("cantidad de operandos (n2): "+ matchVariablesSinCortar.size());
        resultados.append("\ncantidad total (N2): "+matchAllCortadas.size());
    }


    public void parametros(conexion conexion){
        double operadores = matchOperadoresAritmeticos.size() + matchOperadoresLogicos.size() + matchReservadas.size();
        double operandos = matchVariablesSinCortar.size();//matchAllVariablesSinCortar.size();
        double ocurrenciasOperadores = matchAllOperadoresAritmeticos.size() + matchAllOperadoresLogicos.size() + matchAllReservadas.size();
        double ocurrenciasOperandos = matchAllCortadas.size();
        Calcular calcular = new Calcular();

        calcular.setOcurrenciasOperadores(ocurrenciasOperadores);
        calcular.setOcurrenciasOperandos(ocurrenciasOperandos);
        calcular.setOperadores(operadores);
        calcular.setOperandos(operandos);

        int n1 = (int) operadores;
        int n2 = (int) operandos;
        int N1 = (int) ocurrenciasOperadores;
        int N2 = (int) ocurrenciasOperandos;
        String N = String.valueOf(calcular.longitud());
        String n = String.valueOf(calcular.vocabulario());
        String V = String.valueOf(calcular.volumen(calcular.longitud(),calcular.vocabulario()));
        String D = String.valueOf(calcular.dificultad());
        String L = String.valueOf(calcular.nivel(calcular.dificultad()));
        String E = String.valueOf(calcular.esfuerzo(calcular.dificultad(), calcular.volumen(calcular.longitud(), calcular.vocabulario())));
        String T = String.valueOf(calcular.tiempo(calcular.esfuerzo(calcular.dificultad(), calcular.volumen(calcular.longitud(), calcular.vocabulario()))));
        String B = String.valueOf(calcular.bugs(calcular.esfuerzo(calcular.dificultad(), calcular.volumen(calcular.longitud(), calcular.vocabulario()))));

        resultados.append("\n\nn1: "+n1+"\n");
        resultados.append("\nn2: "+n2+"\n");
        resultados.append("\nN1: "+N1+"\n");
        resultados.append("\nN2: "+N2);
        resultados.append("\n\nLongitud: "+N);
        resultados.append("\n\nvocabulario: "+n);
        resultados.append(("\n\nvolumen: "+V));
        resultados.append(("\n\ndificultad: "+D));
        resultados.append(("\n\nnivel: "+L));
        resultados.append(("\n\nesfuerzo: "+E));
        resultados.append(("\n\ntiempo: "+T));
        resultados.append(("\n\nbugs: "+B));

        service service = new service();
        service.crear(conexion,n1,n2,N1,N2,N,n,V,D,L,E,T,B);
    }

}
