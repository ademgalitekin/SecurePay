package com.firisbe.SecurePay.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class PaymentResponse {
    private String cardNumber;
    private BigDecimal amount;
}