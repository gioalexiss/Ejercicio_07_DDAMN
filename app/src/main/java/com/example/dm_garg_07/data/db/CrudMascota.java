package com.example.dm_garg_07.data.db;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.dm_garg_07.data.entities.Mascota;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class CrudMascota {
    //Manejar el CRUD
    //Instanciar la db
    //Usar hilos para realizar el CRUD
    AppDataBase db;
    //Son operaciones de lectira y escritura
    //puede bloquear la pantalla
    ExecutorService executorService =
            Executors.newSingleThreadExecutor();
    //Para regresar el resultado de las consultas al hilo principal (UI)
    Handler handler = new Handler(Looper.getMainLooper());

    public CrudMascota(Context context){
        db = AppDataBase.getInstance(context);
    }

    public void agregar(Mascota mascota){
        executorService.execute(()->{
            //Mandamos a segundo plano la operacion de IO (Input Output)
            db.mascotaDao().crear(mascota);
        });
    }

    public void actualizar(Mascota mascota){
        executorService.execute(()->{
            db.mascotaDao().actualizar(mascota);
        });
    }

    public void eliminar(Long id){
        executorService.execute(()->{
            db.mascotaDao().eliminar(id);
        });
    }

    //Las consultas regresan el resultado en un callback
    //que se ejecuta en el hilo principal
    public void obtenerTodas(Consumer<List<Mascota>> callback){
        executorService.execute(()->{
            List<Mascota> mascotas = db.mascotaDao().obtenerTodos();
            handler.post(()-> callback.accept(mascotas));
        });
    }

    public void obtenerPorId(Long id, Consumer<Mascota> callback){
        executorService.execute(()->{
            Mascota mascota = db.mascotaDao().obtenerPorId(id);
            handler.post(()-> callback.accept(mascota));
        });
    }
}
