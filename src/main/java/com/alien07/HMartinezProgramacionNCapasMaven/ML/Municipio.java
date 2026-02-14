/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alien07.HMartinezProgramacionNCapasMaven.ML;

import com.alien07.HMartinezProgramacionNCapasMaven.ML.Estado;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 *
 * @author Alien 7
 */
public class Municipio {
    
    private int IdMunicipio;
    
    @Pattern(regexp = "[a-zA-Z áéíóú]+", message = "El municipio es incorrecto")
    @NotEmpty(message = "Debe de elegir un municipio")
    private String Nombre;
    
    @Valid
    public Estado Estado;
    
    public Municipio(){
    }
    
    public Municipio(int IdMunicipio, String Nombre){
        this.IdMunicipio = IdMunicipio;
        this.Nombre = Nombre;
    }
    
    public void setIdMunicipio(int IdMunicipio){
        this.IdMunicipio = IdMunicipio;
    }
    
    public int getIdMunicipio(){
        return IdMunicipio;
    }
    
    public void setNombre(String Nombre){
        this.Nombre = Nombre;
    }
    
    public String getNombre(){
        return Nombre;
    }
    
    public Estado getEstado(){
        return Estado;
    }
    
    public void setEstado(Estado Estado){
        this.Estado = Estado;
    }
    
}
