package com.example.dm_garg_07.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.dm_garg_07.data.dao.MascotaDao;
import com.example.dm_garg_07.data.dao.UsuarioDao;
import com.example.dm_garg_07.data.dao.VeterinarioDao;
import com.example.dm_garg_07.data.entities.Mascota;
import com.example.dm_garg_07.data.entities.Usuario;
import com.example.dm_garg_07.data.entities.Veterinario;

@Database(entities = {Mascota.class, Veterinario.class, Usuario.class}, version = 3, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public abstract MascotaDao mascotaDao();
    public abstract VeterinarioDao veterinarioDao();
    public abstract UsuarioDao usuarioDao();

    //Una sola base de datos para toda la app
    private static volatile AppDataBase instance;

    public static AppDataBase getInstance(Context context){
        if (instance == null){
            synchronized (AppDataBase.class){
                if (instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class, "veterinaria")
                            .fallbackToDestructiveMigration(true)
                            .build();
                }
            }
        }
        return instance;
    }
}
