package com.PaymentComponent.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @RequestMapping("/hello")
    public String displayHelloWorld()
    {
        return "Hello World";
    }

    @RequestMapping("/pay")
    public String displayPayment()
    {
        return "Payment Successful";
    }
}
