package com.example.dm_garg_07.data.dao;

import androidx.room3.Dao;
import androidx.room3.Delete;
import androidx.room3.Insert;
import androidx.room3.Query;
import androidx.room3.Update;

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
