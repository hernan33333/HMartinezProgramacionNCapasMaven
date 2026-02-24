/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alien07.HMartinezProgramacionNCapasMaven.ML;

import com.alien07.HMartinezProgramacionNCapasMaven.ML.Colonia;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Alien 7
 */
public class Direccion {
    
    private int IdDireccion;
    
    @NotEmpty(message = "Debe de introducir una calle")
    @Pattern(regexp = "^[a-zA-Z\\d ]+$", message = "Sólo se admiten letras y números")
    @Size(min = 1, max = 100, message = "Más de 2 carácteres y menos de 100")
    private String Calle;
    
    @Pattern(regexp = "[a-zA-Z\\d]+", message = "Sólo se aceptan letras y números")
    @Size(min = 1, max = 20, message = "Más de 1 carácter y menos de 20")
    private String NumeroInterior;
    
    @Pattern(regexp = "[a-zA-Z\\d]+", message = "Sólo se aceptan letras y números")
    @NotEmpty(message = "Debe introducir un valor")
    @Size(min = 1, max = 20, message = "Más de 1 carácter y menos de 20")
    private String NumeroExterior;
    
    @Valid
    public Colonia Colonia;
    
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
