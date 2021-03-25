package com.metricas.metricas;

import androidx.appcompat.app.AppCompatActivity;

import com.metricas.metricas.BD.conexion;
import com.metricas.metricas.control.DAO;
import com.metricas.metricas.control.TableDynamic;
import com.metricas.metricas.control.service;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TableLayout;

public class datosActivity extends AppCompatActivity {

    public static conexion conexion;
    TableLayout tabla;
    EditText nombre, apellido;
    String[] header={"  ID    ", "  n1  "," n2   ","    N1   ","    N2   ","Longitud    ","Vocabulario  ","Volumen  ",
            "Dificultad  ","Nivel    ","Esfuerzo    ","Tiempo   ","Bugs    "};
    TableDynamic tablaDinamica;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        conexion = new conexion(this, "metricas", null, 1);
        service service = new service();
        service.consultar(conexion);
        tabla=findViewById(R.id.mi_tabla_layout);
        tablaDinamica=new TableDynamic(tabla, getApplicationContext());
        tablaDinamica.addHeader(header);
        tablaDinamica.addData(DAO.listaInformacion);
        tablaDinamica.backgroundHeader(Color.BLACK);
        tablaDinamica.backgroundData(Color.DKGRAY, Color.BLUE);
        tablaDinamica.lineColor(Color.BLACK);
        tablaDinamica.textColorData(Color.WHITE);
        tablaDinamica.textColorHeader(Color.WHITE);

    }
}