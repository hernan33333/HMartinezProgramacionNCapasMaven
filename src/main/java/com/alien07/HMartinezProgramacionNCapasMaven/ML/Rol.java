/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alien07.HMartinezProgramacionNCapasMaven.ML;

import jakarta.validation.constraints.NotNull;

/**
 *
 * @author Alien 7
 */
public class Rol {
    
    @NotNull(message = "Debe de elegir un rol")
    private int IdRol;
    
    private String Nombre;
    
    public Rol(){
    }
    
    public Rol(int IdRol, String Nombre){
        this.IdRol = IdRol;
        this.Nombre = Nombre;
    }
    
    public void setIdRol(int IdRol){
        this.IdRol = IdRol;
    }
    
    public int getIdRol(){
        return IdRol;
    }
    
    public void setNombre(String Nombre){
        this.Nombre = Nombre;
    }
    
    public String getNombre(){
        return Nombre;
    }
    
}
