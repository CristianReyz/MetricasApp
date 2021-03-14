package com.metricas.metricas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.metricas.metricas.BD.conexion;

public class MainActivity extends AppCompatActivity {

    Button calcular, baseDeDatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conexion conexion = new conexion(this,"metricas",null,1);

        calcular= (Button) findViewById(R.id.botonCalcular);
        calcular.setOnClickListener(e->{
            Intent pasar = new Intent(MainActivity.this,analizarActivity.class);
            startActivity(pasar);
        });

        baseDeDatos = (Button) findViewById(R.id.botonBasesDeDatos);
        baseDeDatos.setOnClickListener(v ->{
            Intent pasar = new Intent(MainActivity.this,verBDActivity.class);
            startActivity(pasar);
        });
    }
}