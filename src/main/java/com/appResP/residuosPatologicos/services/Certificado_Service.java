package com.appResP.residuosPatologicos.services;

import com.appResP.residuosPatologicos.models.Certificado;
import com.appResP.residuosPatologicos.models.Ticket_control;
import com.appResP.residuosPatologicos.models.Transportista;
import com.appResP.residuosPatologicos.repositories.ICertificado_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class Certificado_Service implements ICertificado_Service{
    @Autowired
     private ICertificado_Repository ICert_rep;


    @Override
    public List<Certificado> getCertificados() {
        List<Certificado> listaCertificados= ICert_rep.findAll();
        return listaCertificados;
    }

    @Override
    public void saveCertificado(Certificado cert) {

    }

    @Override
    public void deleteCertificado(Long id_Cert) {

    }

    @Override
    public Certificado findCertificado(Long id_cert) {
        return null;
    }

    @Override
    public void editCertificado(Long id_Ori, Transportista transportistaNuevo, LocalDate fechaNueva, List<Ticket_control> listaTicketsNueva) {

    }
}
