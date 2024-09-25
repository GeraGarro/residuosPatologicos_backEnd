package com.appResP.residuosPatologicos.persistence;

import com.appResP.residuosPatologicos.models.Certificado;

import java.util.List;
import java.util.Optional;

public interface ICertificadoDAO {
    Optional<Certificado> findByID(Long Id);
    List<Certificado> findAll();

    void save(Certificado certificado);
    void deletebyID(Long id);
}
