package com.appResP.residuosPatologicos.dto;

import com.appResP.residuosPatologicos.models.Generador;
import com.appResP.residuosPatologicos.models.Residuo;
import com.appResP.residuosPatologicos.models.Transportista;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Getter @Setter
public class TicketDTO {
    private Transportista transportista;
    private LocalDate fecha;
    private boolean estado;
    private Generador generador;
    private List<Residuo> listaResiduos;
}
