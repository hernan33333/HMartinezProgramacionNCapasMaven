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
import com.alien07.HMartinezProgramacionNCapasMaven.ML.Colonia;
import com.alien07.HMartinezProgramacionNCapasMaven.ML.Direccion;
import com.alien07.HMartinezProgramacionNCapasMaven.ML.ErroresArchivo;
import com.alien07.HMartinezProgramacionNCapasMaven.ML.Usuario;
import com.alien07.HMartinezProgramacionNCapasMaven.ML.Result;
import com.alien07.HMartinezProgramacionNCapasMaven.ML.Rol;
import com.alien07.HMartinezProgramacionNCapasMaven.Service.ValidationService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
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
    @Autowired
    private ValidationService validationService;

    @GetMapping()// localhost:8080/usuario
    public String Index(Model model) {

        Result result = usuarioDAOImplementation.GetAll();

        model.addAttribute("usuarios", result.objects);
        model.addAttribute("roles", rolDAOImplementation.GetAll().objects);
        model.addAttribute("usuarioBusqueda", new Usuario());

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
    
    @GetMapping("{IdUsuario}/actualizardireccion/{IdDireccion}")
    public String ActualizarDireccion(@PathVariable("IdDireccion") int IdDireccion, @PathVariable("IdUsuario") int IdUsuario, Model model){
        
        Direccion direccion = (Direccion) usuarioDAOImplementation.GetDireccionById(IdUsuario, IdDireccion).object;
        
        model.addAttribute("direccion", direccion);
        model.addAttribute("idusuario", IdUsuario);
        Result resultPais = paisDAOImplementation.GetAll();
        model.addAttribute("paises", resultPais.objects);
        model.addAttribute("noMostrarUsuario", true);
        
        return "Formulario";
    
    }
    
    @PostMapping("actualizardireccion/{IdUsuario}/direccion/{IdDireccion}")
    public String ActualizarDireccion(@PathVariable("IdUsuario") int IdUsuario, @PathVariable("IdDireccion") int IdDireccion, @Valid @ModelAttribute("direccion") Direccion direccion, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){
        
        direccion.setIdDireccion(IdDireccion);
        
        if (bindingResult.hasErrors()) {
            
            model.addAttribute("direccion", direccion);
            agregarValoresModel(model, direccion);
            return "Formulario";
            
        }
        
        Result resultActualizacion = usuarioDAOImplementation.ActualizarDireccion(direccion);
        
        if (resultActualizacion.correct) {
            
            redirectAttributes.addFlashAttribute("successMessage", "¡Se actualizó correctamente la dirección!");
            
        } else {
        
            redirectAttributes.addFlashAttribute("errorMessage", resultActualizacion.errorMessage);
        
        }
        
        return "redirect:/usuario/getbyid/" + IdUsuario;
        
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
    public String BuscarUsuarios(@ModelAttribute("usuarioBusqueda") Usuario usuarioBusqueda, Model model, RedirectAttributes redirectAttributes){
    
        Result resultBusqueda = usuarioDAOImplementation.GetByParams(usuarioBusqueda.getNombre(), usuarioBusqueda.getApellidoPaterno(), usuarioBusqueda.getApellidoMaterno(), usuarioBusqueda.Rol.getIdRol());
        
        if (resultBusqueda.correct) {
            
            if (resultBusqueda.objects.isEmpty()) {
                
                redirectAttributes.addFlashAttribute("notFoundMessage", resultBusqueda.errorMessage);
                
                return "redirect:/usuario";
                
            } else {
            
                model.addAttribute("usuarios", resultBusqueda.objects);
                model.addAttribute("usuario", usuarioBusqueda);
                model.addAttribute("roles", rolDAOImplementation.GetAll().objects);
                model.addAttribute("successSearchMessage", "¡Filtros aplicados!");

                return "Usuario";
                
            }
            
        } else {
        
            redirectAttributes.addFlashAttribute("errorMessageServidorSearch", "Hubo un problema con el servidor: " + resultBusqueda.errorMessage + "\nInténtelo más tarde.");
            
            return "redirect:/usuario";
        
        }
    }
    
    @GetMapping("cargamasiva")
    public String CargaMasiva(){
    
        return "CargaMasiva";
    
    }
    
    @GetMapping("cargamasiva/procesar/{session}")
    public String ProcesarCargaMasiva(@PathVariable("session") String strSession, RedirectAttributes redirectAttributes, HttpSession session){
        
        File archivo = new File((String) session.getAttribute(strSession));
        
        List<Usuario> usuarios;
        
        if (archivo.getName().split("\\.")[1].equals(".txt")) {
            
            usuarios = LecturaArchivoTxt(archivo);
            
        } else {
        
            usuarios = LecturaArchivoXLSX(archivo);
            
        }
        
        Result resultAddAll = usuarioDAOImplementation.AddAll(usuarios);
        
        if (resultAddAll.correct) {
            
            redirectAttributes.addFlashAttribute("successMessage", "Usuarios insertados con éxito");
            
        } else {
        
            redirectAttributes.addFlashAttribute("errorMessage", "No se insertaron los usuarios");
        
        }
        
        session.removeAttribute("archivoValidado");
        
        return "redirect:/usuario";
        
    }
    
    @PostMapping("cargamasiva")
    public String CargaMasiva(@RequestParam("archivo") MultipartFile archivo, Model model, RedirectAttributes redirectAttributes, HttpSession session){
        
        if (archivo.isEmpty() || archivo == null) {
            
            model.addAttribute("errorArchivo", "¡Suba un archivo!");
            return "CargaMasiva";
            
        }
        
        try {
            
            Boolean archivoValido = validarArchivo(archivo);
            
            if (!archivoValido) {
                
                model.addAttribute("errorArchivo", "¡El archivo no es un .xlsx o .txt!");
                return "CargaMasiva";
                
            }
            
            String rutaBase = System.getProperty("user.dir");
            String rutaCarpeta = "src/main/resources/archivosCM";
            String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmSS"));
            String nombreArchivo = fecha + archivo.getOriginalFilename();
            String rutaArchivo = rutaBase + "/" + rutaCarpeta + "/" + nombreArchivo;
            String extension = nombreArchivo.substring(nombreArchivo.lastIndexOf('.') + 1).toLowerCase();

            List<Usuario> usuarios;

            archivo.transferTo(new File(rutaArchivo));
            
            if (extension.equals("txt")) {
                        
                usuarios = LecturaArchivoTxt(new File(rutaArchivo));

            } else {

                usuarios = LecturaArchivoXLSX(new File(rutaArchivo));

            }
            
            if (usuarios == null) {
                
                model.addAttribute("errorArchivo", "¡Hubo un problema al trabajar con el archivo!");
                return "CargaMasiva";
                
            }
            
            List<ErroresArchivo> erroresArchivo = ValidarDatos(usuarios);
                    
            if (erroresArchivo.isEmpty()) {
                
                String uuid = UUID.randomUUID().toString();
                
                session.setAttribute(uuid, rutaArchivo);
                model.addAttribute("idSession", uuid);
                
                model.addAttribute("successMessage", "¡Se validaron correctamente los datos!");
                
                return "CargaMasiva";

            } else {

                model.addAttribute("errores", erroresArchivo);
                return "CargaMasiva";

            }
            
        } catch (Exception ex) {
            
            model.addAttribute("errorArchivo", "Hubo un problema con el archivo");
            return "CargaMasiva";
            
        }
        
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
            
            model.addAttribute("usuario", usuario);
            agregarValoresModel(model, usuario.Direcciones.get(0));

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
            
            model.addAttribute("usuario", usuario);
            agregarValoresModel(model, usuario.Direcciones.get(0));

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
    
    public Boolean validarArchivo(MultipartFile archivo){
        
        String nombreOriginal = archivo.getOriginalFilename();
        
        if (nombreOriginal == null || nombreOriginal.trim().isEmpty()) {
            
            return false;
            
        }
        
        int ultimoIndice = nombreOriginal.lastIndexOf('.');

        if (ultimoIndice <= 0 || ultimoIndice == nombreOriginal.length() - 1) {

            return false;

        }
        
        String extension = nombreOriginal.substring(ultimoIndice + 1).toLowerCase();

        if (extension.equals("txt") || extension.equals("xlsx")) {

            return true;

        } else {

            return false;

        }
    
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

    public void agregarValoresModel(Model model, Direccion direccion) {

        model.addAttribute("paises", paisDAOImplementation.GetAll().objects);
        model.addAttribute("roles", rolDAOImplementation.GetAll().objects);

        int IdPais = direccion.Colonia.Municipio.Estado.Pais.getIdPais();

        if (IdPais > 0) {

            model.addAttribute("estados", estadoDAOImplementation.GetByPais(IdPais).objects);

            int IdEstado = direccion.Colonia.Municipio.Estado.getIdEstado();

            if (IdEstado > 0) {

                model.addAttribute("municipios", municipioDAOImplementation.GetByEstado(IdEstado).objects);

                int IdMunicimipio = direccion.Colonia.Municipio.getIdMunicipio();

                if (IdMunicimipio > 0) {

                    model.addAttribute("colonias", coloniaDAOImplementation.GetByMunicipio(IdMunicimipio).objects);

                }

            }

        }

    }

    private List<Usuario> LecturaArchivoTxt(File archivo) {
        
        List<Usuario> usuarios;
        
        try (InputStream inputStream = new FileInputStream(archivo);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))){
                
            usuarios  = new ArrayList<>();
            
            String cadena = "";
            
            while((cadena = bufferedReader.readLine()) != null){
            
                String[] datosUsuario = cadena.split("\\|");
                
                Usuario usuario = new Usuario();
                usuario.Rol = new Rol();
                usuario.Direcciones = new ArrayList<>();
                Direccion direccion = new Direccion();
                usuario.Direcciones.add(direccion);
                usuario.Direcciones.get(0).Colonia = new Colonia();
                
                SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
                
                usuario.setNombre(datosUsuario[0]);
                usuario.setApellidoPaterno(datosUsuario[1]);
                Date fecha = formatoFecha.parse(datosUsuario[2]);
                usuario.setFechaNacimiento(fecha);
                usuario.setUserName(datosUsuario[3]);
                usuario.setApellidoMaterno(datosUsuario[4]);
                usuario.setEmail(datosUsuario[5]);
                usuario.setPassword(datosUsuario[6]);
                usuario.setSexo(datosUsuario[7]);
                usuario.setTelefono(datosUsuario[8]);
                usuario.Rol.setIdRol(Integer.parseInt(datosUsuario[9].toString()));
                usuario.Direcciones.get(0).setCalle(datosUsuario[10]);
                usuario.Direcciones.get(0).setNumeroInterior(datosUsuario[11]);
                usuario.Direcciones.get(0).setNumeroExterior(datosUsuario[12]);
                usuario.Direcciones.get(0).Colonia.setIdColonia(Integer.parseInt(datosUsuario[13].toString()));
                usuario.setImagen(datosUsuario[14]);
                
                usuarios.add(usuario);
            }
        
        } catch (Exception ex) {
        
            return null;
            
        }
        
        return usuarios;
        
    }
    
    public List<ErroresArchivo> ValidarDatos(List<Usuario> usuarios){
    
        List<ErroresArchivo> errores = new ArrayList<>();
        
        int fila = 1;
        
        for (Usuario usuario : usuarios) {
            
            BindingResult bindingResult = validationService.validateObject(usuario);
            
            if (bindingResult.hasErrors()) {
                
                for (ObjectError objectError : bindingResult.getAllErrors()) {
                    
                    ErroresArchivo erroresArchivo = new ErroresArchivo();
                    FieldError campoError = (FieldError) objectError;
                    
                    erroresArchivo.setFila(fila);
                    
                    String[] objetoCampo = campoError.getField().split("\\.");
                    
                    if (objetoCampo.length > 1) {
                        
                        erroresArchivo.setDato(objetoCampo[1]);
                        
                    } else {
                    
                        erroresArchivo.setDato(campoError.getField());
                    
                    }
                    
                    erroresArchivo.setDescripcion("El valor: '" + campoError.getRejectedValue() + "' NO cumple con la(s) condición(es): " + objectError.getDefaultMessage());
                    errores.add(erroresArchivo);
                    
                }
                
            }
            
            fila++;
            
        }
        
        return errores;
    
    }

    private List<Usuario> LecturaArchivoXLSX(File archivo) {
        
        List<Usuario> usuarios;
        
        try (InputStream inputStream = new FileInputStream(archivo);
                XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            
            XSSFSheet sheet = workbook.getSheetAt(0);
            
            usuarios = new ArrayList<>();
            
            for (Row row : sheet) {
                
                Usuario usuario = new Usuario();
                usuario.Rol = new Rol();
                usuario.Direcciones = new ArrayList<>();
                
                Direccion direccion = new Direccion();
                
                usuario.Direcciones.add(direccion);
                usuario.Direcciones.get(0).Colonia = new Colonia();
                
                usuario.setNombre(row.getCell(0).toString());
                usuario.setApellidoPaterno(row.getCell(1).toString());
                usuario.setFechaNacimiento(row.getCell(2).getDateCellValue());
                usuario.setUserName(row.getCell(3).toString());
                usuario.setApellidoMaterno(row.getCell(4).toString());
                usuario.setEmail(row.getCell(5).toString());
                usuario.setPassword(row.getCell(6).toString());
                usuario.setSexo(row.getCell(7).toString());
                usuario.setTelefono(row.getCell(8).toString());
                usuario.Rol.setIdRol(Integer.parseInt(row.getCell(9).toString().split("\\.")[0]));
                usuario.Direcciones.get(0).setCalle(row.getCell(10).toString());
                usuario.Direcciones.get(0).setNumeroInterior(row.getCell(11).toString().split("\\.")[0]);
                usuario.Direcciones.get(0).setNumeroExterior(row.getCell(12).toString().split("\\.")[0]);
                usuario.Direcciones.get(0).Colonia.setIdColonia(Integer.parseInt(row.getCell(13).toString().split("\\.")[0]));
                
                if (row.getCell(14) != null) {
                
                    usuario.setImagen(row.getCell(14).toString());
                    
                } else {
                
                    usuario.setImagen(null);
                
                }
                
                usuarios.add(usuario);
                
            }
            
        } catch (Exception ex) {
            
            return null;
            
        }
        
        return usuarios;
        
    }

}
