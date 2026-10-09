package com.example.dm_garg_07.data.db;

import android.content.Context;

import androidx.room3.Room;

import com.example.dm_garg_07.data.entities.Mascota;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CrudMascota {
    //Manejar el CRUD
    //Instanciar la db
    //Usar hilos para realizar el CRUD
    AppDataBase db;
    //Son operaciones de lectira y escritura
    //puede bloquear la pantalla
    ExecutorService executorService =
            Executors.newSingleThreadExecutor();

    public CrudMascota(Context context){
        db = Room.databaseBuilder(context,
                AppDataBase.class, "veterinaria").build();
    }
    public agregar(Mascota mascota){
        executorService.execute(()->{
            //Mandamos a segundo plano la operacion de IO (Input Output)
            db.mascotaDao().crear(mascota);
        });

    }

    //falta el de iliminar
    //falta el de actualizar
    //obtener todas
    //obtener por id
}
