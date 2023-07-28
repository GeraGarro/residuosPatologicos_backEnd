package com.appResP.residuosPatologicos.dto;

import com.appResP.residuosPatologicos.models.Ticket_control;
import com.appResP.residuosPatologicos.models.Tipo_residuo;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResiduoDTO {

  private Tipo_residuo tipoResiduo;
  private float peso;
  private Ticket_control tk;
}
