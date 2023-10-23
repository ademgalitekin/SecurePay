package com.firisbe.SecurePay.controller;

import com.firisbe.SecurePay.mapper.PaymentMapper;
import com.firisbe.SecurePay.model.Customer;
import com.firisbe.SecurePay.model.Payment;
import com.firisbe.SecurePay.payload.request.PaymentRequest;
import com.firisbe.SecurePay.payload.response.PaymentResponse;
import com.firisbe.SecurePay.service.CustomerService;
import com.firisbe.SecurePay.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

import static com.firisbe.SecurePay.constant.RestPathConstants.*;

@RestController
@RequestMapping(path = ROOT_PAYMENT_PATH)
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final CustomerService customerService;
    private final PaymentMapper paymentMapper;

    @PostMapping
    public ResponseEntity<Payment> savePayment(@RequestBody PaymentRequest paymentRequest) {
        Payment payment = paymentMapper.requestToEntity(paymentRequest);
        return new ResponseEntity<>(paymentService.savePayment(payment), HttpStatus.CREATED);
    }

    @GetMapping(PATH_SEARCH)
    public ResponseEntity<List<PaymentResponse>> findByCardNumber(@RequestParam String cardNumber) {
        return new ResponseEntity<>(paymentMapper.entitiesToResponses(paymentService.findByCardNumber(cardNumber)), HttpStatus.OK);
    }

    @GetMapping(PATH_CUSTOMER_EMAIL_VARIABLE)
    public ResponseEntity<List<PaymentResponse>> findPaymentsByCustomerEmail(@PathVariable String customerEmail) {
        Customer customer = customerService.findByEmail(customerEmail);
        return new ResponseEntity<>(paymentMapper.entitiesToResponses(paymentService.findPaymentsByCustomerId(customer.getId())), HttpStatus.OK);
    }

    @GetMapping(PATH_CUSTOMER_EMAIL_VARIABLE + PATH_DATE_INTERVAL)
    public ResponseEntity<List<PaymentResponse>> findPaymentsByCustomerAndDateInterval(@PathVariable String customerEmail, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime startDate, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime endDate) {
        Customer customer = customerService.findByEmail(customerEmail);
        return new ResponseEntity<>(paymentMapper.entitiesToResponses(paymentService.findPaymentsByCustomerAndDateInterval(customer, startDate, endDate)), HttpStatus.OK);
    }
}