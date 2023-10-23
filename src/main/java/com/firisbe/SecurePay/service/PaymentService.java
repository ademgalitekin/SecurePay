package com.firisbe.SecurePay.service;

import com.firisbe.SecurePay.exception.CardAlreadyRegisteredException;
import com.firisbe.SecurePay.model.Customer;
import com.firisbe.SecurePay.model.Payment;
import com.firisbe.SecurePay.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public Payment savePayment(Payment payment) {
        Optional<Payment> existingPaymentOpt = paymentRepository.findFirstByCardNumber(payment.getCardNumber());

        if (existingPaymentOpt.isPresent()) {
            Payment existingPayment = existingPaymentOpt.get();
            if (!existingPayment.getCustomer().getId().equals(payment.getCustomer().getId())) {
                throw new CardAlreadyRegisteredException("This card number is already registered for another customer.");
            }
        }
        return paymentRepository.save(payment);
    }

    public List<Payment> findByCardNumber(String cardNumber) {
        return paymentRepository.findByCardNumber(cardNumber);
    }

    public List<Payment> findPaymentsByCustomerAndDateInterval(Customer customer, LocalDateTime startDate, LocalDateTime endDate) {
        return paymentRepository.findByCustomerAndPaymentDateBetween(customer, startDate, endDate);
    }

    public List<Payment> findPaymentsByCustomerId(Long customerId) {
        return paymentRepository.findByCustomerId(customerId);
    }
}
