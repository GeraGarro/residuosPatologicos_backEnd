package com.appResP.residuosPatologicos.repositories;

import com.appResP.residuosPatologicos.models.Ticket_control;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITicket_Repository extends JpaRepository<Ticket_control,Long> {
}
