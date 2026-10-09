package com.example.dm_garg_07.data.dao;

import androidx.room3.Dao;
import androidx.room3.Insert;
import androidx.room3.Query;
import androidx.room3.Update;

import com.example.dm_garg_07.data.entities.Mascota;

import java.util.List;

@Dao
public interface MascotaDao {

    //Query = Obtener consulta
    @Query("SELECT * FROM mascota")
    List<Mascota> obtenerTodos();

    @Query("SELECT * FROM mascota WHERE id = :id")
    Mascota obtenerPorId(Long id);
    //si se requiere conocer el id generado - long
    // de lo contrario void

    @Insert
    void crear(Mascota mascota);

    @Update
    void actualizar(Mascota mascota);

    @Query("DELETE FROM mascota WHERE id = :id")
    void eliminar(Long id);
}

