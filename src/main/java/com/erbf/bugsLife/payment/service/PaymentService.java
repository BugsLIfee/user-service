package com.erbf.bugsLife.payment.service;

import com.erbf.bugsLife.payment.application.web.PaymentDto;

import java.util.List;

public interface PaymentService {
    public abstract void paymentCreate(PaymentDto paymentDto);
    public abstract List<PaymentDto> paymentList();
}
