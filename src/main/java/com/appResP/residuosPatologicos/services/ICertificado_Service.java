package com.appResP.residuosPatologicos.services;

import com.appResP.residuosPatologicos.models.Certificado;
import com.appResP.residuosPatologicos.models.Ticket_control;
import com.appResP.residuosPatologicos.models.Transportista;

import java.time.LocalDate;
import java.util.List;

public interface ICertificado_Service {
//Lista todos los certificados de BD
    public List<Certificado> getCertificados();
//Guardar un certificado generado
public void saveCertificado(Certificado cert);

//Eliminar un certificado de la BD
    public void deleteCertificado(Long id_Cert);

    //Traer un certificado existente
    public Certificado findCertificado(Long id_cert);

//Modificar un Certificado

    public void editCertificado(Long id_Ori, Transportista transportistaNuevo, LocalDate fechaNueva, List<Ticket_control> listaTicketsNueva);




}
