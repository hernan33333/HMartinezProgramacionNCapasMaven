/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alien07.HMartinezProgramacionNCapasMaven.FactorialController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Alien 7
 */
@Controller
@RequestMapping("factorial")
public class Factorial {
    
    @GetMapping("/{numero}")
    public String Factorial(@PathVariable("numero") double numero, Model model){
        
        double resultado = 1;
        
        while (numero != 1){
        
            resultado *= numero;
            numero = numero -1 ;
        
        }
        
        model.addAttribute("resultado", resultado);
        return "Factorial";
    
    }
    
}
