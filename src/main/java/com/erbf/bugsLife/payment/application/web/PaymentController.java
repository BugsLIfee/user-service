package com.erbf.bugsLife.payment.application.web;

import com.erbf.bugsLife.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment/")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/")
    public void paymentCreate(@RequestBody PaymentDto paymentDto) {
        paymentService.paymentCreate(paymentDto);
    }

}
