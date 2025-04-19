package com.salespablo.repository;

import com.salespablo.entity.Cidade;
import com.salespablo.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
