package com.example.dm_garg_07;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dm_garg_07.data.db.CrudUsuario;
import com.example.dm_garg_07.util.Campos;
import com.example.dm_garg_07.util.Pantalla;
import com.example.dm_garg_07.util.Sesion;

public class RegistroActivity extends AppCompatActivity {

    CrudUsuario crudUsuario;
    EditText etEmail, etPassword, etNombre;
    CheckBox cbTerminos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);
        //Evita que el teclado tape la pantalla
        Pantalla.ajustarInsets(this);
        crudUsuario = new CrudUsuario(this);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etNombre = findViewById(R.id.etNombre);
        cbTerminos = findViewById(R.id.cbTerminos);
        Button btnRegistrar = findViewById(R.id.btnRegistrar);
        TextView tvLogin = findViewById(R.id.tvLogin);
        Campos.limpiarAlEscribir(etEmail, etPassword, etNombre);

        btnRegistrar.setOnClickListener(v -> registrar());
        //Volver al login
        tvLogin.setOnClickListener(v -> finish());
    }

    private void registrar() {
        //Email en minúsculas
        String email = etEmail.getText().toString().trim().toLowerCase();
        String password = etPassword.getText().toString();
        String nombre = etNombre.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Campos.error(etEmail, "Ingresa un email válido");
            return;
        }
        if (password.length() < 4) {
            Campos.error(etPassword, "Mínimo 4 caracteres");
            return;
        }
        if (nombre.isEmpty()) {
            Campos.error(etNombre, "El nombre es obligatorio");
            return;
        }
        if (!cbTerminos.isChecked()) {
            Toast.makeText(this, "Debes aceptar los términos y la política de privacidad", Toast.LENGTH_SHORT).show();
            return;
        }
        //Revisa que el email no exista
        crudUsuario.registrar(nombre, email, password, id -> {
            if (id == null) {
                Campos.error(etEmail, "Ese email ya está registrado");
                return;
            }
            //Entra directo a sus mascotas
            new Sesion(this).iniciar(id, nombre);
            Toast.makeText(this, "Bienvenido, " + nombre, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}
