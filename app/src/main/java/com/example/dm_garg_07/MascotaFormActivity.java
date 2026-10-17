package com.example.dm_garg_07;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dm_garg_07.data.db.CrudMascota;
import com.example.dm_garg_07.data.entities.Mascota;
import com.example.dm_garg_07.util.Campos;
import com.example.dm_garg_07.util.Pantalla;
import com.example.dm_garg_07.util.Sesion;

//Pantalla para agregar o editar una mascota
public class MascotaFormActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "mascota_id";

    CrudMascota crudMascota;
    long usuarioId;
    Mascota mascotaEditar;
    EditText etNombre, etRaza, etEdad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mascota_form);
        //Evita que el teclado tape la pantalla
        Pantalla.ajustarInsets(this);
        crudMascota = new CrudMascota(this);
        Sesion sesion = new Sesion(this);
        usuarioId = sesion.getUsuarioId();
        ((TextView) findViewById(R.id.tvUsuario)).setText(sesion.getNombre());
        etNombre = findViewById(R.id.etNombre);
        etRaza = findViewById(R.id.etRaza);
        etEdad = findViewById(R.id.etEdad);
        Campos.limpiarAlEscribir(etNombre, etRaza, etEdad);
        TextView tvTitulo = findViewById(R.id.tvTitulo);
        Button btnGuardar = findViewById(R.id.btnGuardar);

        long id = getIntent().getLongExtra(EXTRA_ID, -1L);
        if (id != -1L) {
            tvTitulo.setText("Actualizar");
            btnGuardar.setText("Actualizar");
            crudMascota.obtenerPorId(id, pet -> {
                //Solo puede editar sus mascotas
                if (pet == null || !Long.valueOf(usuarioId).equals(pet.getUsuarioId())) {
                    Toast.makeText(this, "Mascota no encontrada", Toast.LENGTH_SHORT).show();
                    finish();
                    return;
                }
                mascotaEditar = pet;
                etNombre.setText(pet.getNombre());
                etRaza.setText(pet.getRaza());
                etEdad.setText(String.valueOf(pet.getEdad()));
            });
        }

        btnGuardar.setOnClickListener(v -> guardar());
        //Volver a la lista
        findViewById(R.id.btnRegresar).setOnClickListener(v -> finish());
    }

    private void guardar() {
        String nombre = etNombre.getText().toString().trim();
        String raza = etRaza.getText().toString().trim();
        String edadTexto = etEdad.getText().toString().trim();

        if (nombre.isEmpty()) {
            Campos.error(etNombre, "El nombre es obligatorio");
            return;
        }
        if (raza.isEmpty()) {
            Campos.error(etRaza, "La raza es obligatoria");
            return;
        }
        int edad;
        try {
            edad = Integer.parseInt(edadTexto);
        } catch (NumberFormatException e) {
            Campos.error(etEdad, "Edad no válida");
            return;
        }
        if (edad < 0 || edad > 100) {
            Campos.error(etEdad, "Edad no válida");
            return;
        }

        Long excluirId = mascotaEditar == null ? null : mascotaEditar.getId();
        //Evita nombres repetidos
        crudMascota.existeDuplicado(usuarioId, nombre, excluirId, existe -> {
            if (existe) {
                Campos.error(etNombre, "Ya tienes una mascota con ese nombre");
                return;
            }
            if (mascotaEditar == null) {
                Mascota nueva = new Mascota(nombre, raza, edad, usuarioId);
                crudMascota.agregar(nueva, () -> {
                    Toast.makeText(this, "Mascota agregada", Toast.LENGTH_SHORT).show();
                    finish();
                });
            } else {
                mascotaEditar.setNombre(nombre);
                mascotaEditar.setRaza(raza);
                mascotaEditar.setEdad(edad);
                crudMascota.actualizar(mascotaEditar, () -> {
                    Toast.makeText(this, "Mascota actualizada", Toast.LENGTH_SHORT).show();
                    finish();
                });
            }
        });
    }
}
