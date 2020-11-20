package com.erbf.bugsLife.payment.service.logic;

import com.erbf.bugsLife.payment.application.web.PaymentDto;
import com.erbf.bugsLife.payment.domain.Payment;
import com.erbf.bugsLife.payment.repository.PaymentRepository;
import com.erbf.bugsLife.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentRepository paymentRepo;


    @Override
    public void paymentCreate(PaymentDto paymentDto) {
        paymentRepo.save(Payment.builder()
                .pg(paymentDto.getPg())
                .payMethod(paymentDto.getPayMethod())
                .merchantUid(paymentDto.getMerchantUid())
                .amount(paymentDto.getAmount())
                .buyerEmail(paymentDto.getBuyerEmail())
                .paymentDate(getDate())
                .build()
        );
    }

    private static String getDate() {
        Date date = new Date();
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return transFormat.format(date);
    }

}
