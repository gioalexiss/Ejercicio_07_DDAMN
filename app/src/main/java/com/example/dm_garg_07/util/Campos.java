package com.example.dm_garg_07.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewParent;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

//Muestra errores en los campos
public class Campos {

    public static void error(EditText campo, String mensaje){
        TextInputLayout layout = buscarLayout(campo);
        if (layout != null) {
            layout.setError(mensaje);
        } else {
            campo.setError(mensaje);
        }
        campo.requestFocus();
    }

    //Quita el error al escribir
    public static void limpiarAlEscribir(EditText... campos){
        for (EditText campo : campos) {
            campo.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    TextInputLayout layout = buscarLayout(campo);
                    if (layout != null) {
                        layout.setError(null);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }
    }

    private static TextInputLayout buscarLayout(View campo){
        ViewParent padre = campo.getParent();
        while (padre != null) {
            if (padre instanceof TextInputLayout) {
                return (TextInputLayout) padre;
            }
            padre = padre.getParent();
        }
        return null;
    }
}
