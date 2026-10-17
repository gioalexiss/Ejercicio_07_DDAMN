package com.example.dm_garg_07.util;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dm_garg_07.R;

public class Pantalla {

    //Evita que el teclado tape la pantalla
    public static void ajustarInsets(Activity activity){
        View main = activity.findViewById(R.id.main);
        ViewCompat.setOnApplyWindowInsetsListener(main, (v, insets) -> {
            Insets barras = insets.getInsets(WindowInsetsCompat.Type.systemBars()
                    | WindowInsetsCompat.Type.ime());
            v.setPadding(barras.left, barras.top, barras.right, barras.bottom);
            //Muestra el campo donde se escribe
            if (insets.isVisible(WindowInsetsCompat.Type.ime())) {
                View campo = activity.getCurrentFocus();
                if (campo != null) {
                    campo.post(() -> mostrarCampo(campo));
                }
            }
            return insets;
        });
    }

    private static void mostrarCampo(View campo){
        int margen = (int) (32 * campo.getResources().getDisplayMetrics().density);
        Rect area = new Rect(0, 0, campo.getWidth(), campo.getHeight() + margen);
        campo.requestRectangleOnScreen(area, false);
    }
}
