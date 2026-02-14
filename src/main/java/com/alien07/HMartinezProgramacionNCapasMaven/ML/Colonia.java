/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alien07.HMartinezProgramacionNCapasMaven.ML;

import com.alien07.HMartinezProgramacionNCapasMaven.ML.Municipio;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Alien 7
 */
public class Colonia {
    
    private int IdColonia;
    
    @Pattern(regexp = "[a-zA-Z áéíóú]+", message = "La colonia es incorrecta")
    @NotEmpty(message = "Debe de elegir una colonia")
    @Size(min = 2, max = 100, message = "La colonia es incorrecta")
    private String Nombre;
    
    @Pattern(regexp = "^[\\d]{5}", message = "El código postal es incorrecto")
    @NotEmpty(message = "Debe de haber un código postal")
    private String CodigoPostal;
    
    @Valid
    public Municipio Municipio;
    
    public Colonia(){
    }
    
    public Colonia(int IdColonia, String Nombre, String CodigoPostal){
        this.IdColonia = IdColonia;
        this.Nombre = Nombre;
        this.CodigoPostal = CodigoPostal;
    }
    
    public void setIdColonia(int IdColonia){
        this.IdColonia = IdColonia;
    }
    
    public int getIdColonia(){
        return IdColonia;
    }
    
    public void setNombre(String Nombre){
        this.Nombre = Nombre;
    }
    
    public String getNombre(){
        return Nombre;
    }
    
    public void setCodigoPostal(String CodigoPostal){
        this.CodigoPostal = CodigoPostal;
    }
    
    public String getCodigoPostal(){
        return CodigoPostal;
    }
    
    public Municipio getMunicipio(){
        return Municipio;
    }
    
    public void setMunicipio(Municipio Municipio){
        this.Municipio = Municipio;
    }
}
