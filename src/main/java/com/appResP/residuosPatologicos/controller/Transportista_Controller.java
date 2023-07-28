package com.appResP.residuosPatologicos.controller;

import com.appResP.residuosPatologicos.models.ErrorResponse;
import com.appResP.residuosPatologicos.models.Transportista;
import com.appResP.residuosPatologicos.services.Transportista_service;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/transportista")
public class Transportista_Controller {

    @Autowired
    private Transportista_service transpoService;

    //Metodo para validar un objeto Transportista

    private boolean transportistaValidacion(Transportista transpVal){
       if(transpVal.getNombre_Transp()==null|| transpVal.getApellido_Transp()==null||transpVal.getCuit()==null){
           return false;
       }
       return true;
    }


//Traer La lista de  todos los Transportistas(Verificado)

    @GetMapping("/verTodos")
    public ResponseEntity<List<Transportista>> getListaTransportistas(){
        List<Transportista> listaTransportista=transpoService.getTransportistas();
        if (listaTransportista.isEmpty()) {
            return ResponseEntity.noContent().build();

        }else{
           return ResponseEntity.ok(listaTransportista);
        }
    }

    //Crear un nuevo Transportista (Verificado)
    @PostMapping("/crear")
    public ResponseEntity<String> createTransportista(@RequestBody Transportista transp){
       if(transportistaValidacion(transp)){
           transpoService.saveTransporista(transp);
           return  ResponseEntity.status(HttpStatus.CREATED).body("Ha sido Incorporado un nuevo Transportista");
       }else{
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos de Entrada no valido");
       }
    }

    //Eliminar un transportista (Verificado)
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> deleteTransportista(@PathVariable Long id){
        boolean condicionParaEliminar=transpoService.deleteTransportista(id);

        if(condicionParaEliminar){
            return ResponseEntity.ok("El transportista Se eliminó");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el Transportista con el ID proporcionado");

        }


    }


    //Obtener un transportista por su ID (validado)

    @GetMapping("/datosTransportista/{id_transportista}")
    public ResponseEntity<Transportista> getTransportista(@PathVariable Long id_transportista){
        Transportista transp=transpoService.findTransportista(id_transportista);
        if(transp !=null){
            return ResponseEntity.ok(transp);

        }else{
            return ResponseEntity.notFound().build();
        }

    }


    //modificar un transportista
    @PutMapping("/transportista/editar/{id}")
    public ResponseEntity <Object> editTransportista(@PathVariable Long id_Ori_transportista,
        @RequestParam(required = false,name="nombre") String nuevoNombre,
        @RequestParam(required = false,name="apellido") String nuevoApellido,
        @RequestParam(required = false,name="cuit") String nuevoCuit,
        @RequestParam(required = false,name="estadoTransp") boolean nuevoEstado){
try{
    Transportista transpEditado=transpoService.editTransportista(id_Ori_transportista,nuevoNombre,nuevoNombre,nuevoCuit,nuevoEstado);
    return ResponseEntity.ok(transpEditado);
}catch (EntityNotFoundException e){
    String mensajeError="No se encontró el Transportista con el ID proporcionado";
    ErrorResponse errorResponse=new ErrorResponse(mensajeError);
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

}catch (Exception e){
    String mensajeError="Error al editar Transportista";
    ErrorResponse errorResponse=new ErrorResponse(mensajeError);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
}

    }



}
