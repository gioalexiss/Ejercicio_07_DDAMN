package com.example.dm_garg_07.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dm_garg_07.data.entities.Veterinario;

import java.util.List;

@Dao
public interface VeterinarioDao {
    @Query("SELECT * FROM veterinarios")
    List<Veterinario> obtenerTodos();
    @Query("SELECT * FROM veterinarios WHERE id = :id")
    Veterinario obtenerPorId(Long id);
    @Insert
    void crear(Veterinario veterinario);
    @Update
    void actualizar(Veterinario veterinario);
    @Delete
    void eliminar(Veterinario veterinario);

}
