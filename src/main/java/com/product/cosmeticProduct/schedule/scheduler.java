package com.product.cosmeticProduct.schedule;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@Configuration
public class scheduler {

    @Scheduled(fixedRate = 300000) // Every 5 minutes
    public void keepAlive() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://cosmeticproductbackend.onrender.com";
        try {
            restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            System.out.println("Keep-alive request failed: " + e.getMessage());
        }
    }

}
