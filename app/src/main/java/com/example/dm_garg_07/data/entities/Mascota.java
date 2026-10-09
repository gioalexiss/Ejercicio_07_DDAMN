package com.example.dm_garg_07.data.entities;

import androidx.room3.ColumnInfo;
import androidx.room3.Entity;
import androidx.room3.PrimaryKey;

@Entity(tableName = "mascota")
public class Mascota {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "nombre")
    private String nombre;

    @ColumnInfo(name = "especie")
    private String especie;

    @ColumnInfo(name = "raza")
    private String raza;

    @ColumnInfo(name = "edad")
    private Integer edad;

    public Mascota() {
    }

    public Mascota(Long id, String nombre, String especie, String raza, Integer edad) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
    }

    public Mascota(String nombre, String especie, String raza, Integer edad) {
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
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

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
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
}

