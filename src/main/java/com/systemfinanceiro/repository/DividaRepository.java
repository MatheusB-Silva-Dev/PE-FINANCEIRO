package com.systemfinanceiro.repository;

import com.systemfinanceiro.model.Divida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DividaRepository extends JpaRepository <Divida, Long> {
}
