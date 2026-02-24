/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alien07.HMartinezProgramacionNCapasMaven.ML;

import com.alien07.HMartinezProgramacionNCapasMaven.ML.Estado;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 *
 * @author Alien 7
 */
public class Municipio {
    
    @NotNull(message = "Debe de elegir un municipio")
    @Positive(message = "Municipio no válido")
    @Min(value = 1, message = "Debe de elegir un municipio")
    private int IdMunicipio;
    
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
