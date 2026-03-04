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
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

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
    @NotEmpty(message = "Debe de introducir una calle")
    @Pattern(regexp = "^[a-zA-Z\\d ]+$", message = "Sólo se admiten letras y números")
    @Size(min = 1, max = 100, message = "Más de 2 carácteres y menos de 100")
    private String Calle;
    
    @Column(name = "numerointerior")
    @Pattern(regexp = "[a-zA-Z\\d]+", message = "Sólo se aceptan letras y números")
    @Size(min = 1, max = 20, message = "Más de 1 carácter y menos de 20")
    private String NumeroInterior;
    
    @Column(name = "numeroexterior")
    @Pattern(regexp = "[a-zA-Z\\d]+", message = "Sólo se aceptan letras y números")
    @NotEmpty(message = "Debe introducir un valor")
    @Size(min = 1, max = 20, message = "Más de 1 carácter y menos de 20")
    private String NumeroExterior;
    
    @ManyToOne
    @JoinColumn(name = "idcolonia")
    @Valid
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
