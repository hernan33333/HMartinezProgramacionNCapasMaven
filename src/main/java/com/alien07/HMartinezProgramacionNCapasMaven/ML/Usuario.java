/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alien07.HMartinezProgramacionNCapasMaven.ML;

import com.alien07.HMartinezProgramacionNCapasMaven.ML.Rol;
import com.alien07.HMartinezProgramacionNCapasMaven.ML.Direccion;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Alien 7
 */
public class Usuario {
    
    private int IdUsuario;
    private String Nombre;
    private String ApellidoPaterno;
    private String ApellidoMaterno;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date FechaNacimiento;
    private String UserName;
    private String Email;
    private String Password;
    private String Sexo;
    private String Telefono;
    public Rol Rol;
    public List<Direccion> Direcciones;
    
    public void setIdUsuario(int IdUsuario){
        this.IdUsuario = IdUsuario;
    }
    
    public void setNombre(String Nombre){
        this.Nombre = Nombre;
    }
    
    public void setApellidoPaterno(String ApellidoPaterno){
        this.ApellidoPaterno = ApellidoPaterno;
    }
    
    public void setFechaNacimiento(Date FechaNacimiento){
        this.FechaNacimiento = FechaNacimiento;
    }
    
    public void setApellidoMaterno(String ApellidoMaterno){
        this.ApellidoMaterno = ApellidoMaterno;
    }
    
    public void setUserName(String UserName){
        this.UserName = UserName;
    }
    
    public void setEmail(String Email){
        this.Email = Email;
    }
    
    public void setPassword(String Password){
        this.Password = Password;
    }
    
    public void setSexo(String Sexo){
        this.Sexo = Sexo;
    }
    
    public void setTelefono(String Telefono){
        this.Telefono = Telefono;
    }
    
    public int getIdUsuario(){
        return IdUsuario;
    }
    
    public String getNombre(){
        return Nombre;
    }
    
    public String getApellidoPaterno(){
        return ApellidoPaterno;
    }
    
    public Date getFechaNacimiento(){
        return FechaNacimiento;
    }
    
    public String getApellidoMaterno(){
        return ApellidoMaterno;
    }
    
    public String getUserName(){
        return UserName;
    }
    
    public String getEmail(){
        return Email;
    }
    
    public String getPassword(){
        return Password;
    }
    
    public String getSexo(){
        return Sexo;
    }
    
    public String getTelefono(){
        return Telefono;
    }
    
    //Solo por el motor de plantillas
    public Rol getRol(){
        return Rol;
    }
    
    public void setRol(Rol Rol){
        this.Rol = Rol;
    }
    
    public List<Direccion> getDirecciones(){
        return Direcciones;
    }
    
    public void setDirecciones(List<Direccion> Direcciones){
        this.Direcciones = Direcciones;
    }
    
}
