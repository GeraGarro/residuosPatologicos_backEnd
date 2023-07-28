package com.appResP.residuosPatologicos.repositories;

import com.appResP.residuosPatologicos.models.Residuo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IResiduo_Repository extends JpaRepository<Residuo,Long> {
}
