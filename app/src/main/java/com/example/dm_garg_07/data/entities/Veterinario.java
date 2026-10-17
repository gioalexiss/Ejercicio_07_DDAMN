package com.example.dm_garg_07.data.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "veterinarios")
public class Veterinario {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String nombre;
    private int edad;
    private String cedula;

    public Veterinario() {
    }

    @Ignore
    public Veterinario(String nombre, int edad, String cedula) {
        this.nombre = nombre;
        this.edad = edad;
        this.cedula = cedula;
    }

    @Ignore
    public Veterinario(Long id, String nombre, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
}
