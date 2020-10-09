package it.univaq.disim.mwt.gymportal.configuration.transaction;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ChainedTransactionConfig {
    @Bean("chainedTransactionManager")
    public PlatformTransactionManager chainedTransactionManager
            (@Qualifier("mongotrans") MongoTransactionManager mongoTransactionManager,
            @Qualifier("jpatrans") JpaTransactionManager jpaTransactionManager  ){

        ChainedTransactionManager transactionManager = new ChainedTransactionManager(
                jpaTransactionManager, mongoTransactionManager
        );
        return transactionManager;
    }
}
