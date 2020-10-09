package it.univaq.disim.mwt.gymportal.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AdditionalResourceWebConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.indexOf("win") >= 0){
            registry.addResourceHandler("/upload/**")
                    .addResourceLocations("file:///C:/Sviluppo/MasterProgetti/gymportal/gymportal/src/main/upload/");
        } else if (os.indexOf("mac") >= 0) {
            registry.addResourceHandler("/upload/**")
                    .addResourceLocations("file://" + System.getProperty("user.dir") + "/src/main/upload/");
        }
    }
}