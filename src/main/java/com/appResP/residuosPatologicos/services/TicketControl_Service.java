package com.appResP.residuosPatologicos.services;

import com.appResP.residuosPatologicos.dto.ResiduoDTO;
import com.appResP.residuosPatologicos.dto.TicketDTO;
import com.appResP.residuosPatologicos.models.Residuo;
import com.appResP.residuosPatologicos.models.Ticket_control;
import com.appResP.residuosPatologicos.models.Transportista;
import com.appResP.residuosPatologicos.repositories.ITicket_Repository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
@Service
@Component
public class TicketControl_Service implements ITicketControl_Service{
@Autowired
private ITicket_Repository ticketrepo;
    private static final Logger logger = LoggerFactory.getLogger(TicketControl_Service.class);


    @Override
    public List<Ticket_control> getTickets() {
        List<Ticket_control> listaTickets= ticketrepo.findAll();
        return listaTickets;
    }

    @Override
    public void saveTicket(Ticket_control tk) {
        try {
            ticketrepo.save(tk);
            logger.info("Se ha guardado exitosamente el Ticket con ID: {}", tk.getId_Ticket());
        } catch (DataIntegrityViolationException e) {
            logger.error("Error al guardar el Ticket con ID: {}. Detalles del error: {}", tk.getId_Ticket(), e.getMessage());
            // También puedes lanzar una excepción personalizada para indicar el error específico.
            throw new IllegalArgumentException("Error al guardar el Ticket: " + e.getMessage());
        }
    }


    @Override
    public void saveTicket(TicketDTO ticketDTO) {
        try {
            Ticket_control ticketControl = convertirDTOaTicket(ticketDTO);
            ticketrepo.save(ticketControl);
            logger.info("Se ha guardado exitosamente el Ticket con ID: {}", ticketControl.getId_Ticket());
        } catch (DataIntegrityViolationException e) {
            logger.error("Error al guardar el Ticket. Detalles del error: {}", e.getMessage());
            // También puedes lanzar una excepción personalizada para indicar el error específico.
            throw new IllegalArgumentException("Error al guardar el Ticket: " + e.getMessage());
        }
    }



    private List<Residuo> convertirDTOsAResiduos(List<ResiduoDTO> residuoDTOs, Ticket_control ticket) {
        List<Residuo> listaResiduos = new ArrayList<>();

        for (ResiduoDTO residuoDTO : residuoDTOs) {
            Residuo residuo = new Residuo();
            residuo.setTipo_residuo(residuoDTO.getTipo_residuo());
            residuo.setPeso(residuoDTO.getPeso());

            // Manejar tanto TicketDTO como Ticket_control
            if (residuoDTO.getTicket_control() != null) {
                // Convertir TicketDTO a Ticket_control si es necesario
                residuo.setTicket_control(residuoDTO.getTicket_control());
            } else {
                // Establecer directamente Ticket_control si es proporcionado
                residuo.setTicket_control(ticket);
            }

            listaResiduos.add(residuo);
        }

        return listaResiduos;
    }

    private Ticket_control convertirDTOaTicket(TicketDTO ticketDTO) {
        // Implementa la lógica para convertir un TicketDTO a un Ticket_control.
        // Puedes hacer esto manualmente o considerar el uso de bibliotecas como ModelMapper.

        Ticket_control ticket = new Ticket_control();
        ticket.setTransportista(ticketDTO.getTransportista());
        ticket.setGenerador(ticketDTO.getGenerador());
        ticket.setListaResiduos(convertirDTOsAResiduos(ticketDTO.getListaResiduos(), ticket));
        // ... otros ajustes según tu modelo
        return ticket;
    }


    @Override
    public boolean deleteTicket(Long id_Ticket) {

        //verificamos que el Ticket existe
        Optional<Ticket_control> tickeOpcional=ticketrepo.findById(id_Ticket);
        if(tickeOpcional.isPresent()){
           ticketrepo.deleteById(id_Ticket);
            logger.info("Se ha eliminado exitosamente el Ticket con ID: {}", id_Ticket);

            return true;
        }else{
            logger.warn("No se encontró el Ticket con ID: {} para eliminar", id_Ticket);
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
