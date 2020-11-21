package com.erbf.bugsLife.payment.service.logic;

import com.erbf.bugsLife.oauth.repository.UserRepository;
import com.erbf.bugsLife.oauth.service.UserService;
import com.erbf.bugsLife.payment.application.web.PaymentDto;
import com.erbf.bugsLife.payment.domain.Payment;
import com.erbf.bugsLife.payment.repository.PaymentRepository;
import com.erbf.bugsLife.payment.service.PaymentService;
import com.erbf.bugsLife.point.application.web.PointDto;
import com.erbf.bugsLife.point.domain.Point;
import com.erbf.bugsLife.point.repository.PointRepository;
import com.erbf.bugsLife.point.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentRepository paymentRepo;

    @Autowired
    PointService pointService;

    @Autowired
    UserService userService;

    @Override
    public void paymentCreate(PaymentDto paymentDto) {

        Payment payment = Payment.builder()
                .pg(paymentDto.getPg())
                .payMethod(paymentDto.getPayMethod())
                .merchantUid(paymentDto.getMerchantUid())
                .amount(paymentDto.getAmount())
                .buyerEmail(paymentDto.getBuyerEmail())
                .paymentDate(getDate())
                .build();

        payment.addUser(userService.selectUser(paymentDto.getUserId()));
        paymentRepo.save(payment);

        pointService.pointCreate(PointDto.builder()
                .amount(paymentDto.getAmount())
                .userId(paymentDto.getUserId())
                .detail("포인트결제")
                .build()
        );
    }

    @Override
    public List<PaymentDto> paymentList() {
        List<Payment> payments = paymentRepo.findAll();
        List<PaymentDto> paymentDtos = new ArrayList<>();
        paymentDtos = payments.stream().map(Payment::toDto).collect(Collectors.toList());
        return paymentDtos;
    }

    private static String getDate() {
        Date date = new Date();
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return transFormat.format(date);
    }
}
