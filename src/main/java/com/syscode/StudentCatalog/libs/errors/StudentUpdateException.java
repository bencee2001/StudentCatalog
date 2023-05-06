package com.syscode.StudentCatalog.libs.errors;


import lombok.Getter;
import org.springframework.validation.BindingResult;

public class StudentUpdateException extends BingingResultException{
    public StudentUpdateException(BindingResult bindingResult){
        super(bindingResult);
    }
}
