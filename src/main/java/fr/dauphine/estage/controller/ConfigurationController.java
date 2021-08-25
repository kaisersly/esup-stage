package fr.dauphine.estage.controller;

import fr.dauphine.estage.bootstrap.ApplicationBootstrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ApiController
public class ConfigurationController {

    @Autowired
    private ApplicationBootstrap applicationBootstrap;

    @GetMapping("/version")
    public String testApp() {
        return applicationBootstrap.getApplicationVersion();
    }
}