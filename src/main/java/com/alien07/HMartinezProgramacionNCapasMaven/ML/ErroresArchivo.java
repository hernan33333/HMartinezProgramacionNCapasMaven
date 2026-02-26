/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alien07.HMartinezProgramacionNCapasMaven.ML;

/**
 *
 * @author Alien 7
 */
public class ErroresArchivo {
    
    private int Fila;
    private String Dato;
    private String Descripcion;
    
    public int getFila(){
        return this.Fila;
    }
    
    public void setFila(int Fila){
        this.Fila = Fila;
    }
    
    public String getDato(){
        return this.Dato;
    }
    
    public void setDato(String Dato){
        this.Dato = Dato;
    }
    
    public String getDescripcion(){
        return this.Descripcion;
    }
    
    public void setDescripcion(String Descripcion){
        this.Descripcion = Descripcion;
    }
}
