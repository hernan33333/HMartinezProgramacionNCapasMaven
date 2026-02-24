/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alien07.HMartinezProgramacionNCapasMaven.Controller;

import com.alien07.HMartinezProgramacionNCapasMaven.DAO.ColoniaDAOImplementation;
import com.alien07.HMartinezProgramacionNCapasMaven.DAO.DireccionDAOImplementation;
import com.alien07.HMartinezProgramacionNCapasMaven.DAO.EstadoDAOImplementation;
import com.alien07.HMartinezProgramacionNCapasMaven.DAO.MunicipioDAOImplementation;
import com.alien07.HMartinezProgramacionNCapasMaven.DAO.PaisDAOImplementation;
import com.alien07.HMartinezProgramacionNCapasMaven.DAO.RolDAOImplementation;
import com.alien07.HMartinezProgramacionNCapasMaven.DAO.UsuarioDAOImplementation;
import com.alien07.HMartinezProgramacionNCapasMaven.ML.Direccion;
import com.alien07.HMartinezProgramacionNCapasMaven.ML.Usuario;
import com.alien07.HMartinezProgramacionNCapasMaven.ML.Result;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Alien 7
 */
@Controller
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioDAOImplementation usuarioDAOImplementation;
    @Autowired
    private DireccionDAOImplementation direccionDAOImplementation;
    @Autowired
    private PaisDAOImplementation paisDAOImplementation;
    @Autowired
    private RolDAOImplementation rolDAOImplementation;
    @Autowired
    private EstadoDAOImplementation estadoDAOImplementation;
    @Autowired
    private MunicipioDAOImplementation municipioDAOImplementation;
    @Autowired
    private ColoniaDAOImplementation coloniaDAOImplementation;

    @GetMapping()// localhost:8080/usuario
    public String Index(Model model) {

        Result result = usuarioDAOImplementation.GetAll();

        model.addAttribute("usuarios", result.objects);
        model.addAttribute("roles", rolDAOImplementation.GetAll().objects);
        model.addAttribute("usuario", new Usuario());

        return "Usuario";

    }

    @GetMapping("getbyid/{idusuario}")
    public String GetById(@PathVariable("idusuario") int IdUsuario, Model model) {

        Result resultUsuario = usuarioDAOImplementation.GetById(IdUsuario);

        model.addAttribute("usuario", resultUsuario.object);

        model.addAttribute("direccion", new Direccion());

        Result resultPais = paisDAOImplementation.GetAll();
        model.addAttribute("paises", resultPais.objects);

        Result resultRol = rolDAOImplementation.GetAll();
        model.addAttribute("roles", resultRol.objects);

        return "UsuarioDetail";

    }

    @GetMapping("formulario") // localhost:8080/usuario/formulario
    public String Formulario(Model model) {

        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);

        Result resultPais = paisDAOImplementation.GetAll();
        model.addAttribute("paises", resultPais.objects);

        Result resultRol = rolDAOImplementation.GetAll();
        model.addAttribute("roles", resultRol.objects);

        return "formulario";
    }
    
    @GetMapping("getestadosbypais/{IdPais}")
    @ResponseBody
    public Result GetEstadoByPais(@PathVariable("IdPais") int IdPais) {

        Result resultPais = estadoDAOImplementation.GetByPais(IdPais);

        return resultPais;

    }

    @GetMapping("getmunicipiosbyestado/{IdEstado}")
    @ResponseBody
    public Result GetMunicipiosByEstado(@PathVariable("IdEstado") int IdEstado) {

        Result result = municipioDAOImplementation.GetByEstado(IdEstado);

        return result;

    }

    @GetMapping("getcoloniasbymunicipio/{IdMunicipio}")
    @ResponseBody
    public Result GetColoniadByMunicipio(@PathVariable("IdMunicipio") int IdMunicipio) {

        Result result = coloniaDAOImplementation.GetByMunicipio(IdMunicipio);

        return result;

    }

    @PostMapping("actualizarusuario/{IdUsuario}")
    public String ActualizarUsuario(@PathVariable("IdUsuario") int IdUsuario, @Valid @ModelAttribute("usuario") Usuario usuario, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        
        if (bindingResult.hasErrors()) {
            
            Result resultUsuario = usuarioDAOImplementation.GetById(IdUsuario);

            model.addAttribute("usuario", resultUsuario.object);
            model.addAttribute("direccion", new Direccion());
            model.addAttribute("paises", paisDAOImplementation.GetAll().objects);
            model.addAttribute("roles", rolDAOImplementation.GetAll().objects);
            model.addAttribute("errorMessageUsuarioUpdate", "La información proporcionada no es válida, revisela.");
            
            return "UsuarioDetail";
        }
        
        Result resultUpdate = usuarioDAOImplementation.Update(usuario);
        
        if (resultUpdate.correct) {
            
            redirectAttributes.addFlashAttribute("successMessageUsuarioUpdate", "¡Se actualizó correctamente al usuario!");
            
        } else {
        
            redirectAttributes.addFlashAttribute("errorMessageUsuarioUpdateServidor", "Hubo un problema al intentar hacer la actualización de la información");
        
        }

        return "redirect:/usuario/getbyid/" + IdUsuario;
        
    
    }
    
    @PostMapping("agregardireccion/{IdUsuario}")
    public String AgregarDireccion(@PathVariable("IdUsuario") int IdUsuario, @Valid @ModelAttribute("direccion") Direccion direccion, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){
        
        if (bindingResult.hasErrors()) {
            
            Result resultUsuario = usuarioDAOImplementation.GetById(IdUsuario);
            
            model.addAttribute("usuario", resultUsuario.object);
            model.addAttribute("direccion", direccion);
            model.addAttribute("paises", paisDAOImplementation.GetAll().objects);
            model.addAttribute("roles", rolDAOImplementation.GetAll().objects);
            model.addAttribute("errorMessageDireccionAdd", "Hubo un problema al validar la información proporcionada. Revise los datos de la dirección.");
            
            return "UsuarioDetail";
            
        }
        
        Result resultAdd = direccionDAOImplementation.DireccionAdd(direccion, IdUsuario);
        
        if (resultAdd.correct) {
            
            redirectAttributes.addFlashAttribute("successMessageDireccionAdd", "¡Se agregó correctamente la dirección del usuario!");
            
        } else {
        
            redirectAttributes.addFlashAttribute("errorMessaggeServidorDireccionAdd", "Hubo un problema con el servidor. Inténtelo más tarde.");
            
        }
        
        return "redirect:/usuario/getbyid/" + IdUsuario;
    
    }
    
    @PostMapping("searchusuarios")
    public String BuscarUsuarios(@RequestParam(name = "Nombre") String Nombre, @RequestParam(name = "ApellidoPaterno") String ApellidoPaterno, @RequestParam(name = "ApellidoMaterno") String ApellidoMaterno, @RequestParam(name = "Rol") int IdRol,Model model){
    
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("Nombre", Nombre);
        model.addAttribute("ApellidoPaterno", ApellidoPaterno);
        model.addAttribute("ApellidoMaterno", ApellidoMaterno);
        model.addAttribute("roles", rolDAOImplementation.GetAll().objects);
        
        return "Usuario";
    
    }
    
    @PostMapping("actualizarimagen/{IdUsuario}")
    public String ActualizarImagen(@PathVariable("IdUsuario") int IdUsuario, @RequestParam("imagenActualizar") MultipartFile imagen, Model model, RedirectAttributes redirectAttributes) throws IOException {

        Boolean esImagenValida = verificarImagen(imagen);
        Result resultImagen = new Result();

        if (esImagenValida) {

            String imagenConvertida = Base64.getEncoder().encodeToString(imagen.getBytes());
            resultImagen = usuarioDAOImplementation.imagenUpdate(imagenConvertida, IdUsuario);

            if (resultImagen.correct) {

                redirectAttributes.addFlashAttribute("successMessageImagen", "¡Se actualizó la imagen con éxito!");
                return "redirect:/usuario/getbyid/" + IdUsuario;

            } else {

                redirectAttributes.addFlashAttribute("errorMessageImagenServidor", "¡Hubo un error en el servidor al intentar actualizar la imagen!");
                return "redirect:/usuario/getbyid/" + IdUsuario;

            }

        } else {

            resultImagen.correct = false;
            redirectAttributes.addFlashAttribute("errorMessageImagen", "¡Hubo un error al intentar procesar la imagen!");
            return "redirect:/usuario/getbyid/" + IdUsuario;

        }

    }
    
    @PostMapping("formulario")
    public String Formulario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult bindingResult, @RequestParam("imagenFile") MultipartFile imagen, Model model, RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {

            agregarValoresModel(model, usuario);

            return "formulario";

        }

        Boolean esImagenValida = verificarImagen(imagen);

        if (esImagenValida) {

            String imagenConvertida = Base64.getEncoder().encodeToString(imagen.getBytes());
            usuario.setImagen(imagenConvertida);

        }

        Result result = usuarioDAOImplementation.UsuarioDireccionAdd(usuario);

        if (result.correct) {

            redirectAttributes.addFlashAttribute("successMessage", "Se agregó correctamente el usuario.");
            return "redirect:/usuario";

        } else {

            model.addAttribute("errorMessage", "Hubo un error al intentar registrar al usuario: " + result.errorMessage);

            agregarValoresModel(model, usuario);

            return "formulario";

        }

    }
    
    @DeleteMapping("eliminarDireccion/{IdDireccion}")
    @ResponseBody
    public Result EliminarDireccion(@PathVariable("IdDireccion") int IdDireccion) {

        Result direccion = direccionDAOImplementation.GetById(IdDireccion);
        Result resultEliminacion = new Result();

        if (direccion.correct) {

            resultEliminacion = direccionDAOImplementation.DireccionDelete(IdDireccion);

        } else {

            resultEliminacion.correct = false;

        }

        return resultEliminacion;

    }

    @DeleteMapping("eliminarusuario/{IdUsuario}")
    @ResponseBody
    public Result EliminarUsuario(@PathVariable("IdUsuario") int IdUsuario) {

        Result usuario = usuarioDAOImplementation.GetById(IdUsuario);
        Result resultEliminacion = new Result();

        if (usuario.correct) {

            resultEliminacion = usuarioDAOImplementation.Delete(IdUsuario);

        } else {

            resultEliminacion.correct = false;

        }

        return resultEliminacion;

    }
    
    public Boolean verificarImagen(MultipartFile imagen) {

        if (imagen != null) {

            String nombreImagen = imagen.getOriginalFilename();

            if (nombreImagen == null || nombreImagen.trim().isEmpty()) {

                return false;

            }

            int ultimoIndice = nombreImagen.lastIndexOf('.');

            if (ultimoIndice <= 0 || ultimoIndice == nombreImagen.length() - 1) {

                return false;

            }

            String extension = nombreImagen.substring(ultimoIndice + 1).toLowerCase();

            if (extension.equals("jpg") || extension.equals("png") || extension.equals("jpeg")) {

                return true;

            } else {

                return false;

            }

        } else {

            return false;

        }

    }

    public void agregarValoresModel(Model model, Usuario usuario) {

        model.addAttribute("usuario", usuario);
        model.addAttribute("paises", paisDAOImplementation.GetAll().objects);
        model.addAttribute("roles", rolDAOImplementation.GetAll().objects);

        int IdPais = usuario.Direcciones.get(0).Colonia.Municipio.Estado.Pais.getIdPais();

        if (IdPais > 0) {

            model.addAttribute("estados", estadoDAOImplementation.GetByPais(IdPais).objects);

            int IdEstado = usuario.Direcciones.get(0).Colonia.Municipio.Estado.getIdEstado();

            if (IdEstado > 0) {

                model.addAttribute("municipios", municipioDAOImplementation.GetByEstado(IdEstado).objects);

                int IdMunicimipio = usuario.Direcciones.get(0).Colonia.Municipio.getIdMunicipio();

                if (IdMunicimipio > 0) {

                    model.addAttribute("colonias", coloniaDAOImplementation.GetByMunicipio(IdMunicimipio).objects);

                }

            }

        }

    }

}
