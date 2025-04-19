package com.salespablo.controller;

import com.salespablo.entity.Cliente;
import com.salespablo.repository.CidadeRepository;
import com.salespablo.repository.ClienteRepository;
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
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @GetMapping("/cadastroCliente")
    public ModelAndView cadastrar(Cliente cliente) {
        ModelAndView mv = new ModelAndView("administrativo/clientes/cadastro");
        mv.addObject("cliente", cliente);
        mv.addObject("listarCidades", cidadeRepository.findAll());
        return mv;
    }

    @GetMapping("/listarCliente")
    public ModelAndView listar() {
        List<Cliente> clientes = clienteRepository.findAll();
        ModelAndView mv = new ModelAndView("administrativo/clientes/lista");
        mv.addObject("listarClientes", clientes);
        return mv;
    }

    @GetMapping("/editarCliente/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cadastrar(cliente.get());
    }

    @PostMapping("/removerCliente/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        clienteRepository.delete(cliente.get());
        return listar();
    }

    @PostMapping("/salvarCliente")
    public ModelAndView salvar(Cliente cliente, BindingResult result) {
        if(result.hasErrors()) {
            return cadastrar(cliente);
        }
        clienteRepository.saveAndFlush(cliente);
        return cadastrar(new Cliente());
    }
}
