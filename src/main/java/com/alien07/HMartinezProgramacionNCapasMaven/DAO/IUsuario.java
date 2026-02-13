/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alien07.HMartinezProgramacionNCapasMaven.DAO;

import com.alien07.HMartinezProgramacionNCapasMaven.ML.Usuario;
import com.alien07.HMartinezProgramacionNCapasMaven.ML.Result;

/**
 *
 * @author Alien 7
 */
public interface IUsuario {
    
    //Firmas de metodo
    Result GetAll();
    Result GetById(int IdUsuario);
    
    Result UsuarioDireccionAdd(Usuario usuario);
    
}
