package br.com.senai.deliveryavaliacao.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProdutoRequest {

    @NotBlank (message = "Informe o nome do produto")
    private String nome;

    @NotNull (message = "Informe o preço do produto")
    private java.math.BigDecimal preco;
    
    @NotNull (message = "Informe o restaurante")
    private Integer restauranteId;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public java.math.BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(java.math.BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(Integer restauranteId) {
        this.restauranteId = restauranteId;
    }
}
