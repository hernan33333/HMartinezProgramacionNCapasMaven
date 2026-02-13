/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alien07.HMartinezProgramacionNCapasMaven.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Alien 7
 */
@Controller
@RequestMapping("demo")
public class DemoController {
    
    @GetMapping("saludo")
    public String HolaMundoRequest(@RequestParam String nombre, Model model){
        
        model.addAttribute("nombre", nombre);
        return "HolaMundo";
        
    }
    
    @GetMapping("saludo/{nombre}")
    public String HolaMundoPath(@PathVariable("nombre") String nombre, Model model){
    
        model.addAttribute("nombre", nombre);
        return "HolaMundo";
        
    }
    
}
