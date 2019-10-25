package sam.biblio.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Ici, on s'assure que l'on ne retourne QUE du JSON.... sans prendre en
     * compte la valeur du paramètre Accept du header de requête.
     * @param configurer
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        //configurer.favorPathExtension(true).
        //        favorParameter(false).
        //        ignoreAcceptHeader(true).
        //        defaultContentType(MediaType.APPLICATION_JSON);
    }


}
