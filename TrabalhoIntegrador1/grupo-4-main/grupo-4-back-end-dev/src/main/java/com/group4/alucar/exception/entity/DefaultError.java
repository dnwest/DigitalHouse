package com.group4.alucar.exception.entity;

import java.time.ZonedDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DefaultError {
    private String error;
    private Integer status;
    private String detail;
    private ZonedDateTime timestamp;
}
