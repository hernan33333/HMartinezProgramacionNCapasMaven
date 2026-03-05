/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alien07.HMartinezProgramacionNCapasMaven.RestController;

import com.alien07.HMartinezProgramacionNCapasMaven.DAO.UsuarioDAOJPAImplementation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alien 7
 */

@RestController
@RequestMapping("api/demo")
public class DemoRestController {
    
    @Autowired
    UsuarioDAOJPAImplementation usuarioDAOJPAImplementation;
    
    @GetMapping
    public String HolaMundo(){
        return "Hola mundo!!!";
    }
    
    @GetMapping("suma")
    public String Suma2Elementos(@RequestParam("valor1") Double valor1, @RequestParam("valor2") Double valor2){
        return "El resultado es: " + (valor1 + valor2);
    }
    
    @PostMapping("suma")
    public String SumaNElementos(@RequestBody List<Double> valores){
        
        Double resultado = 0.0;
        
        for (Double valor : valores) {
            resultado += valor;
        }
        
        return "El resultado es: " + resultado;
    }
    
    @GetMapping("getusuarios")
    public List<Object> ObtenerUsuarios(){
        
        List<Object> usuarios = usuarioDAOJPAImplementation.GetAll().objects;
        
        return usuarios;
        
    }
    
}
