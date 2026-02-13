/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alien07.HMartinezProgramacionNCapasMaven.DAO;

import com.alien07.HMartinezProgramacionNCapasMaven.ML.Colonia;
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
public class ColoniaDAOImplementation implements IColonia {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public Result GetByMunicipio(int IdMunicipio) {
        
        Result result = new Result();
        
        jdbcTemplate.execute("CALL ColoniasGetByMunicipioSP(?,?)", (CallableStatementCallback<Boolean>) callableStatement -> {
        
            callableStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
            callableStatement.setInt(2, IdMunicipio);
            
            callableStatement.execute();
            
            ResultSet resultset = (ResultSet) callableStatement.getObject(1);
            
            if (resultset.next()) {
                
                result.objects = new ArrayList<>();
                
                do {
                    
                    result.objects.add(new Colonia(resultset.getInt("IdColonia"), resultset.getString("Nombre"), resultset.getString("CodigoPostal")));
                    
                } while (resultset.next());
                
                
                result.correct = true;
                
            } else {
            
                result.correct = false;
                result.errorMessage = "No se obtuvieron datos de la BD.";
            
            }
            
            
            return true;
        
        });
        
        return result;
        
    }
    
}
