package it.univaq.disim.mwt.gymportal.configuration.transaction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;

@Configuration
public class TransactionManagerConfig {


    @Bean("jpatrans")
    public JpaTransactionManager transactionManagerjpaOther(){
        return new JpaTransactionManager();
    }

    @Bean("standardtrans")
    public JpaTransactionManager transactionManagerjpa(){
        return new JpaTransactionManager();
    }


}
