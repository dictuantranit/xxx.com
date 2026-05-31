package com.xxxx.ddd.application.service.ticket;

import com.xxxx.ddd.application.model.TicketDetailDTO;
import com.xxxx.ddd.domain.model.entity.TicketDetail;

public interface TicketDetailAppService {

    TicketDetailDTO getTicketDetailById(Long ticketId, Long version); // should convert to TickDetailDTO by Application ModuleTicketDetail getTicketDetailById(Long ticketId); // should convert to TickDetailDTO by Application Module

    boolean orderTicketByUser(Long ticketId); // order ticket
}
