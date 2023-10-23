package com.firisbe.SecurePay.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

import static com.firisbe.SecurePay.constant.RegularExpressionConstants.CARD_NUMBER_REGEX;

@Getter
@Setter
@AllArgsConstructor
public class PaymentRequest {

    @Pattern(regexp = CARD_NUMBER_REGEX, message = "Card number must be exactly 16 digits and contain only numbers")
    private String cardNumber;

    @DecimalMin(value = "0", inclusive = false)
    private BigDecimal amount;

    private Long customerId;
}
