package com.appResP.residuosPatologicos.repositories;

import com.appResP.residuosPatologicos.models.Hoja_ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface IHoja_ruta_Repository extends JpaRepository <Hoja_ruta,Long>{



    // Buscar la hoja de ruta con la fecha de fin más reciente
    // Obtener la última hoja de ruta por fechaFin desc
    Optional<Hoja_ruta> findTopByOrderByFechaFinDesc();

    // Otros métodos necesarios
    Optional<Hoja_ruta> findByFechaInicioAndFechaFin(LocalDate fechaInicio, LocalDate fechaFin);

    Optional<Hoja_ruta> findByFechaInicioLessThanEqualAndFechaFinGreaterThanEqual(LocalDate fechaInicio, LocalDate fechaFin);

}
