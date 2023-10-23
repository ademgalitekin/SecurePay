package com.firisbe.SecurePay;

import com.firisbe.SecurePay.model.Customer;
import com.firisbe.SecurePay.model.Payment;
import com.firisbe.SecurePay.repository.PaymentRepository;
import com.firisbe.SecurePay.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class PaymentServiceTest {

    private PaymentService paymentService;

    @MockBean
    private PaymentRepository paymentRepository;

    @BeforeEach
    public void setUp() {
        paymentService = new PaymentService(paymentRepository);
    }

    @Test
    public void testSavePaymentSuccessfully() {
        Payment payment = new Payment();
        payment.setCardNumber("1234567812345678");
        Customer customer1 = new Customer("ademgalitekin", "ademgalitekin760@gmail.com", "password1");
        payment.setCustomer(customer1);

        when(paymentRepository.findFirstByCardNumber(payment.getCardNumber())).thenReturn(Optional.empty());
        when(paymentRepository.save(payment)).thenReturn(payment);

        Payment savedPayment = paymentService.savePayment(payment);

        assertThat(savedPayment).isEqualTo(payment);
    }

    @Test
    public void testFindByCardNumber() {
        String cardNumber = "1234567812345678";
        List<Payment> expectedPayments = new ArrayList<>();
        expectedPayments.add(new Payment());

        when(paymentRepository.findByCardNumber(cardNumber)).thenReturn(expectedPayments);

        List<Payment> resultPayments = paymentService.findByCardNumber(cardNumber);

        assertThat(resultPayments).isEqualTo(expectedPayments);
    }
}
