/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alien07.HMartinezProgramacionNCapasMaven.UsuarioController;

import com.alien07.HMartinezProgramacionNCapasMaven.ML.*;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Alien 7
 */
@Controller
@RequestMapping("usuarios")
public class Usuarios {
    
    @GetMapping("getall")
    public String GetAll(Model model){
        
        Pais pais = new Pais();
        pais.setIdPais(1);
        pais.setNombre("Mexico");
        
        Estado estado = new Estado();
        estado.setIdEstado(1);
        estado.setNombre("Estado de Mexico");
        
        Municipio municipio = new Municipio();
        municipio.setIdMunicipio(1);
        municipio.setNombre("Chimalhuacán");
        
        Colonia colonia = new Colonia();
        colonia.setIdColonia(1);
        colonia.setNombre("Tepalcates");
        colonia.setCodigoPostal("56334");
        
        ArrayList<Usuario> usuarios = new ArrayList<>();
        Usuario usuario = new Usuario();
        
        Direccion direccion = new Direccion();
        Direccion direccion2 = new Direccion();
        Direccion direccion3 = new Direccion();
        
        direccion2 = direccion;
        direccion3 = direccion;
        
        direccion.setIdDireccion(1);
        direccion.setCalle("Ollita");
        direccion.setNumeroExterior("Mz6");
        direccion.setNumeroInterior("Lt24B");
        
        estado.Pais = pais;
        municipio.Estado = estado;
        colonia.Municipio = municipio;
        direccion.Colonia = colonia;
        
        usuario.Direcciones = new ArrayList<>();
        usuario.Direcciones.add(direccion);
        
        usuario.Rol = new Rol();
        usuario.Rol.setIdRol(1);
        usuario.Rol.setNombre("Administrador");
        
        usuario.setIdUsuario(1);
        usuario.setNombre("Hernan");
        usuario.setApellidoPaterno("Martinez");
        usuario.setApellidoMaterno("Bruno");
        usuario.setFechaNacimiento(new Date());
        usuario.setUserName("kza");
        usuario.setSexo("M");
        usuario.setEmail("hmartinez@gmail.com");
        usuario.setTelefono("1234567890");
        usuario.setPassword("password1");
        
        usuarios.add(usuario);//------------------------------------------------------
        
        usuario = new Usuario();
        usuario.Direcciones = new ArrayList<>();
        usuario.Direcciones.add(direccion);
        
        usuario.Rol = new Rol();
        usuario.Rol.setIdRol(2);
        usuario.Rol.setNombre("DBA");
        
        usuario.setIdUsuario(2);
        usuario.setNombre("David");
        usuario.setApellidoPaterno("Tsutsumi");
        usuario.setApellidoMaterno("Bernal");
        usuario.setFechaNacimiento(new Date());
        usuario.setUserName("babac");
        usuario.setSexo("M");
        usuario.setEmail("dtsutsumi@gmail.com");
        usuario.setTelefono("0987654321");
        usuario.setPassword("password2");
        
        usuarios.add(usuario);//------------------------------------------------------
        
        usuario = new Usuario();
        usuario.Direcciones = new ArrayList<>();
        usuario.Direcciones.add(direccion);
        usuario.Direcciones.add(direccion2);
        usuario.Direcciones.add(direccion3);
        
        usuario.Rol = new Rol();
        usuario.Rol.setIdRol(3);
        usuario.Rol.setNombre("Usuario");
        
        usuario.setIdUsuario(3);
        usuario.setNombre("Gustavo");
        usuario.setApellidoPaterno("Saldivar");
        usuario.setApellidoMaterno("Burgos");
        usuario.setFechaNacimiento(new Date());
        usuario.setUserName("dinoe");
        usuario.setSexo("M");
        usuario.setEmail("gsaldivar@gmail.com");
        usuario.setTelefono("1122334455");
        usuario.setPassword("password3");
        
        usuarios.add(usuario);//------------------------------------------------------
        
        model.addAttribute("usuarios", usuarios);
        
        return "Usuarios";
    
    }
    
}
