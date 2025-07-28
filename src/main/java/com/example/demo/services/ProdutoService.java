package com.example.demo.services;

import com.example.demo.models.Produto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private List<Produto> produtos = new ArrayList<>();
    private long nextId = 1L;
    public List<Produto> listarProduto(){
        return produtos;
    }
    public Optional<Produto> buscarProduto(Long id){
        return produtos.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Produto adicionar(Produto produto){
        produto.setId(nextId++);
        produtos.add(produto);
        return produto;
    }
    public Optional<Produto> atualizar(Long id, Produto novoProduto){
        return buscarProduto(id).map(p -> {
            p.setNome(novoProduto.getNome());
            p.setPreco(novoProduto.getPreco());
            return p;
        });
    }
    public boolean excluir(Long id){
        return produtos.removeIf(p->p.getId().equals(id));
    }
}
