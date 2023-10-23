package com.firisbe.SecurePay.repository;

import com.firisbe.SecurePay.model.Customer;
import com.firisbe.SecurePay.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByCustomerAndPaymentDateBetween(Customer customer, LocalDateTime startDate, LocalDateTime endDate);
    Optional<Payment> findFirstByCardNumber(String cardNumber);
    List<Payment> findByCardNumber(String cardNumber);
    List<Payment> findByCustomerId(Long customerId);
}
