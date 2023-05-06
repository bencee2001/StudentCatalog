package com.syscode.StudentCatalog.libs.errors;

import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
public class StudentCreateException extends Exception{

    private final BindingResult bindingResult;

    public StudentCreateException(BindingResult bindingResult){
        super();
        this.bindingResult=bindingResult;
    }
}
