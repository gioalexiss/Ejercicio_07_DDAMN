package com.example.dm_garg_07.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.dm_garg_07.data.entities.Usuario;

@Dao
public interface UsuarioDao {

    @Insert
    long crear(Usuario usuario);

    @Query("SELECT * FROM usuarios WHERE email = :email LIMIT 1")
    Usuario obtenerPorEmail(String email);

    //Revisa email y contraseña
    @Query("SELECT * FROM usuarios WHERE email = :email AND password = :password LIMIT 1")
    Usuario login(String email, String password);
}
