package com.appResP.residuosPatologicos.services;

import com.appResP.residuosPatologicos.models.Tipo_residuo;
import com.appResP.residuosPatologicos.repositories.ITipoResiduo_Repository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
public class TipoResiduo_Service implements ITipoResiduo_Service{
    @Autowired
    private ITipoResiduo_Repository tipoResRepo;

    @Override
    public List<Tipo_residuo> getTipoResiduos() {
        List<Tipo_residuo> listaTipoResiduos=tipoResRepo.findAll();
        return listaTipoResiduos;
    }

    @Override
    public void saveTipoResiduo(Tipo_residuo tipoRe) {
        tipoResRepo.save(tipoRe);
    }

    @Override
    public boolean deleteTipoResiduo(Long id_tipoRes) {
        Optional<Tipo_residuo> tipoResiduoOpt=tipoResRepo.findById(id_tipoRes);
        if(tipoResiduoOpt.isPresent()){
          tipoResRepo.deleteById(id_tipoRes);
          return true;
        }else {
            return false;
        }

    }

    @Override
    public boolean existeTipoResiduo(Long id) {
        return tipoResRepo.existsById(id);
    }

    @Override
    public Tipo_residuo findTipoResiduo(Long id_tipoRes)  {
        Tipo_residuo tipoResiduo=this.tipoResRepo.findById(id_tipoRes).orElse(null);
        return tipoResiduo;
    }

    @Override
    public Tipo_residuo editTipoResiduo(Long id_Ori_tipoRes, String nuevoNombre, boolean nuevoEstado) throws ChangeSetPersister.NotFoundException {
    if(id_Ori_tipoRes==null){
        throw new IllegalArgumentException("El ID no puede ser nulo");
    }
        Tipo_residuo tipoResiduo=tipoResRepo.findById(id_Ori_tipoRes).orElseThrow(()->  new EntityNotFoundException("Tipo residuo no encontrado"));
        tipoResiduo.setNombre_tipoResiduo(nuevoNombre);
        tipoResiduo.setEstadoActivo(nuevoEstado);
        tipoResiduo=tipoResRepo.save(tipoResiduo);

        return tipoResiduo;
    }
}
