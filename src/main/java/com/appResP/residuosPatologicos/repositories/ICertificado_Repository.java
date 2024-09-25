package com.appResP.residuosPatologicos.repositories;

import com.appResP.residuosPatologicos.models.Certificado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICertificado_Repository extends CrudRepository<Certificado,Long> {
/*
    Optional<Certificado> findByTransportistaIdAndAñoAndMesId(Long idTransportista, int año, int mesId);
*/
}
