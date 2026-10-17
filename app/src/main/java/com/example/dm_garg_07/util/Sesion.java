package com.example.dm_garg_07.util;

import android.content.Context;
import android.content.SharedPreferences;

//Guarda el usuario que inició sesión
public class Sesion {
    private static final String PREFS = "sesion";
    private static final String KEY_ID = "usuario_id";
    private static final String KEY_NOMBRE = "usuario_nombre";

    private final SharedPreferences prefs;

    public Sesion(Context context){
        prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public void iniciar(Long usuarioId, String nombre){
        prefs.edit().putLong(KEY_ID, usuarioId).putString(KEY_NOMBRE, nombre).apply();
    }

    public void cerrar(){
        prefs.edit().clear().apply();
    }

    public long getUsuarioId(){
        return prefs.getLong(KEY_ID, -1L);
    }

    public String getNombre(){
        return prefs.getString(KEY_NOMBRE, "");
    }

    public boolean activa(){
        return getUsuarioId() != -1L;
    }
}
