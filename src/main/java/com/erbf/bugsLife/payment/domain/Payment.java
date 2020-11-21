package com.erbf.bugsLife.payment.domain;

import com.erbf.bugsLife.oauth.model.User;
import com.erbf.bugsLife.payment.application.web.PaymentDto;
import com.nimbusds.openid.connect.sdk.claims.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@SequenceGenerator(
        name = "PAYMENT_SEQ_GEN",
        sequenceName = "payment_seq",
        initialValue = 1,
        allocationSize = 2
)
@Entity
public class Payment {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "PAYMENT_SEQ_GEN"
    )
    private Long id;

    private String pg;
    private String payMethod;
    private String merchantUid;
    private int amount;
    private String buyerEmail;
    private String paymentDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public void addUser(User user) {
        this.user = user;
        user.getPaymentList().add(this);
    }

    public PaymentDto toDto() {
        PaymentDto paymentDto = PaymentDto.builder()
                .id(this.id)
                .pg(this.pg)
                .payMethod(this.payMethod)
                .merchantUid(this.merchantUid)
                .amount(this.amount)
                .buyerEmail(this.buyerEmail)
                .paymentDate(this.paymentDate)
                .build();
        return paymentDto;
    }
}
