package com.ioliveira.ecommerce.controllers.exceptions;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class FieldMessage implements Serializable {
    private String fieldName;
    private String message;
}
