package com.salespablo.controller;

import com.salespablo.entity.Fornecedor;
import com.salespablo.repository.CidadeRepository;
import com.salespablo.repository.FornecedorRepository;
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
public class FornecedorController {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @GetMapping("/cadastroFornecedor")
    public ModelAndView cadastrar(Fornecedor fornecedor) {
        ModelAndView mv = new ModelAndView("administrativo/fornecedores/cadastro");
        mv.addObject("fornecedor", fornecedor);
        mv.addObject("listarCidades", cidadeRepository.findAll());
        return mv;
    }

    @GetMapping("/listarFornecedor")
    public ModelAndView listar() {
        List<Fornecedor> fornecedores = fornecedorRepository.findAll();
        ModelAndView mv = new ModelAndView("administrativo/fornecedores/lista");
        mv.addObject("listarFornecedores", fornecedores);
        return mv;
    }

    @GetMapping("/editarFornecedor/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);
        if (fornecedor.isPresent()) {
            return cadastrar(fornecedor.get());
        }
        return listar(); // Retorna a lista caso o fornecedor não seja encontrado
    }

    @PostMapping("/removerFornecedor/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);
        if (fornecedor.isPresent()) {
            fornecedorRepository.delete(fornecedor.get());
        }
        return listar();
    }

    @PostMapping("/salvarFornecedor")
    public ModelAndView salvar(Fornecedor fornecedor, BindingResult result) {
        if (result.hasErrors()) {
            return cadastrar(fornecedor);
        }
        fornecedorRepository.saveAndFlush(fornecedor);
        return cadastrar(new Fornecedor());
    }
}
