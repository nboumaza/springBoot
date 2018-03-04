package com.salimatech.zazie.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    /**
     * Greeter REST controller
     * Please refer to the following swagger api for more detail on the available
     * operations and corresponding response status
     * https://app.swaggerhub.com/apis/nboumaza/springboot/1.0.0#/
     *
     * @return greeting message
     */
    @RequestMapping("/")
    public String home() {
        return "Greetings from Spring Boot!";
    }

}
