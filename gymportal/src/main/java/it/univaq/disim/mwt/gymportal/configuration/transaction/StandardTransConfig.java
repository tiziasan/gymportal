package it.univaq.disim.mwt.gymportal.configuration.transaction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class StandardTransConfig {

    @Bean("standardtrans")
    public JpaTransactionManager transactionManagerjpaOther(){
        return new JpaTransactionManager();
    }

}
