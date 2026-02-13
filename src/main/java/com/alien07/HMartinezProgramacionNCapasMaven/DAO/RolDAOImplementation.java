/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alien07.HMartinezProgramacionNCapasMaven.DAO;

import com.alien07.HMartinezProgramacionNCapasMaven.ML.Result;
import com.alien07.HMartinezProgramacionNCapasMaven.ML.Rol;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alien 7
 */
@Repository
public class RolDAOImplementation implements IRol {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public Result GetAll() {
        
       Result resultRol = new Result();
       
       try{
           
           jdbcTemplate.execute("CALL RolGetAllSP(?)", (CallableStatementCallback<Boolean>) callableStatement -> {
           
               callableStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
               callableStatement.execute();
               
               ResultSet resultset = (ResultSet) callableStatement.getObject(1);
               
               
               if (resultset.next()) {
                   
                   resultRol.objects = new ArrayList<>();
                   
                   do{
                       
                       resultRol.objects.add(new Rol(resultset.getInt("IdRol"), resultset.getString("Nombre")));
                       
                   } while(resultset.next());
                   
                   resultRol.correct = true;
                   
               } else {
               
                   resultRol.correct = false;
                   resultRol.errorMessage = "No se obtuvieron datos de la BD.";
               
               }
               
               return true;
               
           });
           
       } catch(Exception ex){
       
           resultRol.correct = false;
           resultRol.errorMessage = ex.getLocalizedMessage();
           resultRol.ex = ex;
           
       }
       
       return resultRol;
        
    }
    
}
