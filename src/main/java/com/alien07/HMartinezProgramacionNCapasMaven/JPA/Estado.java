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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 *
 * @author Alien 7
 */
@Entity
public class Estado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idestado")
    private int IdEstado;
    
    @Column(name = "nombre")
    private String Nombre;
    
    @ManyToOne
    @JoinColumn(name = "idpais")
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
