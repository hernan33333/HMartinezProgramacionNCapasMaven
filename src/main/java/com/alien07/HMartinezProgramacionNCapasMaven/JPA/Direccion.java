/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alien07.HMartinezProgramacionNCapasMaven.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
public class Direccion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddireccion")
    private int IdDireccion;
    
    @Column(name = "calle")
    private String Calle;
    
    @Column(name = "numerointerior")
    private String NumeroInterior;
    
    @Column(name = "numeroexterior")
    private String NumeroExterior;
    
    @ManyToOne
    @JoinColumn(name = "idcolonia")
    public Colonia Colonia;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idusuario")
    public Usuario usuario;
    
    public void setIdDireccion(int IdDireccion){
        this.IdDireccion = IdDireccion;
    }
    
    public int getIdDireccion(){
        return IdDireccion;
    }
    
    public void setCalle(String Calle){
        this.Calle = Calle;
    }
    
    public String getCalle(){
        return Calle;
    }
    
    public void setNumeroInterior(String NumeroInterior){
        this.NumeroInterior = NumeroInterior;
    }
    
    public String getNumeroInterior(){
        return NumeroInterior;
    }
    
    public void setNumeroExterior(String NumeroExterior){
        this.NumeroExterior = NumeroExterior;
    }
    
    public String getNumeroExterior(){
        return NumeroExterior;
    }
    
    public Colonia getColonia(){
        return Colonia;
    }
    
    public void setColonia(Colonia Colonia){
        this.Colonia = Colonia;
    }
    
}
