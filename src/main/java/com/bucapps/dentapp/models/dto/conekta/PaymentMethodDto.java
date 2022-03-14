package com.bucapps.dentapp.models.dto.conekta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentMethodDto {
    private String type;
    private String token_id;
}
