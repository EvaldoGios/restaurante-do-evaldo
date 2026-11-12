package br.com.senai.deliveryavaliacao.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.senai.deliveryavaliacao.Dto.ProdutoRequest;
import br.com.senai.deliveryavaliacao.Model.Produto;
import br.com.senai.deliveryavaliacao.Model.Restaurante;
import br.com.senai.deliveryavaliacao.Repository.ProdutoRepository;
import br.com.senai.deliveryavaliacao.Repository.RestauranteRepository;

@Service 
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final RestauranteRepository restauranteRepository;

    public ProdutoService(ProdutoRepository produtoRepository, RestauranteRepository restauranteRepository) {
        this.produtoRepository = produtoRepository;
        this.restauranteRepository = restauranteRepository;
    }

    public Optional<Produto> cadastrar(ProdutoRequest produtoRequest) {
        Optional<Restaurante> restauranteOptional = restauranteRepository.findById(produtoRequest.getRestauranteId());

        if (restauranteOptional.isEmpty()) {
            return Optional.empty();
        }

        Restaurante restaurante = restauranteOptional.get();

        Produto produto = new Produto();
        produto.setNome(produtoRequest.getNome());
        produto.setPreco(produtoRequest.getPreco());
        produto.setRestaurante(restaurante);

        Produto produtoSalvo = produtoRepository.save(produto);
        return Optional.of(produtoSalvo);
    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(Integer id) {
        return produtoRepository.findById(id);
    }

    public Optional<Produto> atualizar(Integer id, ProdutoRequest produtoRequest) {
        Optional<Produto> produtoExistente = produtoRepository.findById(id);

        if (produtoExistente.isEmpty()) {
            return Optional.empty();
        }

        if (produtoRequest.getRestauranteId() != null) {
            Optional<Restaurante> restauranteOptional = restauranteRepository.findById(produtoRequest.getRestauranteId());
            if (restauranteOptional.isEmpty()) {
                return Optional.empty();
            }
            Produto produto = produtoExistente.get();
            produto.setRestaurante(restauranteOptional.get());
        }

        Produto produto = produtoExistente.get();
        produto.setNome(produtoRequest.getNome());
        produto.setPreco(produtoRequest.getPreco());

        Produto produtoAtualizado = produtoRepository.save(produto);
        return Optional.of(produtoAtualizado);
    }

    public boolean deletar(Integer id) {

        if (produtoRepository.findById(id).isEmpty()) {
            return false;
        }

        produtoRepository.deleteById(id);
        return true;
    }

}
