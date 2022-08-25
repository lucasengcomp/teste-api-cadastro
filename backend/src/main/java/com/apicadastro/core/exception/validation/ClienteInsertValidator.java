package com.apicadastro.core.exception.validation;

import com.apicadastro.core.exception.resource.FieldMessage;
import com.apicadastro.domain.cliente.entity.Cliente;
import com.apicadastro.domain.cliente.entity.dto.ClienteNovoDTO;
import com.apicadastro.domain.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

import static com.apicadastro.core.consts.Consts.EMAIL_EM_USO;


public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNovoDTO> {

    @Autowired
    private ClienteRepository repository;

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteNovoDTO dtoNewCliente, ConstraintValidatorContext context) {
        List<FieldMessage> fieldMessages = new ArrayList<>();

        verificaSeEmailENuloAdicionaNoFieldMessage(fieldMessages, repository.findByEmail(dtoNewCliente.getEmail()));
        montaFieldMessage(context, fieldMessages);

        return fieldMessages.isEmpty();
    }

    private void verificaSeEmailENuloAdicionaNoFieldMessage(List<FieldMessage> fieldMessages, Cliente emailCliente) {
        if (emailCliente != null) {
            fieldMessages.add(new FieldMessage("email", EMAIL_EM_USO));
        }
    }

    private void montaFieldMessage(ConstraintValidatorContext context, List<FieldMessage> fieldMessages) {
        for (FieldMessage e : fieldMessages) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
    }
}