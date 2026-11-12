package br.com.senai.deliveryavaliacao.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senai.deliveryavaliacao.Model.Restaurante;

@Repository 
public interface RestauranteRepository
    extends JpaRepository<Restaurante, Integer> {
    
}
