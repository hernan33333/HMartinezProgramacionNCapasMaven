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
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 *
 * @author Alien 7
 */
@Entity
public class Estado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idestado")
    @NotNull(message = "Debe de elegir un estado")
    @Positive(message = "Estado no válido")
    @Min(value = 1, message = "Debe de elegir un estado")
    private int IdEstado;
    
    @Column(name = "nombre")
    private String Nombre;
    
    @ManyToOne
    @JoinColumn(name = "idpais")
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
