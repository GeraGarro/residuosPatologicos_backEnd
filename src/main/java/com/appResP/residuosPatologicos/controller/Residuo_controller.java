package com.appResP.residuosPatologicos.controller;

import com.appResP.residuosPatologicos.dto.ResiduoDTO;
import com.appResP.residuosPatologicos.models.ErrorResponse;
import com.appResP.residuosPatologicos.models.Residuo;
import com.appResP.residuosPatologicos.models.Ticket_control;
import com.appResP.residuosPatologicos.models.Tipo_residuo;
import com.appResP.residuosPatologicos.services.Residuo_Service;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/Residuo")
public class Residuo_controller {

   @Autowired
   private Residuo_Service resService;

   private boolean ResiduoValidacion (Residuo residuo){
       // Verificar si el objeto residuo es nulo
       if (residuo == null) {
           return false;
       }

       // Validar que el campo id_residuo no sea nulo
       if (residuo.getId_residuo() == null) {
           return false;
       }

       // Validar que el campo t_residuo no sea nulo
       if (residuo.getT_residuo() == null) {
           return false;
       }

       // Validar que el campo peso sea mayor que cero
       if (residuo.getPeso() <= 0) {
           return false;
       }

       // Validar que el campo ticket_control no sea nulo
       if (residuo.getTicket_control() == null) {
           return false;
       }

       // Si todas las validaciones pasan, el residuo es válido
       return true;
   }

   //Traer la Lista de todos los Residuos
    @GetMapping("/verTodos")
    public ResponseEntity<List<Residuo>> getListaResiduos(){
       List<Residuo> listaTodosResiduos=resService.getResiduos();
        if(listaTodosResiduos.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(listaTodosResiduos);
        }

    }

//Crear y Guardar un Residuo en BD
@PostMapping("/crear")
    public ResponseEntity<String> createResiduo(@RequestBody Residuo residuo){
       if(ResiduoValidacion(residuo)){
           resService.saveResiduo(residuo);
           return ResponseEntity.status(HttpStatus.CREATED).body("Ha sido exitosamente creado El residuo");

       }else{
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos de Entrada no Valido");
       }
}

//Eliminar un Residuo en BD(validado)
@DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> deleteResiduo(@PathVariable Long id){
       boolean condicionParaEliminar=resService.deleteResiduo(id);
    if(condicionParaEliminar){
        return ResponseEntity.ok("Ha sido Eliminado Correctamente");
    }else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el Residuo con el ID proporcionado");
    }

   }
    //Obtener Un Residuo de DB

    @GetMapping("unResiduo/{id_residuo}")
    public ResponseEntity<Object> getResiduo(@PathVariable Long id_residuo){
    Optional<Residuo> residuo=resService.findResiduo(id_residuo);
    if(residuo!=null){
        return ResponseEntity.ok(residuo);
    }else{
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el Residuo con el ID proporcionado");
    }
   }

   //Editar un Residuo
    @PutMapping("/editar/{id}")
    public ResponseEntity<Object> editResiduo(
            @PathVariable Long id,
            @RequestBody ResiduoDTO residuoDTO){
       try{
           Optional<Residuo>residuoOpt=resService.findResiduo(id);
           if(!residuoOpt.isPresent()){
               return ResponseEntity.notFound().build();
           }
        Residuo residuo=residuoOpt.get();

        if(residuoDTO.getTipoResiduo()!=null){
            residuo.setT_residuo(residuoDTO.getTipoResiduo());

        }
        if(residuoDTO.getPeso()!=0){
            residuo.setPeso(residuoDTO.getPeso());
        }

        if(residuoDTO.getTk()!=null){
            residuo.setTicket_control(residuoDTO.getTk());
        }
        //Guardar los cambios  del residuo en la base de datos
        resService.saveResiduo(residuo);
        return ResponseEntity.ok(residuo);
       }catch (EntityNotFoundException e){
           String mensajeError="No se encontró el residuo con el ID proporcionado";
           ErrorResponse errorResponse=new ErrorResponse(mensajeError);
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
       }catch (Exception e){
           String mensajeError="Error al editar el Residuo";
           ErrorResponse errorResponse=new ErrorResponse(mensajeError);
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensajeError);
       }
    }

}

