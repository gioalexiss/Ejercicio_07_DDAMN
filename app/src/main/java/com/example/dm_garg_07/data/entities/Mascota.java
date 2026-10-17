package com.example.dm_garg_07.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

//Cada mascota pertenece a un usuario
@Entity(tableName = "mascota",
        foreignKeys = @ForeignKey(entity = Usuario.class,
                parentColumns = "id",
                childColumns = "usuario_id",
                onDelete = ForeignKey.CASCADE),
        indices = {@Index(value = {"usuario_id"})})
public class Mascota {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "nombre")
    private String nombre;

    @ColumnInfo(name = "raza")
    private String raza;

    @ColumnInfo(name = "edad")
    private Integer edad;

    @ColumnInfo(name = "usuario_id")
    private Long usuarioId;

    public Mascota() {
    }

    @Ignore
    public Mascota(Long id, String nombre, String raza, Integer edad, Long usuarioId) {
        this.id = id;
        this.nombre = nombre;
        this.raza = raza;
        this.edad = edad;
        this.usuarioId = usuarioId;
    }

    @Ignore
    public Mascota(String nombre, String raza, Integer edad, Long usuarioId) {
        this.nombre = nombre;
        this.raza = raza;
        this.edad = edad;
        this.usuarioId = usuarioId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    //Texto que se ve en la lista
    @Override
    public String toString() {
        return nombre + "  ·  " + raza + "  ·  " + edad + (edad != null && edad == 1 ? " año" : " años");
    }
}
