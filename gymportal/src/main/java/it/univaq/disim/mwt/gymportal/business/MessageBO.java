package it.univaq.disim.mwt.gymportal.business;

import org.springframework.stereotype.Service;
import it.univaq.disim.mwt.gymportal.domain.Message;

public interface MessageBO {

    void createMessage (Message msg) throws BusinessException;


}
