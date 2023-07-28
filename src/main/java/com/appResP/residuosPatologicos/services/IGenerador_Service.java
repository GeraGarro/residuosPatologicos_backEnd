package com.appResP.residuosPatologicos.services;

import com.appResP.residuosPatologicos.models.Generador;

import java.util.List;

public interface IGenerador_Service {

    //Lista de Todos los Generadores
    public List<Generador> getGeneradores();

    //Guardar un nuevo Generador en BD
    public void saveGenerador(Generador gen);

    //Eliminar un generador de BD
    public boolean deleteGenerador(Long id_Gen);
    //Comprobar si existe el Generador
    public boolean existeGenerador(Long id_Gen);
    //Obtener un generador de BD
    public Generador findGenerador(Long id_Gen);

    //Editar un generador existente en BD
    public Generador editGenerador(Long id_Generador,String nuevoNombre,String nuevoCuit,String direccionNuevo,boolean estadoNuevo);


}
