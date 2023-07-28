package com.appResP.residuosPatologicos.services;

import com.appResP.residuosPatologicos.models.Tipo_residuo;
import com.appResP.residuosPatologicos.models.Transportista;
import com.appResP.residuosPatologicos.repositories.ITransportista_Repository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Transportista_service implements ITransportista_Service{
    @Autowired
    private ITransportista_Repository transportista_repo;

    @Override
    public List<Transportista> getTransportistas() {
        List<Transportista> listaTransportistas=transportista_repo.findAll();
        return listaTransportistas;
    }

    @Override
    public void saveTransporista(Transportista transp) {
    transportista_repo.save(transp);
    }

    @Override
    public boolean deleteTransportista(Long id_Transportista) {
        Optional<Transportista> transpOpt=transportista_repo.findById(id_Transportista);
        if(transpOpt.isPresent()){
            transportista_repo.deleteById(id_Transportista);
        return true;
        }else{
            return false;
        }

    }

    @Override
    public Transportista findTransportista(Long id_transportista) {
        Transportista transp=transportista_repo.findById(id_transportista).orElse(null);
        return transp;
    }

    @Override
    public boolean existeTransportista(Long id_transpo) {
        return transportista_repo.existsById(id_transpo);
    }

    @Override
    public Transportista editTransportista(Long id_Ori_transportista, String nuevoNombre, String nuevoApellido, String nuevoCuit, boolean nuevoEstado) {

        if(id_Ori_transportista==null){
            throw new IllegalArgumentException("El ID no puede ser vacio");
        }
        Transportista transp=transportista_repo.findById(id_Ori_transportista).orElseThrow(()-> new EntityNotFoundException("Transportista no encontrado"));
        transp.setNombre_Transp(nuevoNombre);
        transp.setApellido_Transp(nuevoApellido);
        transp.setCuit(nuevoCuit);
        transp.setEstado_Transp(nuevoEstado);
        transportista_repo.save(transp);
        return transp;

}
}
