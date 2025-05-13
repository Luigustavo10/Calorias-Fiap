package br.com.fiap.economiCircular.repository;

import br.com.fiap.economiCircular.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ItemRepository extends JpaRepository<Item, Long> {

    Page<Item> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    Page<Item> findByCategoria_CategoriaId(Long categoriaId, Pageable pageable);

    Page<Item> findByNomeContainingIgnoreCaseAndCategoria_CategoriaId(
            String nome, Long categoriaId, Pageable pageable
    );

}