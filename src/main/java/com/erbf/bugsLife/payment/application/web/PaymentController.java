package com.erbf.bugsLife.payment.application.web;

import com.erbf.bugsLife.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment/")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/")
    public ResponseEntity<?> paymentCreate(@RequestBody PaymentDto paymentDto) {
        paymentService.paymentCreate(paymentDto);
        return new ResponseEntity<>("success", HttpStatus.OK);

    }

    @GetMapping("/")
    public List<PaymentDto> paymentList() {
        return paymentService.paymentList();
    }
}
