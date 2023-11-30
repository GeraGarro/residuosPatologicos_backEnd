package com.appResP.residuosPatologicos.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Transportista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(unique = true)
    private Long id_transportista;
    private String nombre_Transp;
    private String apellido_Transp;
    @Column(name="cuit Transportista", unique = true)
    private String cuit;
    private boolean estado_Transp;

    public Transportista() {
    }

    public Transportista(Long id_transportista, String nombreTransp, String apellidoTransp, String cuit, boolean estadoTransp) {
        this.id_transportista = id_transportista;
        this.nombre_Transp = nombreTransp;
        this.apellido_Transp = apellidoTransp;
        this.cuit = cuit;
        this.estado_Transp = estadoTransp;
    }

    public Transportista(String nombreTransp, String apellidoTransp, String cuit, boolean estadoTransp) {
        this.nombre_Transp = nombreTransp;
        this.apellido_Transp = apellidoTransp;

        this.cuit = cuit;
        this.estado_Transp = estadoTransp;
    }


}
