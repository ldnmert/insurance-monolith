package com.adayazilim.sigorta.dto;

import com.adayazilim.sigorta.entity.Payment;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreatePaymentDto {


    private String cardNumber;
    private String cvv;



    public static Payment toPayment(CreatePaymentDto dto) {
        Payment payment = new Payment();
        payment.setCardNumber(dto.getCardNumber());
        System.out.println(dto.getCvv());
        payment.setCvv(dto.getCvv());
        return payment;
    }
}
