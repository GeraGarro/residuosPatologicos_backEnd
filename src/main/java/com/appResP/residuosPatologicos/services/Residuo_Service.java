package com.appResP.residuosPatologicos.services;

import com.appResP.residuosPatologicos.dto.ResiduoDTO;
import com.appResP.residuosPatologicos.models.Residuo;
import com.appResP.residuosPatologicos.models.Ticket_control;
import com.appResP.residuosPatologicos.models.Tipo_residuo;
import com.appResP.residuosPatologicos.repositories.IResiduo_Repository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Residuo_Service implements IResiduo_Service{
    @Autowired
    private IResiduo_Repository residuorepo;

    @Override
    public List<Residuo> getResiduos() {
        List<Residuo> listaResiduos=residuorepo.findAll();
        return listaResiduos;
    }

    @Override
    public List<ResiduoDTO> getResiduosDTObyIdTicket(Long id_Ticket) {
        List<Residuo> residuos=residuorepo.findByTicketControlId(id_Ticket);

        List<ResiduoDTO> residuosDTO = residuos.stream()
                .map(residuo -> mapResiduoToResiduoDTO(residuo))
                .collect(Collectors.toList());

        return residuosDTO;


    }

    private ResiduoDTO mapResiduoToResiduoDTO(Residuo residuo) {
        ResiduoDTO residuoDTO = new ResiduoDTO();
        residuoDTO.setId_residuo(residuo.getId_residuo());
        residuoDTO.setTipo_residuo(residuo.getTipo_residuo());
        residuoDTO.setPeso(residuo.getPeso());
        residuoDTO.setTicket_control(residuo.getTicket_control());
        return residuoDTO;
    }

    @Override
    public void saveResiduo(Residuo res) {
    residuorepo.save(res);
    }

    @Override
    public boolean deleteResiduo(Long id) {
        Optional<Residuo> residuoOpt=residuorepo.findById(id);
        if(residuoOpt.isPresent()){residuorepo.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean existeResiduo(Long id) {
        return residuorepo.existsById(id);
    }

    @Override
    public Optional <Residuo> findResiduo(Long id) {
        return residuorepo.findById(id);
    }

    @Override
    public Residuo editResiduo(Long id, Tipo_residuo tipoResiduoNuevo, float pesoNuevo, Ticket_control nuevoTicket) {

        Residuo residuo=residuorepo.findById(id).orElseThrow(()-> new EntityNotFoundException("Residuo no Encontrado"));
    residuo.setTipo_residuo(tipoResiduoNuevo);
    residuo.setPeso(pesoNuevo);
    residuo.setTicket_control(nuevoTicket);
    this.saveResiduo(residuo);
    return residuo;
    }
}
