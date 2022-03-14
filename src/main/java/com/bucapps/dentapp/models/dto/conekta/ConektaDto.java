package com.bucapps.dentapp.models.dto.conekta;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ConektaDto {
    private String currency = "mxn";
    private MetadataDto metadata;
    private List<LineItemDto> line_items;
    private CustomerInfoDto customer_info;
    private List<ChargesDto> charges;

}
