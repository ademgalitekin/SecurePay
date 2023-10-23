package com.firisbe.SecurePay.mapper;

import com.firisbe.SecurePay.model.Customer;
import com.firisbe.SecurePay.model.Payment;
import com.firisbe.SecurePay.payload.request.PaymentRequest;
import com.firisbe.SecurePay.payload.response.PaymentResponse;
import com.firisbe.SecurePay.service.CustomerService;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Mapper(componentModel = "spring", uses = CustomerService.class)
public abstract class PaymentMapper {

    @Autowired
    private CustomerService customerService;

    public abstract Payment requestToEntity(PaymentRequest paymentRequest);
    public abstract List<PaymentResponse> entitiesToResponses(List<Payment> payments);

    @AfterMapping
    protected void mapCustomer(PaymentRequest paymentRequest, @MappingTarget Payment payment) {
        if (paymentRequest.getCustomerId() != null) {
            Customer customer = customerService.findById(paymentRequest.getCustomerId());
            payment.setCustomer(customer);
        }
    }
}
