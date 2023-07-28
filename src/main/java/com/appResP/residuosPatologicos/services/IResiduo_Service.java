package com.appResP.residuosPatologicos.services;

import com.appResP.residuosPatologicos.models.Residuo;
import com.appResP.residuosPatologicos.models.Ticket_control;
import com.appResP.residuosPatologicos.models.Tipo_residuo;

import java.util.List;
import java.util.Optional;

public interface IResiduo_Service {

    //Lista de todos los residuos
    public List<Residuo> getResiduos();

    //Guardar en la base de datos un residuo Generado
    public void saveResiduo(Residuo res);

    //Eliminar de la base de datos un residuo Existente
    public boolean deleteResiduo(Long id);
    //Veridicar si Existe el Residuo
    public boolean existeResiduo(Long id);
    //Traer Un Residuo existente
    public Optional <Residuo> findResiduo(Long id);

    //Editar UN residuo existente
    public Residuo editResiduo(Long id, Tipo_residuo tipoResiduoNuevo, float pesoNuevo, Ticket_control nuevoTicket);
}
