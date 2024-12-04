package com.appResP.residuosPatologicos.services.imp;

import com.appResP.residuosPatologicos.models.Hoja_ruta;
import com.appResP.residuosPatologicos.persistence.implementacion.Hoja_rutaDAOimp;
import com.appResP.residuosPatologicos.services.IHojaRuta_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class HojaRuta_service implements IHojaRuta_service {

    @Autowired
    Hoja_rutaDAOimp hoja_rutaImp;

    @Override
    public void crearHojaRutaSemanal() {
        hoja_rutaImp.crearHojaRutaSemanal();
    }

    @Override
    public Hoja_ruta obtenerHojaRutaPorFecha(LocalDate fechaEmision) {
        return hoja_rutaImp.obtenerHojaRutaPorFecha(fechaEmision);
    }

    @Override
    public Optional <Hoja_ruta> findLatestForCurrentWeek() {
        return hoja_rutaImp.findHojaRutaForCurrentDate();
    }

    @Override
    public Optional<Hoja_ruta> findById(Long id) {
        return hoja_rutaImp.findById(id);
    }

    public Optional<Hoja_ruta> obtenerHojaRutaActual() {
        return hoja_rutaImp.findHojaRutaForCurrentDate();
    }

}
