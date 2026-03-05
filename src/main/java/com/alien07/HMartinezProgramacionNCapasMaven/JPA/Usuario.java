/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alien07.HMartinezProgramacionNCapasMaven.JPA;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Alien 7
 */
@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private int IdUsuario;
    
    @Column(name = "nombre")
    private String Nombre;
    
    @Column(name = "apellidopaterno")
    private String ApellidoPaterno;
    
    @Column(name = "apellidomaterno")
    private String ApellidoMaterno;
    
    @Column(name = "fechanacimiento")
    private Date FechaNacimiento;
    
    @Column(name = "username")
    private String UserName;
    
    @Column(name = "email")
    private String Email;
    
    @Column(name = "password")
    private String Password;
    
    @Column(name = "sexo")
    private String Sexo;
    
    @Column(name = "telefono")
    private String Telefono;
    
    @Column(name = "imagen")
    @Lob
    private String Imagen;
    
    @Column(name = "status")
    private int Status;
    
    @ManyToOne
    @JoinColumn(name = "idrol")
    public Rol Rol;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
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
    
    public String getImagen(){
        return Imagen;
    }
    
    public void setImagen(String Imagen){
        this.Imagen = Imagen;
    }
    
    public int getStatus(){
        return Status;
    }
    
    public void setStatus(int Status){
        this.Status = Status;
    }
    
}
