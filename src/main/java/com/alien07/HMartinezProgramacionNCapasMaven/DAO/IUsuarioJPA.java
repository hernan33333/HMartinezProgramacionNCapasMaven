package com.alien07.HMartinezProgramacionNCapasMaven.DAO;

import com.alien07.HMartinezProgramacionNCapasMaven.ML.Result;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author Alien 7
 */
public interface IUsuarioJPA {
    
    Result GetAll();
    Result Add(com.alien07.HMartinezProgramacionNCapasMaven.ML.Usuario usuario);
    Result GetById(int IdUsuario);
    Result AgregarDireccion(com.alien07.HMartinezProgramacionNCapasMaven.ML.Direccion direccion, int IdUsuario);
    Result GetDireccionById(int IdDireccion);
    Result UpdateDireccion(com.alien07.HMartinezProgramacionNCapasMaven.ML.Direccion direccion, int IdUsuario);
    Result UpdateUsuario(com.alien07.HMartinezProgramacionNCapasMaven.ML.Usuario usuario);
    Result DeleteDireccion(int IdDireccion);
    Result UpdateImagen(int IdUsuario, String imagen);
    Result DeleteUsuario(int IdUsuario);
    Result CargaMasiva(List<com.alien07.HMartinezProgramacionNCapasMaven.ML.Usuario> usuarios);
    Result ActualizarStatus(int IdUsuario, int Status);
    Result Busqueda(String Nombre, String ApellidoPaterno, String ApellidoMaterno, int IdRol);
    
}
