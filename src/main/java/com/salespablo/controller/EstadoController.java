package com.salespablo.controller;

import com.salespablo.entity.Estado;
import com.salespablo.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping("/cadastroEstado")
    public ModelAndView cadastrar(Estado estado) {
        ModelAndView mv = new ModelAndView("administrativo/estados/cadastro");
        mv.addObject("estado", estado);
        return mv;
    }

    @GetMapping("/listarEstado")
    public ModelAndView listar() {
        List<Estado> estados = estadoRepository.findAll();
        ModelAndView mv = new ModelAndView("administrativo/estados/lista");
        mv.addObject("listarEstados", estados);
        return mv;
    }

    @GetMapping("/editarEstado/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Estado> estado = estadoRepository.findById(id);
        return cadastrar(estado.get());
    }

    @PostMapping("/removerEstado/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<Estado> estado = estadoRepository.findById(id);
        estadoRepository.delete(estado.get());
        return listar();
    }

    @PostMapping("/salvarEstado")
    public ModelAndView salvar(Estado estado, BindingResult result) {
        if(result.hasErrors()) {
            return cadastrar(estado);
        }
        estadoRepository.saveAndFlush(estado);
        return cadastrar(new Estado());
    }
}
