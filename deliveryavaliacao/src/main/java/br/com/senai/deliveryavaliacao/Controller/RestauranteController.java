package br.com.senai.deliveryavaliacao.Controller;

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

import br.com.senai.deliveryavaliacao.Dto.RestauranteRequest;
import br.com.senai.deliveryavaliacao.Model.Restaurante;
import br.com.senai.deliveryavaliacao.Service.RestauranteService;
import jakarta.validation.Valid;

@RestController 
@RequestMapping ("/restaurantes")
public class RestauranteController {

    private final RestauranteService restauranteService;

    public RestauranteController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    @PostMapping
    public ResponseEntity<Restaurante> criarRestaurante(
        @Valid @RequestBody Restaurante restaurante) {

        Restaurante novoRestaurante = restauranteService.Cadastrar(restaurante);

        return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(novoRestaurante);
    }

    @GetMapping
    public ResponseEntity<Iterable<Restaurante>> listarRestaurantes() {

        Iterable<Restaurante> restaurantes = restauranteService.findAll();

        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Restaurante> buscarPorId(
        @PathVariable Integer id) {

        Optional<Restaurante> restauranteOptional = restauranteService.buscarPorId(id);

        if (restauranteOptional.isPresent()) {
            return ResponseEntity.ok(restauranteOptional.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Restaurante> atualizar(
        @PathVariable Integer id,
        @Valid @RequestBody RestauranteRequest restauranteAtualizado) {

        Optional<Restaurante> restauranteOptional = restauranteService.atualizar(id, restauranteAtualizado);

        if (restauranteOptional.isPresent()) { 
            return ResponseEntity.ok(restauranteOptional.get());
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deletar(
        @PathVariable Integer id) {

        boolean deletado = restauranteService.deletar(id);

        if (deletado) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}
