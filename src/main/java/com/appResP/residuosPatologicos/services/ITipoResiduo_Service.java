package com.appResP.residuosPatologicos.services;

import com.appResP.residuosPatologicos.models.Tipo_residuo;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface ITipoResiduo_Service {
    //Listar Todos los Tipos de Residuos de la BD
    public List<Tipo_residuo> getTipoResiduos();

    //Guardar un TipoResiduo en la BD
    public void saveTipoResiduo(Tipo_residuo tipoRe);

    //Eliminar un tipoResiduo de la BD
    public boolean deleteTipoResiduo(Long id_tipoRes);

    //Verificar Si existe El Tipo Residuo
    public boolean existeTipoResiduo(Long id);
    //Traer un TipoResiduo De la BD
    public Tipo_residuo findTipoResiduo(Long id_tipoRes);



    //Editar un TipoResiduo
    public Tipo_residuo editTipoResiduo(Long id_Ori_tipoRes,String nuevoNombre,boolean nuevoEstado) throws ChangeSetPersister.NotFoundException;
}
