package com.TrainComponent.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("/train")
    public class TrainController {

        @RequestMapping("/hello")
        public String displayHelloWorld()
        {
            return "Hello World";
        }
    }

