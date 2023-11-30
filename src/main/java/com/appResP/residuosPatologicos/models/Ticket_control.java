package com.appResP.residuosPatologicos.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter
public class Ticket_control {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(unique = true)
    private Long id_Ticket;
    @OneToOne
    @JoinColumn(name="id_transportista",referencedColumnName = "id_transportista")
    private Transportista transportista;
    private LocalDate fechaEmisionTk;
    private boolean estadoTicket;

    @OneToOne
    @JoinColumn(name="id_Generador", referencedColumnName = "id_Generador")
    private Generador generador;

    @OneToMany(mappedBy = "ticket_control", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    List<Residuo> listaResiduos;

    public Ticket_control() {
    }

    public Ticket_control(Long id_Ticket, Transportista transportista, LocalDate fechaEmisionTk, boolean estadoTicket, Generador generador, List<Residuo> listaResiduos) {
        this.id_Ticket = id_Ticket;
        this.transportista = transportista;
        this.fechaEmisionTk = fechaEmisionTk;
        this.estadoTicket = estadoTicket;
        this.generador = generador;
        this.listaResiduos = listaResiduos;
    }

    public Ticket_control(Transportista transportista, LocalDate fechaEmisionTk, boolean estadoTicket, Generador generador, List<Residuo> listaResiduos) {
        this.transportista = transportista;
        this.fechaEmisionTk = fechaEmisionTk;
        this.estadoTicket = estadoTicket;
        this.generador = generador;
        this.listaResiduos = listaResiduos;
    }

    @Override
    public String toString() {
        return "Ticket_control{" +
                "id_Ticket=" + id_Ticket +
                ", transportista=" + transportista +
                ", fechaEmisionTk=" + fechaEmisionTk +
                ", estadoTicket=" + estadoTicket +
                ", generador=" + generador +
                ", listaResiduos=" + getListaResiduosIds() +
                '}';
    }

    private String getListaResiduosIds() {
        if (listaResiduos == null || listaResiduos.isEmpty()) {
            return "[]";
        }

        StringBuilder ids = new StringBuilder("[");
        for (Residuo residuo : listaResiduos) {
            ids.append(residuo.getId_residuo()).append(", ");
        }
        ids.delete(ids.length() - 2, ids.length());
        ids.append("]");

        return ids.toString();
    }
}
