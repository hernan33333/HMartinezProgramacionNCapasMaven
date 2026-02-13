/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alien07.HMartinezProgramacionNCapasMaven.CalculadoraController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 * @author Alien 7
 */
@Controller
@RequestMapping("calculadora")
public class DivisionController {
    
    @GetMapping("division")
    public String Division(@RequestParam double valor1, @RequestParam double valor2, Model model){
    
        model.addAttribute("valor1", valor1);
        model.addAttribute("valor2", valor2);
        
        if (valor2 != 0) {
            double resultado;
            resultado = valor1 / valor2;
            model.addAttribute("resultado", resultado);
        } else {
            
            String resultado = "NO SE PUEDE DIVIDIR SOBRE 0";
            model.addAttribute("resultado", resultado);
        
        }
        
        return "Division";
    
    }
    
}
