package com.erbf.bugsLife.payment.application.web;

import com.erbf.bugsLife.payment.domain.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PaymentDto {

    private Long id;
    private String pg;
    private String payMethod;
    private String merchantUid;
    private int amount;
    private String buyerEmail;
    private String paymentDate;

    public Payment toEntity() {
        return Payment.builder()
                .pg(this.pg)
                .payMethod(this.payMethod)
                .payMethod(this.payMethod)
                .merchantUid(this.merchantUid)
                .amount(this.amount)
                .buyerEmail(this.buyerEmail)
                .build();
    }
}
