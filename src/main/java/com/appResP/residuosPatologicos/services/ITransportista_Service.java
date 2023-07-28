package com.appResP.residuosPatologicos.services;



import com.appResP.residuosPatologicos.models.Transportista;

import java.util.List;

public interface ITransportista_Service {

    //Listar Todos los Transportistas de la BD
    public List<Transportista> getTransportistas();

    //Guardar un Transportista en la BD
    public void saveTransporista(Transportista trans);

    //Eliminar un Transportista de la BD
    public boolean deleteTransportista(Long id_Transportista);

    //Traer un Transportista De la BD
    public Transportista findTransportista(Long id_transportista);

    //Comprobar si existe Transportista
    public boolean existeTransportista(Long id_transpo);
    //Editar un Transportista
    public Transportista editTransportista(Long id_Ori_transportista,String nuevoNombre,String nuevoApellido,String nuevoCuit,boolean nuevoEstado);

}
