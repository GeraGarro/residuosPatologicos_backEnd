package com.appResP.residuosPatologicos.controller;

import com.appResP.residuosPatologicos.DTO.Hoja_RutaDTO;
import com.appResP.residuosPatologicos.models.Hoja_ruta;
import com.appResP.residuosPatologicos.services.imp.HojaRuta_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/HojaRuta")

public class HojaRuta_controller {

    @Autowired
    HojaRuta_service hoja_service;

    @GetMapping ("/verTodos")
    public Hoja_ruta obtenerTodasLasHojasRuta(LocalDate fecha) {
        return hoja_service.obtenerHojaRutaPorFecha(fecha);
    }

@GetMapping("/actual")
    public ResponseEntity<Map<String, Object>> obtenerHojaRutaActual() {
        Optional<Hoja_ruta> hojaRutaActual = hoja_service.obtenerHojaRutaActual();

        if (hojaRutaActual.isPresent()) {
            Hoja_ruta hojaRuta = hojaRutaActual.get();
            Hoja_RutaDTO dto = new Hoja_RutaDTO(
                    hojaRuta.getId(),
                    hojaRuta.getFechaInicio(),
                    hojaRuta.getFechaFin()
            );

            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "data", dto
            ));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "success", false,
                    "error", "No se encontró una hoja de ruta para la fecha actual"
            ));
        }
    }



    }
