package com.codecool.risque.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.codecool.risque.service.CategoryService;

@Controller
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = {"/","/index","/home"})
    public String home(Model model){
        model.addAttribute("categories", categoryService.findAll());
        return "home";
    }


}
