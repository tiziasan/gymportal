package it.univaq.disim.mwt.gymportal.business;

import it.univaq.disim.mwt.gymportal.domain.Gym;


public interface CombinedTransactionalBO {

    void deleteGymAndRelatedChats(Gym gym);


}
