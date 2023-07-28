package com.appResP.residuosPatologicos.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Residuo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(unique = true)
  private Long id_residuo;
 @OneToOne
 @JoinColumn(name="id_TipoResiduo",referencedColumnName = "id_TipoResiduo")
  private Tipo_residuo t_residuo;

  private float peso;
  @ManyToOne(optional = false)
  @JoinColumn(name = "ticket_control")
  private Ticket_control ticket_control;

    public Residuo() {
    }

    public Residuo(Long id_residuo, Tipo_residuo t_residuo, float peso, Ticket_control ticket_control) {
        this.id_residuo = id_residuo;
        this.t_residuo = t_residuo;
        this.peso = peso;
        this.ticket_control = ticket_control;
    }

    public Residuo(Tipo_residuo t_residuo, float peso, Ticket_control ticket_control) {
        this.t_residuo = t_residuo;
        this.peso = peso;
        this.ticket_control = ticket_control;
    }
}


