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
    AppDataBase db;
    ExecutorService executorService =
            Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());

    public CrudMascota(Context context){
        db = AppDataBase.getInstance(context);
    }

    public void agregar(Mascota mascota, Runnable onTerminado){
        executorService.execute(()->{
            db.mascotaDao().crear(mascota);
            notificar(onTerminado);
        });
    }

    public void actualizar(Mascota mascota, Runnable onTerminado){
        executorService.execute(()->{
            db.mascotaDao().actualizar(mascota);
            notificar(onTerminado);
        });
    }

    public void eliminar(Long id, Runnable onTerminado){
        executorService.execute(()->{
            db.mascotaDao().eliminar(id);
            notificar(onTerminado);
        });
    }

    public void obtenerTodas(Consumer<List<Mascota>> callback){
        executorService.execute(()->{
            List<Mascota> mascotas = db.mascotaDao().obtenerTodos();
            handler.post(()-> callback.accept(mascotas));
        });
    }

    public void obtenerPorUsuario(Long usuarioId, Consumer<List<Mascota>> callback){
        executorService.execute(()->{
            List<Mascota> mascotas = db.mascotaDao().obtenerPorUsuario(usuarioId);
            handler.post(()-> callback.accept(mascotas));
        });
    }

    public void obtenerPorId(Long id, Consumer<Mascota> callback){
        executorService.execute(()->{
            Mascota mascota = db.mascotaDao().obtenerPorId(id);
            handler.post(()-> callback.accept(mascota));
        });
    }

    //Revisa nombres repetidos
    public void existeDuplicado(Long usuarioId, String nombre, Long excluirId, Consumer<Boolean> callback){
        executorService.execute(()->{
            long excluir = excluirId == null ? -1L : excluirId;
            boolean existe = db.mascotaDao().contarDuplicados(usuarioId, nombre, excluir) > 0;
            handler.post(()-> callback.accept(existe));
        });
    }

    private void notificar(Runnable onTerminado){
        if (onTerminado != null) {
            handler.post(onTerminado);
        }
    }
}
