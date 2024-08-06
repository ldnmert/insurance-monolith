package com.adayazilim.sigorta.dto;

import com.adayazilim.sigorta.entity.Payment;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreatePaymentDto {


    private String cardNumber;
    private String cvv;

    private String cardHolder;
    private String expiryDate;

    public static Payment toPayment(CreatePaymentDto dto) {
        Payment payment = new Payment();
        payment.setCardNumber(dto.getCardNumber());
        payment.setCardHolder(dto.getCardHolder());
        payment.setExpiryDate(dto.getExpiryDate());
        payment.setCvv(dto.getCvv());
        return payment;
    }
}
