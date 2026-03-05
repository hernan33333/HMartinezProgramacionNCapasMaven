/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alien07.HMartinezProgramacionNCapasMaven.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 *
 * @author Alien 7
 */
@Entity
public class Rol {
    
    @Id
    @Column(name = "idrol")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdRol;
    
    @Column(name = "nombre")
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
