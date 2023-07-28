package com.appResP.residuosPatologicos.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Generador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(unique = true)
    private Long id_Generador ;

    private String nombre_generador;
    private String cuit_generador;
    private String direccion_Generador;
    private boolean estado_actividad_generador;

    public Generador() {
    }

    public Generador(Long id_Generador, String nombre_generador, String cuit_generador, String direccion_Generador, boolean estado_actividad_generador) {
        this.id_Generador = id_Generador;
        this.nombre_generador = nombre_generador;
        this.cuit_generador = cuit_generador;
        this.direccion_Generador = direccion_Generador;
        this.estado_actividad_generador = estado_actividad_generador;
    }

    public Generador(String nombre_generador, String cuit_generador, String direccion_Generador, boolean estado_actividad_generador) {
        this.nombre_generador = nombre_generador;
        this.cuit_generador = cuit_generador;
        this.direccion_Generador = direccion_Generador;
        this.estado_actividad_generador = estado_actividad_generador;
    }
}
