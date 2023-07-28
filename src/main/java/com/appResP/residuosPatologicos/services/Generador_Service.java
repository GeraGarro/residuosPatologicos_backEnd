package com.appResP.residuosPatologicos.services;

import com.appResP.residuosPatologicos.models.Generador;
import com.appResP.residuosPatologicos.repositories.IGenerador_Repository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Generador_Service implements IGenerador_Service{
    @Autowired
    private IGenerador_Repository generadorRepo;

    @Override
    public List<Generador> getGeneradores() {
        List<Generador> listaGeneradores= generadorRepo.findAll();
        return listaGeneradores;
    }

    @Override
    public void saveGenerador(Generador gen) {
        generadorRepo.save(gen);
    }

    @Override
    public boolean deleteGenerador(Long id_Gen) {
        Optional<Generador> generadorOpt=generadorRepo.findById(id_Gen);
        if(generadorOpt.isPresent()){
            generadorRepo.deleteById(id_Gen);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean existeGenerador(Long id_Gen) {
        return generadorRepo.existsById(id_Gen);
    }

    @Override
    public Generador findGenerador(Long id_Gen) {
        Generador gen= this.generadorRepo.findById(id_Gen).orElse(null);

        return gen;
    }

    @Override
    public Generador editGenerador(Long id_Generador, String nuevoNombre, String nuevoCuit, String direccionNuevo, boolean estadoNuevo) {
        if(id_Generador==null){
            throw new IllegalArgumentException("El ID no puede ser Nulo");
        }

        Generador gen=generadorRepo.findById(id_Generador).orElseThrow(()-> new EntityNotFoundException("Generador no encontrado"));
    gen.setNombre_generador(nuevoNombre);
    gen.setCuit_generador(nuevoCuit);
    gen.setDireccion_Generador(direccionNuevo);
    gen.setEstado_actividad_generador(estadoNuevo);
    this.saveGenerador(gen);
    return gen;
    }
}
