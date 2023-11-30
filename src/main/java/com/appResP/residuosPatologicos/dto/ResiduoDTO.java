package com.appResP.residuosPatologicos.dto;

import com.appResP.residuosPatologicos.models.Residuo;
import com.appResP.residuosPatologicos.models.Ticket_control;
import com.appResP.residuosPatologicos.models.Tipo_residuo;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResiduoDTO {
  private Long id_residuo;
  private Tipo_residuo tipo_residuo;
  private Float peso;
  private Ticket_control tk;
  public ResiduoDTO() {
    // Constructor sin argumentos necesario para la deserialización
  }
}
