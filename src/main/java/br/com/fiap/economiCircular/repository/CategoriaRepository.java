package br.com.fiap.economiCircular.repository;

import br.com.fiap.economiCircular.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {}