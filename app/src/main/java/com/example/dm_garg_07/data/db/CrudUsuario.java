package com.example.dm_garg_07.data.db;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.dm_garg_07.data.entities.Usuario;
import com.example.dm_garg_07.util.Seguridad;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class CrudUsuario {
    AppDataBase db;
    ExecutorService executorService =
            Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());

    public CrudUsuario(Context context){
        db = AppDataBase.getInstance(context);
    }

    //Registra si el email no existe
    public void registrar(String nombre, String email, String password, Consumer<Long> callback){
        executorService.execute(()->{
            Long id = null;
            if (db.usuarioDao().obtenerPorEmail(email) == null) {
                id = db.usuarioDao().crear(new Usuario(nombre, email, Seguridad.hash(password)));
            }
            Long resultado = id;
            handler.post(()-> callback.accept(resultado));
        });
    }

    //Revisa los datos del usuario
    public void login(String email, String password, Consumer<Usuario> callback){
        executorService.execute(()->{
            Usuario u = db.usuarioDao().login(email, Seguridad.hash(password));
            handler.post(()-> callback.accept(u));
        });
    }
}
