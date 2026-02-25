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
    
    private int fila;
    private String dato;
    private String descripcion;
    
    public int getFila(){
        return this.fila;
    }
    
    public void setFila(int fila){
        this.fila = fila;
    }
    
    public String getDato(){
        return this.dato;
    }
    
    public void setDato(String dato){
        this.dato = dato;
    }
    
    public String getDescripcion(){
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
}
