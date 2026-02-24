/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alien07.HMartinezProgramacionNCapasMaven.ML;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 *
 * @author Alien 7
 */
public class Pais {
    
    @NotNull(message = "Debe de elegir un pais")
    @Positive(message = "Pais no válido")
    @Min(value = 1, message = "Debe de elegir un pais")
    private int IdPais;
    
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
