package com.syscode.StudentCatalog.libs.errors;

import lombok.Getter;
import org.springframework.validation.BindingResult;

public class StudentCreateException extends BingingResultException{

    public StudentCreateException(BindingResult bindingResult){
        super(bindingResult);
    }
}
