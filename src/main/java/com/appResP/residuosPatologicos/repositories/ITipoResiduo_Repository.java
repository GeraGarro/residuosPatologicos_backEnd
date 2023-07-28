package com.appResP.residuosPatologicos.repositories;

import com.appResP.residuosPatologicos.models.Tipo_residuo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoResiduo_Repository extends JpaRepository<Tipo_residuo,Long> {
}
