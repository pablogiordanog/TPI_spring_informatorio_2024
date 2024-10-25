package com.informatorio.resetascocina_app.exceptions;

import com.informatorio.resetascocina_app.dto.errores.ErrorGenericDto;
import com.informatorio.resetascocina_app.dto.errores.ErrorsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandle {
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorGenericDto> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
        ErrorGenericDto errorsDto = new ErrorGenericDto("El tipo de parametros es invalido para el endpoint", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorsDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorsDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        var errorList = ex.getFieldErrors().stream()
                .map(fieldError -> {
                    Map<String, String> errorMap = new HashMap<>();
                    errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                    return errorMap;
                }).toList();

        ErrorsDto errorsDto = new ErrorsDto("No se pudo ejecutar el endpoint", errorList);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorsDto);
    }

   @ExceptionHandler(NoSuchElementException.class)
   public ResponseEntity<ErrorGenericDto> handleNoSuchElementException(NoSuchElementException ex, WebRequest webRequest){
       ErrorGenericDto errorsDto = new ErrorGenericDto(ex.getMessage() + ", por favor controle.", webRequest.getDescription(false));

       return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
               .body(errorsDto);
   }


    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorGenericDto> handleNoSuchElementException(NoResourceFoundException ex, WebRequest webRequest){
        ErrorGenericDto errorsDto = new ErrorGenericDto(ex.getMessage() + ", por favor controle.", webRequest.getDescription(false));

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorsDto);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorGenericDto> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest webRequest){
        ErrorGenericDto errorsDto = new ErrorGenericDto(ex.getMessage() + ", por favor controle.", webRequest.getDescription(false));

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorsDto);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorGenericDto> handleException(Exception ex, WebRequest webRequest){
        ErrorGenericDto errorsDto = new ErrorGenericDto(ex.getMessage() + ", por favor controle.", webRequest.getDescription(false));

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorsDto);
    }



}
