/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alien07.HMartinezProgramacionNCapasMaven.ML;

import com.alien07.HMartinezProgramacionNCapasMaven.ML.Rol;
import com.alien07.HMartinezProgramacionNCapasMaven.ML.Direccion;
import jakarta.validation.Valid;;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Alien 7
 */
public class Usuario {
    
    private int IdUsuario;
    
    @Pattern(regexp = "[a-zA-Z áéíóú]+", message = "Sólo debe de escribir letras")
    @NotEmpty(message = "Escriba un nombre")
    @Size(min = 3, max = 50, message = "Debe de contener más de 2 letras y menos de 50")
    private String Nombre;
    
    @Pattern(regexp = "[a-zA-Z áéíóú]+", message = "Sólo debe de escribir letras")
    @NotEmpty(message = "Escriba un nombre")
    @Size(min = 3, max = 50, message = "Debe de contener más de 2 letras y menos de 50")
    private String ApellidoPaterno;
    
    @Pattern(regexp = "[a-zA-Z áéíóú]+", message = "Sólo debe de escribir letras")
    @Size(min = 0, max = 50, message = "Máximo 50 crácteres")
    private String ApellidoMaterno;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Past(message = "Ingrese una fecha válida")
    private Date FechaNacimiento;
    
    @Pattern(regexp = "[a-zA-Z\\d]+", message = "Sólo debe de escribir letras o números")
    @NotEmpty(message = "Escriba un username")
    @Size(min = 3, max = 50, message = "Debe de contener más de 2 letras y menos de 50")
    private String UserName;
    
    @Pattern(regexp = "[a-zA-Z\\d\\._]+@[a-z]+\\.com", message = "El correo es inválido")
    @NotEmpty(message = "Debe de escribir un email")
    @Size(max = 254, message = "Email demasiado largo")
    private String Email;
    
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[`~!@#$%^&\\.\\*()_])[a-zA-Z\\d`~!@#$%^&\\.\\*()_]+$", message = "Debe de contener al menos 1 minúscula, 1 mayúscula, 1 número, 1 carácter especial")
    @NotEmpty(message = "Debe de introducir una contraseña")
    @Size(min = 8, max = 16, message = "Mínimo 8 carácteres y máximo 16")
    private String Password;
    
    @Pattern(regexp = "[F|M|O]", message = "El sexo es inválido")
    @NotEmpty(message = "Debe de seleccionar un sexo")
    private String Sexo;
    
    @Pattern(regexp = "[\\d]{1,10}", message = "El número es inválido")
    @NotEmpty(message = "Debe de introducir un número")
    private String Telefono;
    
    private String Imagen;
    
    private int Status;
    
    @Valid
    public Rol Rol;
    @Valid
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
