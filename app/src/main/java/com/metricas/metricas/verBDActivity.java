package com.metricas.metricas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.metricas.metricas.BD.conexion;
import com.metricas.metricas.control.DAO;
import com.metricas.metricas.control.service;

public class verBDActivity extends AppCompatActivity {

    public static conexion conexion;

    ListView mostrarDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_b_d);

        conexion = new conexion(this,"metricas",null,1);
        mostrarDatos = (ListView) findViewById(R.id.datosCapturados);
        service service = new service();
        service.consultar(conexion);


        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1, DAO.listaInformacion);
        mostrarDatos.setAdapter(adaptador);
    }

}