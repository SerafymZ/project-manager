package com.projectmanager.service;

import com.projectmanager.validation.CustomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ValidationServiceImpl implements ValidationService{

    private final static String VALIDATOR_NOT_FOUND_MESSAGE = "No validator that support object with type: %s";

    private final List<CustomValidator> validatorList;

    @Override
    public void validate(Object object) {

        CustomValidator validator = validatorList.stream()
                .filter(v -> v.supports(object.getClass()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format(VALIDATOR_NOT_FOUND_MESSAGE, object.getClass().getName())));

        validator.validate(object);

    }
}