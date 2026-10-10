package com.example.dm_garg_07.data.db;

import android.content.Context;

import androidx.room3.Database;
import androidx.room3.Room;
import androidx.room3.RoomDatabase;

import com.example.dm_garg_07.data.dao.MascotaDao;
import com.example.dm_garg_07.data.dao.VeterinarioDao;
import com.example.dm_garg_07.data.entities.Mascota;
import com.example.dm_garg_07.data.entities.Veterinario;

@Database(entities = {Mascota.class, Veterinario.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract MascotaDao mascotaDao();
    public abstract VeterinarioDao veterinarioDao();

    //Una sola instancia de la db para toda la app (Singleton)
    //asi CrudMascota y CrudVeterinario comparten la misma conexion
    private static volatile AppDataBase instance;

    public static AppDataBase getInstance(Context context){
        if (instance == null){
            synchronized (AppDataBase.class){
                if (instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class, "veterinaria").build();
                }
            }
        }
        return instance;
    }
}
