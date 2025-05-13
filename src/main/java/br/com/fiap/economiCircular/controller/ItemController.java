package br.com.fiap.economiCircular.controller;

import br.com.fiap.economiCircular.dto.ItemCadastroDTO;
import br.com.fiap.economiCircular.dto.ItemAtualizacaoDTO;
import br.com.fiap.economiCircular.dto.ItemExibicaoDTO;
import br.com.fiap.economiCircular.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/itens")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ItemExibicaoDTO> salvar(
            @RequestBody @Valid ItemCadastroDTO itemDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(itemService.salvar(itemDTO));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ItemExibicaoDTO> listarTodos(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Long categoriaId,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        return itemService.listarTodos(nome, categoriaId, pageable);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemExibicaoDTO> detalhar(@PathVariable Long itemId) {
        return ResponseEntity.ok(itemService.buscarPorId(itemId));
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<ItemExibicaoDTO> atualizar(
            @PathVariable Long itemId,
            @RequestBody @Valid ItemAtualizacaoDTO itemDTO) {

        return ResponseEntity.ok(itemService.atualizar(itemId, itemDTO));
    }

    @DeleteMapping("/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long itemId) {
        itemService.deletar(itemId);
    }
}