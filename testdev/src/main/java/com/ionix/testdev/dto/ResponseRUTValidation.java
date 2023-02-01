package com.ionix.testdev.dto;

import lombok.Data;

@Data
public class ResponseRUTValidation{
    private int responseCode;
    private String description;
    private Result result;
}

