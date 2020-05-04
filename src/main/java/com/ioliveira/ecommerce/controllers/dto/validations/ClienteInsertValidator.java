package com.ioliveira.ecommerce.controllers.dto.validations;

import com.ioliveira.ecommerce.controllers.dto.request.ClienteInsertDTO;
import com.ioliveira.ecommerce.controllers.dto.validations.utils.ValidateDocument;
import com.ioliveira.ecommerce.controllers.exceptions.FieldMessage;
import com.ioliveira.ecommerce.entities.enums.TipoCliente;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteInsertDTO> {
    @Override
    public void initialize(ClienteInsert constraintAnnotation) {

    }

    @Override
    public boolean isValid(ClienteInsertDTO insertDTO, ConstraintValidatorContext context) {

        List<FieldMessage> error = new ArrayList<>();

        if (insertDTO.getTipoCliente().equals(TipoCliente.PESSOA_FISICA.getCodigo())) {
            if (!ValidateDocument.isValidCpf(insertDTO.getCpfCnpj())) {
                error.add(new FieldMessage("cpfCnpj", "CPF inválido."));
            }
        } else {
            if (!ValidateDocument.isValidCnpj(insertDTO.getCpfCnpj())) {
                error.add(new FieldMessage("cpfCnpj", "CNPJ inválido."));
            }
        }

        error.forEach(erro -> {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(erro.getMessage()).addPropertyNode(erro.getFieldName())
                    .addConstraintViolation();
        });

        return error.isEmpty();
    }
}
