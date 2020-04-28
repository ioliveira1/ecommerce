package com.ioliveira.ecommerce.controllers.exceptions;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class StandardError implements Serializable {
    private Integer status;
    private String message;
    private String path;
    private LocalDateTime timestamp;
}
