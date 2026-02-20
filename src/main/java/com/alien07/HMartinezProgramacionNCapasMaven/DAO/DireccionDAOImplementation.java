/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alien07.HMartinezProgramacionNCapasMaven.DAO;

import com.alien07.HMartinezProgramacionNCapasMaven.ML.Colonia;
import com.alien07.HMartinezProgramacionNCapasMaven.ML.Direccion;
import com.alien07.HMartinezProgramacionNCapasMaven.ML.Estado;
import com.alien07.HMartinezProgramacionNCapasMaven.ML.Municipio;
import com.alien07.HMartinezProgramacionNCapasMaven.ML.Pais;
import com.alien07.HMartinezProgramacionNCapasMaven.ML.Result;
import java.sql.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alien 7
 */
@Repository
public class DireccionDAOImplementation implements IDireccion {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Result GetById(int IdDireccion) {
        
        Result resultDireccion = new Result();
        
        try {
            
            jdbcTemplate.execute("{CALL DireccionGetById(?,?)}", (CallableStatementCallback<Boolean>) callableStatement ->{
            
                callableStatement.setInt(1, IdDireccion);
                callableStatement.registerOutParameter(2, java.sql.Types.REF_CURSOR);
                
                callableStatement.execute();
                
                ResultSet resultset = (ResultSet) callableStatement.getObject(2);
                
                if (resultset.next()) {
                    
                    Direccion direccion = new Direccion();
                    direccion.Colonia = new Colonia();
                    direccion.Colonia.Municipio = new Municipio();
                    direccion.Colonia.Municipio.Estado = new Estado();
                    direccion.Colonia.Municipio.Estado.Pais = new Pais();
                    
                    direccion.setCalle(resultset.getString("Calle"));
                    direccion.setNumeroInterior(resultset.getString("NumeroInterior"));
                    direccion.setNumeroExterior(resultset.getString("NumeroExterior"));
                    direccion.Colonia.setIdColonia(resultset.getInt("IdColonia"));
                    direccion.Colonia.setNombre(resultset.getString("NombreColonia"));
                    direccion.Colonia.setCodigoPostal(resultset.getString("CodigoPostal"));
                    direccion.Colonia.Municipio.setIdMunicipio(resultset.getInt("IdMunicipio"));
                    direccion.Colonia.Municipio.setNombre(resultset.getString("NombreMunicipio"));
                    direccion.Colonia.Municipio.Estado.setIdEstado(resultset.getInt("IdEstado"));
                    direccion.Colonia.Municipio.Estado.setNombre(resultset.getString("NombreEstado"));
                    direccion.Colonia.Municipio.Estado.Pais.setIdPais(resultset.getInt("IdPais"));
                    direccion.Colonia.Municipio.Estado.Pais.setNombre(resultset.getString("NombrePais"));
                    
                    resultDireccion.object = direccion;
                    resultDireccion.correct = true;
                    
                } else {
                
                    resultDireccion.correct = false;
                    resultDireccion.errorMessage = "No se encontró el registro";
                
                }
                
                return true;
            
            });
            
        } catch (Exception ex) {
            
            resultDireccion.correct = false;
            resultDireccion.errorMessage = "No se pudo obtener la dirección: " + ex.getLocalizedMessage();
            resultDireccion.ex = ex;
            
        }
        
        return resultDireccion;
        
    }

    @Override
    public Result DireccionAdd(Direccion direccion, int IdUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Result DireccionDelete(int IdDireccion) {
        
        Result resultDireccion = new Result();
        
        try {
            
            jdbcTemplate.execute("{CALL DeleteDireccionSP(?)}", (CallableStatementCallback<Boolean>) callableStatement -> {
            
                callableStatement.setInt(1, IdDireccion);
                
                int resultadoEliminacion = callableStatement.executeUpdate();
                
                if (resultadoEliminacion == 1) {
                    
                    resultDireccion.correct = true;
                    
                } else {
                
                    resultDireccion.correct = false;
                    resultDireccion.errorMessage = "No se pudo eliminar el registro";
                
                }
                
                return true;
            
            });
            
        } catch (Exception ex) {
        
            resultDireccion.correct = false;
            resultDireccion.errorMessage = ex.getLocalizedMessage();
            resultDireccion.ex = ex;
            
        }
        
        return resultDireccion;
        
    }
    
}
