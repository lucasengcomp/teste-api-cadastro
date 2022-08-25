package com.apicadastro.core.exception.validation;

import com.apicadastro.core.exception.resource.FieldMessage;
import com.apicadastro.domain.cliente.entity.Cliente;
import com.apicadastro.domain.cliente.entity.dto.ClienteDTO;
import com.apicadastro.domain.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.apicadastro.core.consts.Consts.EMAIL_DUPLICADO;


public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClienteRepository repository;

    @Override
    public void initialize(ClienteUpdate ann) {
    }

    @Override
    public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {

        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer urId = Integer.parseInt(map.get("id"));

        List<FieldMessage> fieldMessages = new ArrayList<>();

        verificaSeEmailEstaEmUsoAdicionaNoFieldMessage(urId, fieldMessages, repository.findByEmail(objDto.getEmail()));

        montaFieldMessage(context, fieldMessages);

        return fieldMessages.isEmpty();
    }

    private void montaFieldMessage(ConstraintValidatorContext context, List<FieldMessage> fieldMessages) {
        for (FieldMessage e : fieldMessages) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
    }

    private void verificaSeEmailEstaEmUsoAdicionaNoFieldMessage(Integer urId, List<FieldMessage> list, Cliente emailCliente) {
        if (emailCliente != null && !emailCliente.getId().equals(urId)) {
            list.add(new FieldMessage("email", EMAIL_DUPLICADO));
        }
    }
}
