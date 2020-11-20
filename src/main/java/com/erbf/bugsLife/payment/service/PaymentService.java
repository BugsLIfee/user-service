package com.erbf.bugsLife.payment.service;

import com.erbf.bugsLife.payment.application.web.PaymentDto;

public interface PaymentService {
    public abstract void paymentCreate(PaymentDto paymentDto);
}
