/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alien07.HMartinezProgramacionNCapasMaven.DAO;

import com.alien07.HMartinezProgramacionNCapasMaven.ML.Direccion;
import com.alien07.HMartinezProgramacionNCapasMaven.ML.Usuario;
import com.alien07.HMartinezProgramacionNCapasMaven.ML.Result;
import java.util.List;

/**
 *
 * @author Alien 7
 */
public interface IUsuario {
    
    //Firmas de metodo
    Result GetAll();
    Result GetById(int IdUsuario);
    Result GetDireccionById(int IdUsuario, int IdDireccion);
    Result UsuarioDireccionAdd(Usuario usuario);
    Result Delete(int IdUsuario);
    Result imagenUpdate(String imagenConvertida, int IdUsuario);
    Result Update(Usuario usuario);
    Result GetByParams(String Nombre, String ApellidoPaterno, String ApellidoMaterno, int IdRol);
    Result AddAll(List<Usuario> usuarios);
    Result ActualizarDireccion(Direccion direccion);
    Result StatusUpdate(int IdUsuario, int Status);
    
    
}
