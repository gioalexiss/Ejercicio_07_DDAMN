package com.example.dm_garg_07.data.db;

import androidx.room3.Database;
import androidx.room3.RoomDatabase;

import com.example.dm_garg_07.data.dao.MascotaDao;
import com.example.dm_garg_07.data.dao.VeterinarioDao;
import com.example.dm_garg_07.data.entities.Mascota;
import com.example.dm_garg_07.data.entities.Veterinario;

@Database(entities = {Mascota.class, Veterinario.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract MascotaDao mascotaDao();
    public abstract VeterinarioDao veterinarioDao();
}
