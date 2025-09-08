package com.jonathan.dto.request;

import lombok.Data;

@Data
public class PhoneRequestDTO {
    private String number;
    private String citycode;
    private String contrycode;
}