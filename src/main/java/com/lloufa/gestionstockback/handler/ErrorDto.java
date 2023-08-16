package com.lloufa.gestionstockback.handler;

import com.lloufa.gestionstockback.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto {

    private Integer httpCode;

    private ErrorCode errorCode;

    private String message;

    private List<String> errors = new ArrayList<>();

}
