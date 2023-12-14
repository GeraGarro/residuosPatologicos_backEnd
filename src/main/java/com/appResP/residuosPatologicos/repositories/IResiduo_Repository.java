package com.appResP.residuosPatologicos.repositories;

import com.appResP.residuosPatologicos.models.Residuo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IResiduo_Repository extends JpaRepository<Residuo,Long> {
    @Query ("SELECT r FROM Residuo r WHERE r.ticket_control.id_Ticket = :idTicket")
    List<Residuo> findByTicketControlId(@Param("idTicket") Long idTicket);
}
