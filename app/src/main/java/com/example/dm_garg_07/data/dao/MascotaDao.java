package com.example.dm_garg_07.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dm_garg_07.data.entities.Mascota;

import java.util.List;

@Dao
public interface MascotaDao {

    @Query("SELECT * FROM mascota")
    List<Mascota> obtenerTodos();

    //Mascotas del usuario
    @Query("SELECT * FROM mascota WHERE usuario_id = :usuarioId ORDER BY nombre")
    List<Mascota> obtenerPorUsuario(Long usuarioId);

    @Query("SELECT * FROM mascota WHERE id = :id")
    Mascota obtenerPorId(Long id);

    //Revisa nombres repetidos
    @Query("SELECT COUNT(*) FROM mascota WHERE usuario_id = :usuarioId " +
            "AND LOWER(nombre) = LOWER(:nombre) AND id != :excluirId")
    int contarDuplicados(Long usuarioId, String nombre, Long excluirId);

    @Insert
    void crear(Mascota mascota);

    @Update
    void actualizar(Mascota mascota);

    @Query("DELETE FROM mascota WHERE id = :id")
    void eliminar(Long id);
}
