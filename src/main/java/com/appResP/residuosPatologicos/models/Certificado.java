package com.appResP.residuosPatologicos.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter
public class Certificado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_certificado;
    @OneToOne
    @JoinColumn(name="id_Transportista",referencedColumnName = "id_transportista")
    private Transportista tr;
    private LocalDate periodo;
    private float peso;
    @OneToMany
    @JoinTable(name="certificado con tickets",
    joinColumns = @JoinColumn(name="id_certficado",unique = true, nullable = false),
    inverseJoinColumns = @JoinColumn(name="id_ticket",nullable = false))
    private List<Ticket_control> listaTickets;


    public Certificado() {
    }

    public Certificado(Long id_certificado, Transportista tr, LocalDate periodo, float peso) {
        this.id_certificado = id_certificado;
        this.tr = tr;
        this.periodo = periodo;
        this.peso = peso;
    }

    public Certificado(Transportista tr, LocalDate periodo, float peso) {
        this.tr = tr;
        this.periodo = periodo;
        this.peso = peso;
    }
}
