package com.appResP.residuosPatologicos.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Tipo_residuo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(unique = true)
    private Long id_tipoResiduo;

    private String nombre_tipoResiduo;
    private boolean estadoActivo;

    public Tipo_residuo() {
    }

    public Tipo_residuo(Long id_tipoResiduo, String nombre_tipoResiduo, boolean estadoActivo) {
        this.id_tipoResiduo = id_tipoResiduo;
        this.nombre_tipoResiduo = nombre_tipoResiduo;
        this.estadoActivo = estadoActivo;
    }

    public Tipo_residuo(String nombre_tipoResiduo, boolean estadoActivo) {
        this.nombre_tipoResiduo = nombre_tipoResiduo;
        this.estadoActivo = estadoActivo;
    }
}
