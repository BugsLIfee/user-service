package com.erbf.bugsLife.payment.repository;

import com.erbf.bugsLife.payment.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
