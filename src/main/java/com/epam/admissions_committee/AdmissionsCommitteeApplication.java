package com.epam.admissions_committee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.http.MediaType;
import org.springframework.plugin.core.OrderAwarePluginRegistry;
import org.springframework.plugin.core.PluginRegistry;

@SpringBootApplication
public class AdmissionsCommitteeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdmissionsCommitteeApplication.class, args);
    }

   @Bean
   public PluginRegistry<LinkDiscoverer, MediaType> discoverers(OrderAwarePluginRegistry<LinkDiscoverer, MediaType> relProviderPluginRegistry) {
       return relProviderPluginRegistry;
   }
}
