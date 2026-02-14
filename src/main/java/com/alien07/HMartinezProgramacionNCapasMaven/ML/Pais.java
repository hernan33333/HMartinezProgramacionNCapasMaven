/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alien07.HMartinezProgramacionNCapasMaven.ML;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 *
 * @author Alien 7
 */
public class Pais {
    
    private int IdPais;
    
    @Pattern(regexp = "[a-zA-Z áéíóú]+", message = "El pais es incorrecto")
    @NotEmpty(message = "Debe elegir un pais")
    private String Nombre;

    public Pais(){
    }
    
    public Pais(int IdPais, String Nombre) {
        this.IdPais = IdPais;
        this.Nombre = Nombre;
    }
    
    public void setIdPais(int IdPais){
        this.IdPais = IdPais;
    }
    
    public int getIdPais(){
        return IdPais;
    }
    
    public void setNombre(String Nombre){
        this.Nombre = Nombre;
    }
    
    public String getNombre(){
        return Nombre;
    }
}
