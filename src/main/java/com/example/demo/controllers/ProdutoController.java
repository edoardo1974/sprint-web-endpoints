package com.example.demo.controllers;

import com.example.demo.models.Produto;
import com.example.demo.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public List<Produto> listar(){
        return service.listarProduto();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> obter(@PathVariable Long id){
        return service.buscarProduto(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Produto adicionar(@RequestBody Produto produto){
        return service.adicionar(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto produto){
        return service.atualizar(id, produto).map(p -> ResponseEntity.ok(p)).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> exluir(@PathVariable Long id){
        if (service.excluir(id)){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
