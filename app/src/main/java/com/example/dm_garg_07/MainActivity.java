package com.example.dm_garg_07;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dm_garg_07.data.db.CrudMascota;
import com.example.dm_garg_07.data.entities.Mascota;
import com.example.dm_garg_07.util.Pantalla;
import com.example.dm_garg_07.util.Sesion;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    CrudMascota crudMascota;
    Sesion sesion;
    //Todas las mascotas y las que se muestran
    List<Mascota> todas = new ArrayList<>();
    List<Mascota> mascotas = new ArrayList<>();
    ArrayAdapter<Mascota> adapter;
    TextView tvVacio;
    EditText etBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sesion = new Sesion(this);
        //Si no hay sesión, regresa al login
        if (!sesion.activa()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //Evita que el teclado tape la pantalla
        Pantalla.ajustarInsets(this);
        crudMascota = new CrudMascota(this);

        TextView tvBienvenida = findViewById(R.id.tvBienvenida);
        tvBienvenida.setText(sesion.getNombre());
        tvVacio = findViewById(R.id.tvVacio);
        ListView lvMascotas = findViewById(R.id.lvMascotas);
        etBuscar = findViewById(R.id.etBuscar);
        Button btnAgregar = findViewById(R.id.btnAgregar);
        Button btnCerrarSesion = findViewById(R.id.btnCerrarSesion);

        adapter = new ArrayAdapter<>(this, R.layout.item_mascota, mascotas);
        lvMascotas.setAdapter(adapter);

        //Buscador
        etBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filtrar();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        //Agregar mascota
        btnAgregar.setOnClickListener(v ->
                startActivity(new Intent(this, MascotaFormActivity.class)));

        //Editar mascota
        lvMascotas.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, MascotaFormActivity.class);
            intent.putExtra(MascotaFormActivity.EXTRA_ID, mascotas.get(position).getId());
            startActivity(intent);
        });

        //Eliminar mascota
        lvMascotas.setOnItemLongClickListener((parent, view, position, id) -> {
            Mascota pet = mascotas.get(position);
            new AlertDialog.Builder(this)
                    .setTitle("Eliminar mascota")
                    .setMessage("¿Seguro que deseas eliminar a " + pet.getNombre() + "?")
                    .setPositiveButton("Eliminar", (dialog, which) ->
                            crudMascota.eliminar(pet.getId(), () -> {
                                Toast.makeText(this, "Mascota eliminada", Toast.LENGTH_SHORT).show();
                                cargarMascotas();
                            }))
                    .setNegativeButton("Cancelar", null)
                    .show();
            return true;
        });

        btnCerrarSesion.setOnClickListener(v -> {
            sesion.cerrar();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }

    //Actualiza la lista al regresar
    @Override
    protected void onResume() {
        super.onResume();
        if (crudMascota != null) {
            cargarMascotas();
        }
    }

    private void cargarMascotas() {
        crudMascota.obtenerPorUsuario(sesion.getUsuarioId(), lista -> {
            todas.clear();
            todas.addAll(lista);
            filtrar();
        });
    }

    private void filtrar() {
        String texto = etBuscar.getText().toString().trim().toLowerCase(Locale.ROOT);
        mascotas.clear();
        for (Mascota pet : todas) {
            if (pet.getNombre().toLowerCase(Locale.ROOT).contains(texto)) {
                mascotas.add(pet);
            }
        }
        adapter.notifyDataSetChanged();
        if (mascotas.isEmpty()) {
            tvVacio.setText(todas.isEmpty()
                    ? "Aún no tienes mascotas registradas"
                    : "Ninguna mascota coincide con la búsqueda");
            tvVacio.setVisibility(View.VISIBLE);
        } else {
            tvVacio.setVisibility(View.GONE);
        }
    }
}
