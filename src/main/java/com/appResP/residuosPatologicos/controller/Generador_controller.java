package com.appResP.residuosPatologicos.controller;

import com.appResP.residuosPatologicos.models.ErrorResponse;
import com.appResP.residuosPatologicos.models.Generador;
import com.appResP.residuosPatologicos.services.Generador_Service;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Generador")

public class Generador_controller{

    @Autowired
    private Generador_Service generador_Serv;

    //Metodo para validar un objeto Generador
    private boolean generadorValidacion(Generador gen){
        if(gen.getNombre_generador()==null || gen.getDireccion_Generador()==null || gen.getCuit_generador()==null ){
            return false;
        }return true;
    }

    //Ver todos los generadores existente en la BD
    @GetMapping("/verTodos")
    public ResponseEntity<List<Generador>> getGeneradores(){
        List<Generador> listaGeneradores= generador_Serv.getGeneradores();
        if(listaGeneradores.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(listaGeneradores);
        }
    }

    //Guardar Un Generador en la BD(Verificado)
    @PostMapping("/crear")
    public ResponseEntity<String> createGenerador(@RequestBody Generador gen){
       if(generadorValidacion(gen)){
           generador_Serv.saveGenerador(gen);
           return ResponseEntity.status(HttpStatus.CREATED).body("Ha sido creado exitosamente EL Generador");
       }else {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos de Entrada no válidos");
       }

    }

    //Eliminar un Generador de la BD(verificado)
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> deleteGenerador(@PathVariable Long id){
        boolean condicionParaEliminar=generador_Serv.deleteGenerador(id);
        if (condicionParaEliminar) {

            return ResponseEntity.ok("Ha sido Exitosamente eliminado el Generador");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el ID del Generador proporcionado");
        }

    }
    //Obtener un Generador de la BD
    @GetMapping("unGenerador/{id}")
    public ResponseEntity<Object> getGenerador(@PathVariable Long id){
        Generador gen=generador_Serv.findGenerador(id);
        if(gen!=null){
        return ResponseEntity.ok(gen);
    }else{
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el Residuo con el ID proporcionado");
        }
    }
    //Editar un tipo de Generador(Verificado)
    @PutMapping("/editar/{id_Generador}")
    public ResponseEntity<Object> editGenerador(@PathVariable Long id_Generador,
                                   @RequestParam(required = false,name = "nuevoNombreGen")String nuevoNombre,
                                    @RequestParam(required = false,name = "nuevoCuitGen")String nuevoCuit,
                                    @RequestParam(required = false,name = "nuevaDireccionGen")String direccionNuevo,
                                   @RequestParam(required = false,name = "nuevoEstadoGen")boolean estadoNuevo){

       try{
           Generador gen=generador_Serv.editGenerador(id_Generador,nuevoNombre,nuevoCuit,direccionNuevo,estadoNuevo);
        return ResponseEntity.ok(gen);
       }catch (EntityNotFoundException e){
           String mensajeError="No se encontró el Generador con el ID proporcionado ";
           ErrorResponse errorResponse=new ErrorResponse(mensajeError);
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
       }catch (Exception e){
           String mensajeError="Error al editar Generador";
           ErrorResponse errorResponse=new ErrorResponse(mensajeError);
       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
       }
       }



}
