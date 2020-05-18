package com.ioliveira.ecommerce.controllers.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CredenciaisDTO {
    private String email;
    private String senha;
}
