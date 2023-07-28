package com.appResP.residuosPatologicos.repositories;

import com.appResP.residuosPatologicos.models.Generador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGenerador_Repository extends JpaRepository<Generador,Long> {
}
