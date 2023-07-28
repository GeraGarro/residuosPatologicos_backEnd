package com.appResP.residuosPatologicos.repositories;

import com.appResP.residuosPatologicos.models.Transportista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransportista_Repository extends JpaRepository <Transportista,Long> {
}
