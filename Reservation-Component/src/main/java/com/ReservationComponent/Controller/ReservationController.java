package com.ReservationComponent.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @RequestMapping("/hello")
    public String displayHelloWorld()
    {
        return "Hello World";
    }
}
