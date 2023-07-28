package com.appResP.residuosPatologicos.repositories;

import com.appResP.residuosPatologicos.models.Certificado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICertificado_Repository extends JpaRepository<Certificado,Long> {
}
