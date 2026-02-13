/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alien07.HMartinezProgramacionNCapasMaven.DAO;

import com.alien07.HMartinezProgramacionNCapasMaven.ML.Estado;
import com.alien07.HMartinezProgramacionNCapasMaven.ML.Result;
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
public class EstadoDAOImplementation implements IEstado {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public Result GetAll() {
        
        Result result = new Result();
        
        try{
            
            jdbcTemplate.execute("CALL EstadoGetAllSP(?)", (CallableStatementCallback<Boolean>) callableStatement -> {
                
                callableStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
                callableStatement.execute();
                
                ResultSet resultset = (ResultSet) callableStatement.getObject(1);
                
                if (resultset.next()) {
                    
                    result.objects = new ArrayList<>();
                    
                    do{
                        
                        result.objects.add(new Estado(resultset.getInt("IdEstado"), resultset.getString("Nombre")));
                        
                    } while (resultset.next());
                    
                    result.correct = true;
                    
                } else {
                
                    result.correct = false;
                    result.errorMessage = "No se obtubieron datos de la BD.";
                
                }
                
                return true;
                
            });
            
        } catch(Exception ex){
            
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            
        }
        
        return result;
        
    }

    @Override
    public Result GetByPais(int IdPais) {
        
        Result result = new Result();
        
        try {
            
            jdbcTemplate.execute("CALL EstadoGetByPaisSP(?,?)", (CallableStatementCallback<Boolean>) callableStatement -> {
            
                callableStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
                callableStatement.setInt(2, IdPais);
                
                callableStatement.execute();
                
                ResultSet resultset = (ResultSet) callableStatement.getObject(1);
                
                if (resultset.next()) {
                    
                    result.objects = new ArrayList<>();
                    
                    do{
                        
                        result.objects.add(new Estado(resultset.getInt("IdEstado"), resultset.getString("Nombre")));
                        
                    } while (resultset.next());
                    
                    result.correct= true;
                    
                } else {
                
                    result.correct = false;
                    result.errorMessage = "No se obtuvieron datos de la BD.";
                
                }
                
                return true;
            
            });
            
        } catch (Exception ex) {
        
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            
        }
        
        return result;
        
    }
    
}
