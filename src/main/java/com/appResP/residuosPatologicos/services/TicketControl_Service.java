package com.appResP.residuosPatologicos.services;

import com.appResP.residuosPatologicos.models.Residuo;
import com.appResP.residuosPatologicos.models.Ticket_control;
import com.appResP.residuosPatologicos.models.Transportista;
import com.appResP.residuosPatologicos.repositories.ITicket_Repository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Service
public class TicketControl_Service implements ITicketControl_Service{
@Autowired
private ITicket_Repository ticketrepo;

    @Override
    public List<Ticket_control> getTickets() {
        List<Ticket_control> listaTickets= ticketrepo.findAll();
        return listaTickets;
    }

    @Override
    public void saveTicket(Ticket_control tk) {
        try {
            ticketrepo.save(tk);
        } catch (DataIntegrityViolationException e) {
            // Aquí puedes imprimir o loguear el mensaje de error para obtener más detalles.
            e.printStackTrace();
            // También puedes lanzar una excepción personalizada para indicar el error específico.
            throw new IllegalArgumentException("Error al guardar el Ticket: " + e.getMessage());
        }
    }


    @Override
    public boolean deleteTicket(Long id_Ticket) {

        //verificamos que el Ticket existe
        Optional<Ticket_control> tickeOpcional=ticketrepo.findById(id_Ticket);
        if(tickeOpcional.isPresent()){
           ticketrepo.deleteById(id_Ticket);
           return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean existeTicket(Long id) {
        return ticketrepo.existsById(id);
    }

    @Override
    public Optional< Ticket_control> findTicket(Long id_tk) {
        return ticketrepo.findById(id_tk);

    }

    @Override
    public Ticket_control editTicket(Long idOri, Transportista transpNuevo, LocalDate fechaNueva,List<Residuo>ListaResiduo, boolean nuevoEstado) {
        Ticket_control ticketAEditar= ticketrepo.findById(idOri).orElseThrow(()-> new EntityNotFoundException("Ticket No encontrado"));
        ticketAEditar.setTransportista(transpNuevo);
        ticketAEditar.setFechaEmisionTk(fechaNueva);
        ticketAEditar.setListaResiduos(ListaResiduo);
        ticketAEditar.setEstadoTicket(nuevoEstado);
        this.saveTicket(ticketAEditar);
        return ticketAEditar;
    }
}
