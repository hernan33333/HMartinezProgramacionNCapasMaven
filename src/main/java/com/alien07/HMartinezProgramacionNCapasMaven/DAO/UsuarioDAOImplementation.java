/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alien07.HMartinezProgramacionNCapasMaven.DAO;

import com.alien07.HMartinezProgramacionNCapasMaven.ML.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class UsuarioDAOImplementation implements IUsuario {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Result GetAll() {

        Result result = new Result();

        jdbcTemplate.execute("CALL UsuarioDireccionGetAllSP(?)", (CallableStatementCallback<Boolean>) callableStatement -> {

            callableStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
            callableStatement.execute();

            ResultSet resultset = (ResultSet) callableStatement.getObject(1);

            result.objects = new ArrayList<>();

            while (resultset.next()) {

                int idUsuario = resultset.getInt("IdUsuario");

                if (!result.objects.isEmpty() && idUsuario == ((Usuario) (result.objects.get(result.objects.size() - 1))).getIdUsuario()) {

                    ((Usuario) (result.objects.get(result.objects.size() - 1))).Direcciones.add(agregarDireccion(resultset));

                } else {

                    Usuario usuario = new Usuario();
                    usuario.Rol = new Rol();

                    usuario.setIdUsuario(idUsuario);
                    usuario.setNombre(resultset.getString("NombreUsuario"));
                    usuario.setApellidoPaterno(resultset.getString("ApellidoPaterno"));
                    usuario.setApellidoMaterno(resultset.getString("ApellidoMaterno"));
                    usuario.setUserName(resultset.getString("UserName"));
                    usuario.setFechaNacimiento(resultset.getDate("FechaNacimiento"));
                    usuario.setEmail(resultset.getString("Email"));
                    usuario.setSexo(resultset.getString("Sexo"));
                    usuario.setTelefono(resultset.getString("Telefono"));
                    usuario.Rol.setNombre(resultset.getString("NombreRol"));
                    usuario.setImagen(resultset.getString("Imagen"));

                    int IdDireccion = resultset.getInt("IdDireccion");

                    if (IdDireccion != 0) {

                        usuario.Direcciones = new ArrayList<>();

                        usuario.Direcciones.add(agregarDireccion(resultset));

                    }

                    result.objects.add(usuario);

                }

            }

            result.correct = true;

            return true;
        });

        return result;

    }

    @Override
    public Result GetById(int IdUsuario) {

        Result result = new Result();

        try {

            jdbcTemplate.execute("CALL UsuarioDireccionByIdSP(?,?)", (CallableStatementCallback<Boolean>) callableStatement -> {

                callableStatement.setInt(1, IdUsuario);
                callableStatement.registerOutParameter(2, java.sql.Types.REF_CURSOR);

                callableStatement.execute();

                ResultSet resultset = (ResultSet) callableStatement.getObject(2);

                if (resultset.next()) {

                    Usuario usuario = new Usuario();
                    usuario.Rol = new Rol();

                    usuario.setIdUsuario(IdUsuario);
                    usuario.setNombre(resultset.getString("NombreUsuario"));
                    usuario.setApellidoPaterno(resultset.getString("ApellidoPaterno"));
                    usuario.setApellidoMaterno(resultset.getString("ApellidoMaterno"));
                    usuario.setUserName(resultset.getString("UserName"));
                    usuario.setFechaNacimiento(resultset.getDate("FechaNacimiento"));
                    usuario.setEmail(resultset.getString("Email"));
                    usuario.setSexo(resultset.getString("Sexo"));
                    usuario.setTelefono(resultset.getString("Telefono"));
                    usuario.Rol.setIdRol(resultset.getInt("IdRol"));
                    usuario.Rol.setNombre(resultset.getString("NombreRol"));
                    usuario.setImagen(resultset.getString("Imagen"));

                    int idDireccion = resultset.getInt("IdDireccion");

                    if (idDireccion != 0) {

                        usuario.Direcciones = new ArrayList<>();

                        do {

                            usuario.Direcciones.add(agregarDireccion(resultset));

                        } while (resultset.next());
                    }

                    result.correct = true;
                    result.object = usuario;

                } else {

                    result.correct = false;
                    result.errorMessage = "La consulta no regreso ningun resultado.";

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

    public static Direccion agregarDireccion(ResultSet resultset) throws SQLException {

        Direccion direccion = new Direccion();
        direccion.Colonia = new Colonia();
        direccion.Colonia.Municipio = new Municipio();
        direccion.Colonia.Municipio.Estado = new Estado();
        direccion.Colonia.Municipio.Estado.Pais = new Pais();

        direccion.setIdDireccion(resultset.getInt("IdDireccion"));
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

        return direccion;

    }

    @Override
    public Result UsuarioDireccionAdd(Usuario usuario) {

        Result result = new Result();

        try {
            
            jdbcTemplate.execute("{CALL UsuarioDireccionAddSP(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}", (CallableStatementCallback<Boolean>) callableStatement -> {

                callableStatement.setString(1, usuario.getNombre());
                callableStatement.setString(2, usuario.getApellidoPaterno());
                callableStatement.setDate(3, new Date(usuario.getFechaNacimiento().getTime()));
                callableStatement.setString(4, usuario.getUserName());
                callableStatement.setString(5, usuario.getApellidoMaterno());
                callableStatement.setString(6, usuario.getEmail());
                callableStatement.setString(7, usuario.getPassword());
                callableStatement.setString(8, usuario.getSexo());
                callableStatement.setString(9, usuario.getTelefono());
                callableStatement.setInt(10, usuario.Rol.getIdRol());
                callableStatement.setString(11, usuario.Direcciones.get(0).getCalle());
                callableStatement.setString(12, usuario.Direcciones.get(0).getNumeroExterior());
                callableStatement.setString(13, usuario.Direcciones.get(0).getNumeroInterior());
                callableStatement.setInt(14, usuario.Direcciones.get(0).Colonia.getIdColonia());
                callableStatement.setString(15, usuario.getImagen());

                int resultadoInsercion = callableStatement.executeUpdate();
                if (resultadoInsercion == 1) {

                    result.correct = true;

                } else {

                    result.correct = false;
                    result.errorMessage = "La ejecución devolvió el código: " + resultadoInsercion;

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

    public Result DeleteById(int IdUsuaurio) {
        
        Result result = new Result();
        
        try {
            
            jdbcTemplate.execute("{CALL UsuarioDeleteSP(?)}", (CallableStatementCallback<Boolean>) callableStatement -> {
                
                callableStatement.setInt(1, IdUsuaurio);
                
                int resultadoEliminacion = callableStatement.executeUpdate();
                
                if (resultadoEliminacion == 1) {
                    
                    result.correct = true;
                    
                } else {
                
                    result.correct = false;
                    result.errorMessage = "No se pudo eliminar al usuario";
                
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

    public Result imagenUpdate(String imagenConvertida, int IdUsuario) {
        
        Result resultUsuario = GetById(IdUsuario);
        Result resultImagen = new Result();
        
        if (resultUsuario.correct) {
            
            try {
            
                jdbcTemplate.execute("{CALL UsuarioImageUpdateSP(?)}", (CallableStatementCallback<Boolean>) callableStatement -> {

                    callableStatement.setInt(1, IdUsuario);
                    callableStatement.setString(2, imagenConvertida);

                    int actualizacionImagen = callableStatement.executeUpdate();

                    if (actualizacionImagen == 1) {

                        resultImagen.correct = true;

                    } else {

                        resultImagen.correct = false;

                    }

                    return true;

                });

            } catch (Exception ex) {

                resultImagen.correct = false;
                resultImagen.errorMessage = "Hubo un error al intentar cambiar la imagen";
                resultImagen.ex = ex;

            }
            
        } else {
        
            resultImagen.correct = false;
        
        }
        
        return resultImagen;
        
    }

}
