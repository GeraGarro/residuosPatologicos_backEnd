package com.appResP.residuosPatologicos.controller;

import com.appResP.residuosPatologicos.dto.ResiduoDTO;
import com.appResP.residuosPatologicos.dto.TicketDTO;
import com.appResP.residuosPatologicos.models.*;
import com.appResP.residuosPatologicos.services.TicketControl_Service;
import com.appResP.residuosPatologicos.services.TipoResiduo_Service;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.*;

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
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/verTodos")
    public ResponseEntity<List<Ticket_control>> getListaTickets(){
    List<Ticket_control> listaTodosTickets=tkService.getTickets();
    if(listaTodosTickets.isEmpty()){
        return ResponseEntity.noContent().build();
    }else{
        return ResponseEntity.ok(listaTodosTickets);
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


  //Crear y Guardar un Ticket en DB
  @PostMapping("/crear")
  public ResponseEntity<String> createTicket(@RequestBody TicketDTO tkNuevo) {
      try {
          // Crear un nuevo Ticket_control
          Ticket_control ticket = new Ticket_control();
          ticket.setTransportista(tkNuevo.getTransportista());
          ticket.setGenerador(tkNuevo.getGenerador());
          ticket.setFechaEmisionTk(tkNuevo.getFecha());
          ticket.setEstadoTicket(tkNuevo.isEstado());

          // Crear y asignar la lista de Residuo
          List<Residuo> listaResiduos = new ArrayList<>();
          for (ResiduoDTO residuoDTO : tkNuevo.getListaResiduos()) {
              Residuo residuo = new Residuo();
              residuo.setT_residuo(residuoDTO.getTipo_residuo());
              residuo.setPeso(residuoDTO.getPeso());
              residuo.setTicket_control(ticket); // Asignar la relación bidireccional
              listaResiduos.add(residuo);
          }

          // Asignar la lista de Residuo al Ticket_control
          ticket.setListaResiduos(listaResiduos);

          // Guardar el Ticket_control en el servicio
          tkService.saveTicket(ticket);

          return ResponseEntity.status(HttpStatus.CREATED).body("Ha sido generado exitosamente el Ticket " + ticket.toString());
      } catch (IllegalArgumentException e) {
          // Aquí puedes imprimir o loguear el mensaje de error para obtener más detalles.
          e.printStackTrace();
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear el Ticket: " + e.getMessage());
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
    ) {
        try {
            Optional<Ticket_control> ticketOpt = tkService.findTicket(id);

            if (!ticketOpt.isPresent()) {
                return ResponseEntity.notFound().build();
            }

            Ticket_control ticket = ticketOpt.get();

            // Actualizar propiedades simples
            if (ticketDTO.getTransportista() != null) {
                ticket.setTransportista(ticketDTO.getTransportista());
            }
            if (ticketDTO.getGenerador() != null) {
                ticket.setGenerador(ticketDTO.getGenerador());
            }

            // Actualizar la lista de residuos
            if (ticketDTO.getListaResiduos() != null) {
                // Limpiar la lista existente
                ticket.getListaResiduos().clear();

                // Agregar los nuevos residuos
                for (ResiduoDTO residuoDTO : ticketDTO.getListaResiduos()) {
                    Residuo residuo = new Residuo();
                    residuo.setTicket_control(ticket); // Asignar el Ticket_control al Residuo
                    // Configurar otras propiedades del residuo
                    ticket.getListaResiduos().add(residuo);
                }
            }

            // Guardar la entidad actualizada
            tkService.saveTicket(ticket);

            return ResponseEntity.ok(ticket);
        } catch (EntityNotFoundException e) {
            String mensajeError = "No se encontró el Ticket con el ID proporcionado";
            ErrorResponse errorResponse = new ErrorResponse(mensajeError);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        } catch (IllegalArgumentException e) {
            String mensajeError = "El ID no puede ser Nulo";
            ErrorResponse errorResponse = new ErrorResponse(mensajeError);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensajeError);
        }
    }
}
