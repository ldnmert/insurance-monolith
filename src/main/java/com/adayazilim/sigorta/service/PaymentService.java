package com.adayazilim.sigorta.service;

import com.adayazilim.sigorta.entity.Payment;
import com.adayazilim.sigorta.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PolicyService policyService;

    public PaymentService(PaymentRepository paymentRepository, PolicyService policyService) {
        this.paymentRepository = paymentRepository;
        this.policyService = policyService;

    }


    public void changeStatusAndSavePayment(String policyNumber, Payment payment) {

        double amount = policyService.changeStatusAndGetAmount(policyNumber);
        payment.setAmount(amount);
        payment.setPolicyNumber(policyNumber);
        paymentRepository.save(payment);

    }
}
