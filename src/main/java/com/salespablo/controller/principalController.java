package com.salespablo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class principalController {

    @GetMapping("/administrativo")
    public String acessarPrincipal() {
        return "administrativo/index";
    }
}

