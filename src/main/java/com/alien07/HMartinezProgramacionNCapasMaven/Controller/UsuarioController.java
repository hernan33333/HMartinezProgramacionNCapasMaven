/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alien07.HMartinezProgramacionNCapasMaven.Controller;

import com.alien07.HMartinezProgramacionNCapasMaven.DAO.ColoniaDAOImplementation;
import com.alien07.HMartinezProgramacionNCapasMaven.DAO.EstadoDAOImplementation;
import com.alien07.HMartinezProgramacionNCapasMaven.DAO.MunicipioDAOImplementation;
import com.alien07.HMartinezProgramacionNCapasMaven.DAO.PaisDAOImplementation;
import com.alien07.HMartinezProgramacionNCapasMaven.DAO.RolDAOImplementation;
import com.alien07.HMartinezProgramacionNCapasMaven.DAO.UsuarioDAOImplementation;
import com.alien07.HMartinezProgramacionNCapasMaven.ML.Usuario;
import com.alien07.HMartinezProgramacionNCapasMaven.ML.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public String Index(Model model){
        
        Result result = usuarioDAOImplementation.GetAll();
        
        model.addAttribute("usuarios", result.objects);
        
        return "Usuario";
    
    }
    
    @GetMapping("getbyid/{idusuario}")
    public String GetById(@PathVariable("idusuario") int IdUsuario, Model model){
    
        Result resultUsuario = usuarioDAOImplementation.GetById(IdUsuario);
        
        model.addAttribute("usuario", resultUsuario.object);
        
        Result resultPais = paisDAOImplementation.GetAll();
        model.addAttribute("paises", resultPais.objects);
        
        Result resultRol = rolDAOImplementation.GetAll();
        model.addAttribute("roles", resultRol.objects);
        
        return "Formulario";
    
    }
    
    @GetMapping("formulario") // localhost:8080/usuario/formulario
    public String Formulario(Model model){
    
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        
        Result resultPais = paisDAOImplementation.GetAll();
        model.addAttribute("paises", resultPais.objects);
        
        Result resultRol = rolDAOImplementation.GetAll();
        model.addAttribute("roles", resultRol.objects);
        
        return "formulario";
    }
    
    @PostMapping("formulario")
    public String Formulario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){
        
        
//        Result result = usuarioDAOImplementation.UsuarioDireccionAdd(usuario);
//        
//        if (result.correct) {
//            
//            redirectAttributes.addFlashAttribute("successMessage", "Se agregó correctamente la información del usuario.");
//            
//        } else {
//        
//            redirectAttributes.addFlashAttribute("errorMessage", "Hubo un error al intentar registrar al usuario: " + result.errorMessage);
//        
//        }
        
        if (bindingResult.hasErrors()) {
            
            model.addAttribute("usuario", usuario);
            model.addAttribute("paises", paisDAOImplementation.GetAll().objects);
            
            int IdPais = usuario.Direcciones.get(0).Colonia.Municipio.Estado.Pais.getIdPais();
            
            if (IdPais > 0) {
                
                model.addAttribute("estados", estadoDAOImplementation.GetByPais(IdPais));
                
                int IdEstado = usuario.Direcciones.get(0).Colonia.Municipio.Estado.getIdEstado();
                
                if (IdEstado > 0) {
                    
                    model.addAttribute("municipios", municipioDAOImplementation.GetByEstado(IdEstado));
                    
                    int IdMunicimipio = usuario.Direcciones.get(0).Colonia.Municipio.getIdMunicipio();
                    
                    if (IdMunicimipio > 0) {
                        
                        model.addAttribute("colonias", coloniaDAOImplementation.GetByMunicipio(IdMunicimipio));
                        
                    }
                    
                }
                
            }
            return "formulario";
            
        }

        return "redirect:usuario";
    
    }
    
    @GetMapping("getestadosbypais/{IdPais}")
    @ResponseBody
    public Result GetEstadoByPais(@PathVariable("IdPais") int IdPais){
    
        Result resultPais = estadoDAOImplementation.GetByPais(IdPais);
        
        return resultPais;
        
    }
    
    @GetMapping("getmunicipiosbyestado/{IdEstado}")
    @ResponseBody
    public Result GetMunicipiosByEstado(@PathVariable("IdEstado") int IdEstado){
        
        Result result = municipioDAOImplementation.GetByEstado(IdEstado);
        
        return result;
    
    }
    
    @GetMapping("getcoloniasbymunicipio/{IdMunicipio}")
    @ResponseBody
    public Result GetColoniadByMunicipio(@PathVariable("IdMunicipio") int IdMunicipio){
        
        Result result = coloniaDAOImplementation.GetByMunicipio(IdMunicipio);
        
        return result;
    
    }
    
}
