/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alien07.HMartinezProgramacionNCapasMaven.DAO;

import com.alien07.HMartinezProgramacionNCapasMaven.ML.Pais;
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
public class PaisDAOImplementation implements IPais {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public Result GetAll() {
        
        Result resultPais = new Result();
        
        try {
            
            jdbcTemplate.execute("CALL PaisGetAllSP(?)", (CallableStatementCallback<Boolean>) callableStatement ->{
                
                callableStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
                callableStatement.execute();
                
                ResultSet resultset = (ResultSet) callableStatement.getObject(1);
                
                if (resultset.next()) {
                    
                    resultPais.objects = new ArrayList<>();
                    
                    do{
                        
                        resultPais.objects.add(new Pais(resultset.getInt("IdPais"), resultset.getString("Nombre")));
                        
                    } while (resultset.next());
                    
                    resultPais.correct = true;
                    
                } else {
                
                    resultPais.correct = false;
                    resultPais.errorMessage = "No se obtuvieron datos de la BD.";
                
                }
                
                return true;
            
            });
        
        } catch (Exception ex){
        
            resultPais.correct = false;
            resultPais.errorMessage = ex.getLocalizedMessage();
            resultPais.ex = ex;
        
        }
        
        return resultPais;
        
    }
    
}
