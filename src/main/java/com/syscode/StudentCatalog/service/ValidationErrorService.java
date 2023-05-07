package com.syscode.StudentCatalog.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.Objects;

@Service
public class ValidationErrorService {

    /**
     * Format the errormessage
     * @param bindingResult it has the validation errors
     * @return errormessage
     */
    public String getErrorMessage(BindingResult bindingResult) {
        StringBuilder builder = new StringBuilder();
        for(ObjectError error : bindingResult.getAllErrors()){
            builder.append(Objects.requireNonNull(error.getCodes())[0]);
            builder.append(": ");
            builder.append(error.getDefaultMessage());
            builder.append('\n');
        }
        return builder.toString();
    }
}
