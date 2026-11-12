package br.com.senai.deliveryavaliacao.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.deliveryavaliacao.Dto.ProdutoRequest;
import br.com.senai.deliveryavaliacao.Model.Produto;
import br.com.senai.deliveryavaliacao.Service.ProdutoService;
import jakarta.validation.Valid;

@RestController 
@RequestMapping ("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;
    
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Produto> criarProduto(
        @Valid @RequestBody ProdutoRequest produto) {

        Optional<Produto> produtoOptional = produtoService.cadastrar(produto);

        if (produtoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(produtoOptional.get());
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {

        List<Produto> produtos = produtoService.listar();

        return ResponseEntity.ok(produtos);
    } 

    @GetMapping ("/{id}")
    public ResponseEntity<Produto> buscarPorId(
        @PathVariable Integer id) {

        Optional<Produto> produtoOptional = produtoService.buscarPorId(id);

        if (produtoOptional.isPresent()) {
            return ResponseEntity.ok(produtoOptional.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Produto> atualizarProduto(
        @PathVariable Integer id,
        @Valid @RequestBody ProdutoRequest produto) {

        Optional<Produto> produtoOptional = produtoService.atualizar(id, produto);

        if (produtoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(produtoOptional.get());
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deletarProduto(
        @PathVariable Integer id) {

        boolean deletado = produtoService.deletar(id);

        if (!deletado) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
