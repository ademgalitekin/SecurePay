package com.firisbe.SecurePay.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.firisbe.SecurePay.constant.RegularExpressionConstants.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = CARD_NUMBER_REGEX, message = "Card number must be exactly 16 digits and contain only numbers")
    private String cardNumber;

    @DecimalMin(value = "0", inclusive = false)
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private LocalDateTime paymentDate = LocalDateTime.now();
}