package com.syscode.StudentCatalog.libs.errors;

import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
public abstract class BingingResultException extends Exception{

    private final BindingResult bindingResult;

    public BingingResultException(BindingResult bindingResult){
        super();
        this.bindingResult=bindingResult;
    }
}
