/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alien07.HMartinezProgramacionNCapasMaven.DAO;

import com.alien07.HMartinezProgramacionNCapasMaven.JPA.Direccion;
import com.alien07.HMartinezProgramacionNCapasMaven.JPA.Usuario;
import com.alien07.HMartinezProgramacionNCapasMaven.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
            
            TypedQuery<Usuario> queryUsuario = entityManager.createQuery("FROM Usuario ORDER BY IdUsuario ASC", Usuario.class);
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

    @Override
    @Transactional
    public Result Add(com.alien07.HMartinezProgramacionNCapasMaven.ML.Usuario usuario) {
        
        Result resultAdd = new Result();
        
        try {
            
            Usuario usuarioJPA = new Usuario();
            modelMapper.map(usuario, usuarioJPA);
            
            usuarioJPA.Direcciones.get(0).usuario = usuarioJPA;
            
            entityManager.persist(usuarioJPA);
            
            resultAdd.correct = true;
            
        } catch (Exception ex) {
        
            resultAdd.correct = false;
            resultAdd.errorMessage = ex.getLocalizedMessage();
            resultAdd.ex = ex;
            
        }
        
        return resultAdd;
        
    }

    @Override
    public Result GetById(int IdUsuario) {
        
        Result resultById = new Result();
        
        try {
            
            TypedQuery<Usuario> queryJPA = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.IdUsuario = :IdUsuario", Usuario.class);
            queryJPA.setParameter("IdUsuario",IdUsuario);
            
            Usuario usuarioJPA = queryJPA.getSingleResult();
            com.alien07.HMartinezProgramacionNCapasMaven.ML.Usuario usuarioML = new com.alien07.HMartinezProgramacionNCapasMaven.ML.Usuario();
            
            modelMapper.map(usuarioJPA, usuarioML);
            
            resultById.object = usuarioML;
            resultById.correct = true;
            
        } catch (Exception ex) {
        
            resultById.correct = false;
            resultById.errorMessage = ex.getLocalizedMessage();
            resultById.ex = ex;
            
        }
        
        return resultById;
        
    }

    @Override
    @Transactional
    public Result AgregarDireccion(com.alien07.HMartinezProgramacionNCapasMaven.ML.Direccion direccion, int IdUsuario) {
        
        Result resultUpdate = new Result();
        
        try {
            
            Usuario usuarioJPA = entityManager.find(Usuario.class, IdUsuario);
            
            if (usuarioJPA != null) {
            
                Direccion direccionJPA = new Direccion();
                modelMapper.map(direccion, direccionJPA);
                direccionJPA.usuario = usuarioJPA;

                entityManager.merge(direccionJPA);

                resultUpdate.correct = true;
                
            } else {
            
                resultUpdate.correct = false;
                resultUpdate.errorMessage = "No se encontró el usuario en la BD.";
            
            }
            
        } catch (Exception ex) {
        
            resultUpdate.correct = false;
            resultUpdate.errorMessage = ex.getLocalizedMessage();
            resultUpdate.ex = ex;
            
        }
        
        return resultUpdate;
        
    }

    @Override
    public Result GetDireccionById(int IdDireccion) {
        
        Result resultById = new Result();
        
        try {
            
            Direccion direccionJPA = entityManager.find(Direccion.class, IdDireccion);
            
            if (direccionJPA != null) {
                
                com.alien07.HMartinezProgramacionNCapasMaven.ML.Direccion direccionML = new com.alien07.HMartinezProgramacionNCapasMaven.ML.Direccion();
            
                modelMapper.map(direccionJPA, direccionML);

                resultById.object = direccionML;
                resultById.correct = true;
                
            } else {
            
                resultById.correct = false;
                resultById.errorMessage = "No se encontró el registro en la BD.";
            
            }
            
            
            
        } catch (Exception ex) {
        
            resultById.correct = false;
            resultById.errorMessage = ex.getLocalizedMessage();
            resultById.ex = ex;
        }
        
        return resultById;
        
    }

    @Override
    @Transactional
    public Result UpdateDireccion(com.alien07.HMartinezProgramacionNCapasMaven.ML.Direccion direccion, int IdUsuario) {
        
        Result resultUpdate = new Result();
        
        try {
            
            Usuario usuarioJPA = entityManager.find(Usuario.class, IdUsuario);
            
            if (usuarioJPA != null) {
                
                Direccion direccionJPA = new Direccion();
            
                modelMapper.map(direccion, direccionJPA);

                direccionJPA.usuario = usuarioJPA;

                entityManager.merge(direccionJPA);

                resultUpdate.correct = true;
                
            } else {
            
                resultUpdate.correct = false;
                resultUpdate.errorMessage = "No se encontró el registro en la BD.";
            
            }
            
            
            
        } catch (Exception ex) {
        
            resultUpdate.correct = true;
            resultUpdate.errorMessage = ex.getLocalizedMessage();
            resultUpdate.ex = ex;
            
        }
        
        return resultUpdate;
        
    }

    @Override
    @Transactional
    public Result UpdateUsuario(com.alien07.HMartinezProgramacionNCapasMaven.ML.Usuario usuario) {
        
        Result resultUpdate = new Result();
        
        try {
            
            Usuario usuarioJPA = entityManager.find(Usuario.class, usuario.getIdUsuario());
            
            if (usuarioJPA != null) {
                
                MapearDatosActualizados(usuario, usuarioJPA);
            
                entityManager.merge(usuarioJPA);

                resultUpdate.correct = true;
                
            } else {
            
                resultUpdate.correct = false;
                resultUpdate.errorMessage = "No se encontró al usuario en la BD.";
            
            }
            
        } catch (Exception ex) {
            
            resultUpdate.correct = false;
            resultUpdate.errorMessage = ex.getLocalizedMessage();
            resultUpdate.ex = ex;
            
        }
        
        return resultUpdate;
        
    }
    
    static void MapearDatosActualizados(com.alien07.HMartinezProgramacionNCapasMaven.ML.Usuario usuarioML, Usuario usuarioJPA){
    
        usuarioJPA.setNombre(usuarioML.getNombre());
        usuarioJPA.setApellidoPaterno(usuarioML.getApellidoPaterno());
        usuarioJPA.setApellidoMaterno(usuarioML.getApellidoMaterno());
        usuarioJPA.setFechaNacimiento(usuarioML.getFechaNacimiento());
        usuarioJPA.setUserName(usuarioML.getUserName());
        usuarioJPA.setEmail(usuarioML.getEmail());
        usuarioJPA.setTelefono(usuarioML.getTelefono());
        usuarioJPA.setPassword(usuarioML.getPassword());
        usuarioJPA.setSexo(usuarioML.getSexo());
        usuarioJPA.Rol.setIdRol(usuarioML.Rol.getIdRol());
        
    }

    @Override
    @Transactional
    public Result DeleteDireccion(int IdDireccion) {
        
        Result resultDelete = new Result();
        
        try {
            
            Direccion direccionJPA = entityManager.find(Direccion.class, IdDireccion);
            
            if (direccionJPA != null) {
                
                entityManager.remove(direccionJPA);
            
                resultDelete.correct = true;
                
            } else {
            
                resultDelete.correct = false;
                resultDelete.errorMessage = "No se encontró el registro en la BD.";
            
            }
            
        } catch (Exception ex) {
        
            resultDelete.correct = false;
            resultDelete.errorMessage = ex.getLocalizedMessage();
            resultDelete.ex = ex;
            
        }
        
        return resultDelete;
        
    }

    @Override
    @Transactional
    public Result UpdateImagen(int IdUsuario, String imagen) {
        
        Result resultUpdate = new Result();
        
        try {
            
            Usuario usuarioJPA = entityManager.find(Usuario.class, IdUsuario);
            
            if (usuarioJPA != null) {
                
                usuarioJPA.setImagen(imagen);
            
                entityManager.merge(usuarioJPA);

                resultUpdate.correct = true;
                
            } else {
            
                resultUpdate.correct = false;
                resultUpdate.errorMessage = "No se encontró el registro en la BD.";
                
            }
            
        } catch (Exception ex) {
        
            resultUpdate.correct = false;
            resultUpdate.errorMessage = ex.getLocalizedMessage();
            resultUpdate.ex = ex;
            
        }
        
        return resultUpdate;
        
    }

    @Override
    @Transactional
    public Result DeleteUsuario(int IdUsuario) {
        
        Result resultDelete = new Result();
        
        try {
            
            Usuario usuarioJPA = entityManager.find(Usuario.class, IdUsuario);
            
            if (usuarioJPA != null) {
                
                entityManager.remove(usuarioJPA);
            
                resultDelete.correct = true;
                
            } else {
            
                resultDelete.correct = false;
                resultDelete.errorMessage = "No se encontró el registro en la BD.";
            
            }
            
            
            
        } catch (Exception ex) {
        
            resultDelete.correct = false;
            resultDelete.errorMessage = ex.getLocalizedMessage();
            resultDelete.ex = ex;
            
        }
        
        return resultDelete;
        
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result CargaMasiva(List<com.alien07.HMartinezProgramacionNCapasMaven.ML.Usuario> usuarios) {
        
        Result resultCarga = new Result();
        
        try {
            
            List<Usuario> usuariosJPA = usuarios.stream().map(usuario -> modelMapper.map(usuario, Usuario.class)).collect(Collectors.toList());
            
            for (int i = 0; i < usuariosJPA.size(); i++) {
                
                Usuario usuario = usuariosJPA.get(i);
                entityManager.persist(usuario);
                
                if (i % 50 == 0 && i > 0) {
                    
                    entityManager.flush();
                    entityManager.clear();
                    
                }
                
            }
            
            resultCarga.correct = true;
            
        } catch (Exception ex) {
            
            resultCarga.correct = false;
            resultCarga.errorMessage = ex.getLocalizedMessage();
            resultCarga.ex = ex;
            
        }
        
        return resultCarga;
        
    }

    @Override
    @Transactional
    public Result ActualizarStatus(int IdUsuario, int Status) {
        
        Result resultStatus = new Result();
        
        try {
            
            Usuario usuarioJPA = entityManager.find(Usuario.class, IdUsuario);
            
            if (usuarioJPA != null) {
                
                usuarioJPA.setStatus(Status);
            
                entityManager.merge(usuarioJPA);

                resultStatus.correct = true;
                
            } else {
            
                resultStatus.correct = false;
                resultStatus.errorMessage = "No se encontró el registro en la BD.";
            
            }
            
            
            
        } catch (Exception ex) {
        
            resultStatus.correct = false;
            resultStatus.errorMessage = ex.getLocalizedMessage();
            resultStatus.ex = ex;
            
        }
        
        return resultStatus;
        
    }

    @Override
    public Result Busqueda(String Nombre, String ApellidoPaterno, String ApellidoMaterno, int IdRol) {
        
        Result resultBusqueda = new Result();
        
        try {
            
            String queryPrincipal = """
                                    FROM Usuario
                                    WHERE (:nombre IS NULL OR LOWER (Nombre) LIKE LOWER (:nombre))
                                    AND (:apellidoP IS NULL OR LOWER (ApellidoPaterno) LIKE LOWER (:apellidoP))
                                    AND (:apellidoM IS NULL OR LOWER (ApellidoMaterno) LIKE LOWER (:apellidoM))
                                    """;
            
            if (IdRol > 0) {
                
                queryPrincipal += " AND Rol.IdRol = :IdRol"; 
                
            }
            
            TypedQuery<Usuario> queryBusqueda = entityManager.createQuery(queryPrincipal, Usuario.class);
            
            queryBusqueda.setParameter("nombre", (Nombre == null || Nombre.isBlank()) ? null : "%" + Nombre + "%");
            queryBusqueda.setParameter("apellidoP", (ApellidoPaterno == null || ApellidoPaterno.isBlank()) ? null : "%" + ApellidoPaterno + "%");
            queryBusqueda.setParameter("apellidoM", (ApellidoMaterno == null || ApellidoMaterno.isBlank()) ? null : "%" + ApellidoMaterno + "%");
            
            if (IdRol > 0) {
                
                queryBusqueda.setParameter("IdRol", IdRol);
                
            }
            
            List<Usuario> usuarios = queryBusqueda.getResultList();
            
            resultBusqueda.objects = usuarios.stream().map(usuario -> modelMapper.map(usuario, com.alien07.HMartinezProgramacionNCapasMaven.ML.Usuario.class)).collect(Collectors.toList());
            
            if (resultBusqueda.objects.isEmpty()) {
                
                resultBusqueda.errorMessage = "No se encontraron registros en la BD";
            }
            
            resultBusqueda.correct = true;
            
        } catch (Exception ex) {
        
            resultBusqueda.correct = false;
            resultBusqueda.errorMessage = ex.getLocalizedMessage();
            resultBusqueda.ex = ex;
            
        }
        
        return resultBusqueda;
        
    }
    
    
}
