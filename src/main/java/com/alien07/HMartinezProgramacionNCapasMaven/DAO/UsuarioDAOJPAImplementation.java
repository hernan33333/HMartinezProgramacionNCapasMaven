/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alien07.HMartinezProgramacionNCapasMaven.DAO;

import com.alien07.HMartinezProgramacionNCapasMaven.JPA.Usuario;
import com.alien07.HMartinezProgramacionNCapasMaven.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alien 7
 */
@Repository
public class UsuarioDAOJPAImplementation implements IUsuarioJPA {

    @Autowired
    EntityManager entityManager;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public Result GetAll() {
        
        Result resultAll = new Result();
        
        try {
            
            TypedQuery<Usuario> queryUsuario = entityManager.createQuery("FROM Usuario", Usuario.class);
            List<Usuario> usuarios = queryUsuario.getResultList();
            
            resultAll.objects = usuarios.stream().map(usuario -> modelMapper.map(usuario, com.alien07.HMartinezProgramacionNCapasMaven.ML.Usuario.class)).collect(Collectors.toList());
            
            resultAll.correct = true;
            
        } catch (Exception ex) {
            
            resultAll.correct = false;
            resultAll.errorMessage = ex.getLocalizedMessage();
            resultAll.ex = ex;
            
        }
        
        return resultAll;
        
    }
    
}
