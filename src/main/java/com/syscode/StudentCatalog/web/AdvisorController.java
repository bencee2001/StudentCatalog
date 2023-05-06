package com.syscode.StudentCatalog.web;

import com.syscode.StudentCatalog.libs.errors.BingingResultException;
import com.syscode.StudentCatalog.libs.errors.NoSuchStudentException;
import com.syscode.StudentCatalog.libs.errors.StudentCreateException;
import com.syscode.StudentCatalog.libs.errors.StudentUpdateException;
import com.syscode.StudentCatalog.service.ErrorService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@AllArgsConstructor
@Log4j2
public class AdvisorController {

    private final ErrorService errorService;

    @ExceptionHandler({StudentCreateException.class,StudentUpdateException.class})
    public ResponseEntity<String> handleStudentCreateException(BingingResultException ex, WebRequest request){
        String errorMessage =errorService.getErrorMessage(ex.getBindingResult());
        log.error(errorMessage);
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchStudentException.class)
    public ResponseEntity<String> handleNoSuchStudentException(NoSuchStudentException ex, WebRequest request){
        String errorMessage = ex.getMessage();
        log.error(errorMessage);
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

}
