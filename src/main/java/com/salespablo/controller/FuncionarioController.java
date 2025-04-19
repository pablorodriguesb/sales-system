package com.salespablo.controller;

import com.salespablo.entity.Funcionario;
import com.salespablo.repository.FuncionarioRepository;
import com.salespablo.repository.CidadeRepository;
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
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @GetMapping("/cadastroFuncionario")
    public ModelAndView cadastrar(Funcionario funcionario) {
        ModelAndView mv = new ModelAndView("administrativo/funcionarios/cadastro");
        mv.addObject("funcionario", funcionario);
        mv.addObject("listarCidades", cidadeRepository.findAll());
        return mv;
    }

    @GetMapping("/listarFuncionario")
    public ModelAndView listar() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        ModelAndView mv = new ModelAndView("administrativo/funcionarios/lista");
        mv.addObject("listarFuncionarios", funcionarios);
        return mv;
    }

    @GetMapping("/editarFuncionario/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        return cadastrar(funcionario.get());
    }

    @PostMapping("/removerFuncionario/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        funcionarioRepository.delete(funcionario.get());
        return listar();
    }

    @PostMapping("/salvarFuncionario")
    public ModelAndView salvar(Funcionario funcionario, BindingResult result) {
        if(result.hasErrors()) {
            return cadastrar(funcionario);
        }
        funcionarioRepository.saveAndFlush(funcionario);
        return cadastrar(new Funcionario());
    }
}
