package br.com.fiap.economiCircular.service;

import br.com.fiap.economiCircular.dto.ItemAtualizacaoDTO;
import br.com.fiap.economiCircular.dto.ItemCadastroDTO;
import br.com.fiap.economiCircular.dto.ItemExibicaoDTO;
import br.com.fiap.economiCircular.model.Categoria;
import br.com.fiap.economiCircular.model.Item;
import br.com.fiap.economiCircular.model.Usuario;
import br.com.fiap.economiCircular.repository.CategoriaRepository;
import br.com.fiap.economiCircular.repository.ItemRepository;
import br.com.fiap.economiCircular.repository.UsuarioRepository;
import br.com.fiap.economiCircular.exception.UsuarioNaoEncontradoException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ItemExibicaoDTO salvar(ItemCadastroDTO itemDTO) {
        Optional<Usuario> usuario = usuarioRepository.findById(1L); // Exemplo: ID fixo ou obtido do contexto
        if (usuario.isEmpty()) throw new UsuarioNaoEncontradoException("Usuário não encontrado!");

        Optional<Categoria> categoria = categoriaRepository.findById(itemDTO.categoriaId());
        if (categoria.isEmpty()) throw new EntityNotFoundException("Categoria não encontrada!");

        Item item = new Item();
        BeanUtils.copyProperties(itemDTO, item);
        item.setUsuario(usuario.get());
        item.setCategoria(categoria.get());

        return new ItemExibicaoDTO(itemRepository.save(item));
    }

    public ItemExibicaoDTO atualizar(Long id, ItemAtualizacaoDTO itemDTO) {
        Optional<Item> itemOpt = itemRepository.findById(id);
        if (itemOpt.isEmpty()) throw new EntityNotFoundException("Item não encontrado!");

        Optional<Categoria> categoria = categoriaRepository.findById(itemDTO.categoriaId());
        if (categoria.isEmpty()) throw new EntityNotFoundException("Categoria não encontrada!");

        Item item = itemOpt.get();
        item.setNome(itemDTO.nome());
        item.setDescricao(itemDTO.descricao());
        item.setDisponivel(itemDTO.disponivel());
        item.setCategoria(categoria.get());

        return new ItemExibicaoDTO(itemRepository.save(item));
    }
    public Page<ItemExibicaoDTO> listarTodos(String nome, Long categoriaId, Pageable pageable) {
        Page<Item> itens;

        if (nome != null && categoriaId != null) {
            itens = itemRepository.findByNomeContainingIgnoreCaseAndCategoria_CategoriaId(
                    nome, categoriaId, pageable
            );
        } else if (nome != null) {
            itens = itemRepository.findByNomeContainingIgnoreCase(nome, pageable);
        } else if (categoriaId != null) {
            itens = itemRepository.findByCategoria_CategoriaId(categoriaId, pageable);
        } else {
            itens = itemRepository.findAll(pageable);
        }

        return itens.map(ItemExibicaoDTO::new); // Converte os itens para o DTO adequado
    }


    public ItemExibicaoDTO buscarPorId(Long id) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isEmpty()) throw new EntityNotFoundException("Item não encontrado!");

        return new ItemExibicaoDTO(item.get());
    }


    public void deletar(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new EntityNotFoundException("Item não encontrado");
        }
        itemRepository.deleteById(id);
    }


}