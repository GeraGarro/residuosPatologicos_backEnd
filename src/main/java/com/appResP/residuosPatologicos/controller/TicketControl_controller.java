package com.appResP.residuosPatologicos.controller;

import com.appResP.residuosPatologicos.dto.TicketDTO;
import com.appResP.residuosPatologicos.models.ErrorResponse;
import com.appResP.residuosPatologicos.models.Generador;
import com.appResP.residuosPatologicos.models.Ticket_control;
import com.appResP.residuosPatologicos.models.Transportista;
import com.appResP.residuosPatologicos.services.TicketControl_Service;
import com.appResP.residuosPatologicos.services.TipoResiduo_Service;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("api/TicketControl")
public class TicketControl_controller {

@Autowired
    private TicketControl_Service tkService;

private boolean ticketValidacion(Ticket_control tk){
    //Verificamos si existe el ticket
    if(tk ==null){
        return false;
    }

    //Validar que el campo id no sea Nulo
    if(tk.getId_Ticket()==null){
        return false;
    }

    //Validar Que el transportista no sea Nulo
    if(tk.getTransportista()==null){
        return false;
    }

    //Validar que La fecha no este Vacia
    if(tk.getFechaEmisionTk()==null){
        return false;
    }
    //Validar que el Generador no este vacio
    if(tk.getGenerador()==null){
        return false;
    }
    return true;
}

//Traer Lista de todos los Ticket existentes en BD
    @GetMapping("/verTodos")
    public ResponseEntity<List<Ticket_control>> getListaTickets(){
    List<Ticket_control> listaTodosTickets=tkService.getTickets();
    if(listaTodosTickets.isEmpty()){
        return ResponseEntity.noContent().build();
    }else{
        return ResponseEntity.ok(listaTodosTickets);
    }
  }
  //Crear y Guardar un Ticket en DB
    @PostMapping("/crear")
    public ResponseEntity<String> createTicket(@RequestBody Ticket_control tkNuevo) {
        try {
            if (ticketValidacion(tkNuevo)) {
                tkService.saveTicket(tkNuevo);
                return ResponseEntity.status(HttpStatus.CREATED).body("Ha sido generado exitosamente el Ticket");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos de Entrada faltantes o no Validos");
            }
        } catch (IllegalArgumentException e) {
            // Aquí puedes imprimir o loguear el mensaje de error para obtener más detalles.
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear el Ticket: " + e.getMessage());
        }
}
    //Eliminar un Ticket de BD

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable Long id){
        boolean condicionParaEliminar=tkService.deleteTicket(id);
        if(condicionParaEliminar){
            return ResponseEntity.ok("Ha sido Eliminado Correctamente el Ticket");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No ha sido Posible Eliminar el Ticket");
        }
    }

    //Obtener un Ticket de Db
    @GetMapping("unTicket/{id}")
    public ResponseEntity<Object> getTicket(@PathVariable Long id){
    Optional<Ticket_control> tk=tkService.findTicket(id);
    if(tk.isPresent()){
        return ResponseEntity.ok(tk.get());
    }else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el Ticket con el ID proporcionado");
      }
    }
    //Editar un Ticket

    @PutMapping("/editar/{id}")
    public ResponseEntity<Object> editTicket(
            @PathVariable Long id,
            @RequestBody TicketDTO ticketDTO
            ){
    try{
        Optional<Ticket_control> ticketOpt = tkService.findTicket(id);
        if(!ticketOpt.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Ticket_control ticket=ticketOpt.get();
        if(ticketDTO.getTransportista()!=null){
            ticket.setTransportista(ticketDTO.getTransportista());
        }
        if(ticketDTO.getGenerador()!=null){
            ticket.setGenerador(ticketDTO.getGenerador());
        }
        if(ticketDTO.getFecha()!=null){
            ticket.setFechaEmisionTk(ticketDTO.getFecha());
        }
        if(ticketDTO.getListaResiduos()!=null){
            if(ticket.getListaResiduos()==null){
                ticket.setListaResiduos(new ArrayList<>(ticketDTO.getListaResiduos()));
            }else{
                ticket.getListaResiduos().clear();
                ticket.getListaResiduos().addAll(ticketDTO.getListaResiduos());
            }
        }
        //Guardar Los datos en la Base de datos
        tkService.saveTicket(ticket);
        return ResponseEntity.ok(ticket);
    }catch (EntityNotFoundException e){
        String mensajeError="No se encontró el Ticket con el ID proporcionado";
        ErrorResponse errorResponse=new ErrorResponse(mensajeError);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
    }catch (IllegalArgumentException e){
        String mensajeError="El ID no puede ser Nulo";
        ErrorResponse errorResponse=new ErrorResponse(mensajeError);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensajeError);
    }
    }
}
