package com.example.dm_garg_07.data.entities;

import androidx.room3.Entity;
import androidx.room3.PrimaryKey;

@Entity(tableName = "veterinarios")
public class Veterinario {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String nombre;
    private int edad;
    private String cedula;

    public Veterinario() {
    }

    public Veterinario(String nombre, int edad, String cedula) {
        this.nombre = nombre;
        this.edad = edad;
        this.cedula = cedula;
    }

    public Veterinario(Long id, String nombre, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
    }
    //getters and setters
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
