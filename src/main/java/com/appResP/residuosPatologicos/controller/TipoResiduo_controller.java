package com.appResP.residuosPatologicos.controller;

import com.appResP.residuosPatologicos.models.ErrorResponse;
import com.appResP.residuosPatologicos.models.Tipo_residuo;
import com.appResP.residuosPatologicos.services.TipoResiduo_Service;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipoResiduo")
public class TipoResiduo_controller {

    @Autowired
    private TipoResiduo_Service tipoResServ;

    //Metodo para validar un objeto Tipo Residuo
    private boolean tipoResiduoValidacion(Tipo_residuo tipRes) {
        if (tipRes.getNombre_tipoResiduo() == null || tipRes.getNombre_tipoResiduo().trim().isEmpty()) {
            return false;
        }
        return true;
    }

    //Traer La lista de todos los Tipos Residuos(Validado)
    @GetMapping("/verTodos")
    public ResponseEntity<List<Tipo_residuo>> getListaTipoResiduos() {
        List<Tipo_residuo> listaTodosTipoRes=tipoResServ.getTipoResiduos();
        if(listaTodosTipoRes.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(listaTodosTipoRes);
        }

    }

    //Guardar un Tipo de Residuo en BD(Validado)
    @PostMapping("/crear")
    public ResponseEntity<String> createTipRes(@RequestBody Tipo_residuo tipRes) {
        if (tipoResiduoValidacion(tipRes)) {
            tipoResServ.saveTipoResiduo(tipRes);
            return ResponseEntity.status(HttpStatus.CREATED).body("Ha sido exitosamente creado el Tipo De Residuo");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos de Entrada no válidos");
        }

    }

    //Eliminar un Tipo de Residuo en BD(validado)
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> deleteTipRes(@PathVariable Long id) {
        boolean condicionEliminacion = tipoResServ.deleteTipoResiduo(id);

        if (condicionEliminacion) {
            return ResponseEntity.ok("Ha sido Eliminado Exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el Tipo De Residuo con el ID proporcionado");

        }

    }

    //Obtener UN tipo De residuo en DB (verificado)

    @GetMapping("info_unTipoRes/{id_tipoResiduo}")
    public ResponseEntity<Object> getTransportista(@PathVariable Long id_tipoResiduo) {
       Tipo_residuo tipoRe = tipoResServ.findTipoResiduo(id_tipoResiduo);
        if (tipoRe != null) {
            return ResponseEntity.ok(tipoRe);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el Tipo De Residuo con el ID proporcionado");
        }
    }

    //Editar un tipo De residuo
    @PutMapping("/editar/{id}")
    public ResponseEntity<Object> editTipoResiduo
    (@PathVariable Long id,
    @RequestParam(required = false, name = "nuevoNombre") String nuevoNombre,
    @RequestParam(required = false, name = "nuevoEstado") boolean nuevoEstado) {
        try {
            Tipo_residuo tipoRes=tipoResServ.editTipoResiduo(id, nuevoNombre, nuevoEstado);
            return ResponseEntity.ok(tipoRes);
        } catch (EntityNotFoundException e) {
            String mensajeError = "No se encontró el Tipo de Residuo con el ID proporcionado.";
            ErrorResponse errorResponse = new ErrorResponse(mensajeError);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (Exception e) {
            String mensajeError="Error al editar el Tipo de Residuo";
            ErrorResponse errorResponse = new ErrorResponse(mensajeError);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
