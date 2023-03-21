package com.grupo4.api_alucar.exception.entity;

import java.time.ZonedDateTime;

import lombok.*;

@Getter
@Setter
@Builder
public class DefaultError {
    private String error;
    private Integer status;
    private String detail;
    private ZonedDateTime timestamp;
}
