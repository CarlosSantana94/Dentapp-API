package com.bucapps.dentapp.models.dto.conekta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChargesDto {
    private PaymentMethodDto payment_method;
    private Integer amount;
}
