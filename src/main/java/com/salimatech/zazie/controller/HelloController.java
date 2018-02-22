package com.salimatech.zazie.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    /**
     *   Greeter REST controller
     *   Please refer to the following swagger api for more detail on the available
     *   operations and corresponding response status
     *   https://app.swaggerhub.com/apis/nboumaza/springboot/1.0.0#/
     */
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}
