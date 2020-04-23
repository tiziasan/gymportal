package it.univaq.disim.mwt.gymportal.business;

import it.univaq.disim.mwt.gymportal.domain.Message;

public interface MessageBO {

    Message createMessage (Message msg) throws BusinessException;


}
