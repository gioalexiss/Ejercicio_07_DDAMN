package com.example.dm_garg_07;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dm_garg_07.data.db.CrudUsuario;
import com.example.dm_garg_07.util.Campos;
import com.example.dm_garg_07.util.Pantalla;
import com.example.dm_garg_07.util.Sesion;

public class LoginActivity extends AppCompatActivity {

    CrudUsuario crudUsuario;
    Sesion sesion;
    EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sesion = new Sesion(this);
        //Si ya hay sesión, entra directo
        if (sesion.activa()) {
            irAMain();
            return;
        }
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        //Evita que el teclado tape la pantalla
        Pantalla.ajustarInsets(this);
        crudUsuario = new CrudUsuario(this);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegistro = findViewById(R.id.btnRegistro);
        Campos.limpiarAlEscribir(etEmail, etPassword);

        btnLogin.setOnClickListener(v -> login());
        btnRegistro.setOnClickListener(v ->
                startActivity(new Intent(this, RegistroActivity.class)));
    }

    private void login() {
        String email = etEmail.getText().toString().trim().toLowerCase();
        String password = etPassword.getText().toString();
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Campos.error(etEmail, "Ingresa un email válido");
            return;
        }
        if (password.isEmpty()) {
            Campos.error(etPassword, "Ingresa tu contraseña");
            return;
        }
        //Revisa los datos del usuario
        crudUsuario.login(email, password, u -> {
            if (u == null) {
                Toast.makeText(this, "Email o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                return;
            }
            sesion.iniciar(u.getId(), u.getNombre());
            irAMain();
        });
    }

    private void irAMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
