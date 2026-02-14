/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alien07.HMartinezProgramacionNCapasMaven.ML;

import com.alien07.HMartinezProgramacionNCapasMaven.ML.Pais;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 *
 * @author Alien 7
 */
public class Estado {
    
    private int IdEstado;
    @Pattern(regexp = "[a-zA-Z áéíóú]+", message = "El estado es incorrecto")
    @NotEmpty(message = "Debe de seleccionar un estado")
    private String Nombre;
    
    @Valid
    public Pais Pais;
    
    public Estado(){
    }
    
    public Estado(int IdEstado, String Nombre){
        this.IdEstado = IdEstado;
        this.Nombre = Nombre;
    }
    
    public void setIdEstado(int IdEstado){
        this.IdEstado = IdEstado;
    }
    
    public int getIdEstado(){
        return IdEstado;
    }
    
    public void setNombre(String Nombre){
        this.Nombre = Nombre;            
    }
    
    public String getNombre(){
        return Nombre;
    }
    
    public Pais getPais(){
        return Pais;
    }
    
    public void setPais(Pais Pais){
        this.Pais = Pais;
    }
    
}
