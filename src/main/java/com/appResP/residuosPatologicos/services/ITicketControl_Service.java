package com.appResP.residuosPatologicos.services;

import com.appResP.residuosPatologicos.models.Residuo;
import com.appResP.residuosPatologicos.models.Ticket_control;
import com.appResP.residuosPatologicos.models.Transportista;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ITicketControl_Service {

    //Listar todos los tickets existentes
    public List<Ticket_control> getTickets();

    //guardar un nuevo Ticket
    public void saveTicket(Ticket_control tk);

    //Eliminar un Ticket de BD
    public boolean deleteTicket(Long id_Ticket);

    //Verificar Si existe el Ticket
    public boolean existeTicket(Long id);
    //Traer un Ticket Existente
    public Optional <Ticket_control> findTicket(Long id_tk);

    //Editar un Ticket
    public Ticket_control editTicket (Long idOri, Transportista transpNuevo, LocalDate fechaNueva, List<Residuo> listaResiduo,boolean nuevoEstado);

}
