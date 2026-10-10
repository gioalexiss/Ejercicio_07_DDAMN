package com.example.dm_garg_07.data.db;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.dm_garg_07.data.entities.Veterinario;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class CrudVeterinario {
    //Manejar el CRUD
    //Instanciar la db
    //Usar hilos para realizar el CRUD
    AppDataBase db;
    ExecutorService executorService =
            Executors.newSingleThreadExecutor();
    //Para regresar el resultado de las consultas al hilo principal (UI)
    Handler handler = new Handler(Looper.getMainLooper());

    public CrudVeterinario(Context context){
        db = AppDataBase.getInstance(context);
    }

    public void agregar(Veterinario veterinario){
        executorService.execute(()->{
            db.veterinarioDao().crear(veterinario);
        });
    }

    public void actualizar(Veterinario veterinario){
        executorService.execute(()->{
            db.veterinarioDao().actualizar(veterinario);
        });
    }

    public void eliminar(Veterinario veterinario){
        executorService.execute(()->{
            db.veterinarioDao().eliminar(veterinario);
        });
    }

    public void obtenerTodos(Consumer<List<Veterinario>> callback){
        executorService.execute(()->{
            List<Veterinario> veterinarios = db.veterinarioDao().obtenerTodos();
            handler.post(()-> callback.accept(veterinarios));
        });
    }

    public void obtenerPorId(Long id, Consumer<Veterinario> callback){
        executorService.execute(()->{
            Veterinario veterinario = db.veterinarioDao().obtenerPorId(id);
            handler.post(()-> callback.accept(veterinario));
        });
    }
}
