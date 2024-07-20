package com.adayazilim.sigorta.controller;

import com.adayazilim.sigorta.dto.CreatePaymentDto;
import com.adayazilim.sigorta.entity.Payment;
import com.adayazilim.sigorta.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/enable-policy")
    public ResponseEntity<Void> enablePolicy(@RequestBody CreatePaymentDto createPaymentDto,
                                                @RequestParam String policyNumber) {
       Payment payment = CreatePaymentDto.toPayment(createPaymentDto);
        paymentService.changeStatusAndSavePayment(policyNumber, payment);
        return ResponseEntity.ok().build();
    }


}
