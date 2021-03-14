package com.metricas.metricas.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class conexion extends SQLiteOpenHelper {

    public conexion(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(mapeo.crearTabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS metricas");
        onCreate(db);
    }

    /* 	ocurrenciasOperadores INTEGER UNSIGNED NOT NULL,
	ocurrenciasOperandos INTEGER UNSIGNED NOT NULL,
	cantidadOperadores INTEGER UNSIGNED NOT NULL,
	cantidadOperandos INTEGER UNSIGNED NOT NULL,
	longitud VARCHAR(10) NOT NULL,
	vocabulario VARCHAR(10) NOT NULL,
	volumen VARCHAR(10) NOT NULL,
	dificultad VARCHAR(10) NOT NULL,
	nivel VARCHAR(10) NOT NULL,
	esfuerzo VARCHAR(10) NOT NULL,
	tiempo VARCHAR(10) NOT NULL,
	bugs VARCHAR(10) NOT NULL,*/
}
