package br.com.senai.deliveryavaliacao.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senai.deliveryavaliacao.Model.Produto;

@Repository 
public interface ProdutoRepository
    extends JpaRepository<Produto, Integer> {
}
