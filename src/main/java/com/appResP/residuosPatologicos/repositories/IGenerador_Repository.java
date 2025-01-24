package com.appResP.residuosPatologicos.repositories;

import com.appResP.residuosPatologicos.models.Generador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGenerador_Repository extends CrudRepository<Generador,Long> {

    List<Generador> findByEstadoTrue();
    List<Generador> findByEstadoFalse();
}
