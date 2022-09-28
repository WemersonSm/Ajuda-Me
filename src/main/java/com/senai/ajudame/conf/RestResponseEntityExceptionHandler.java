package com.senai.ajudame.conf;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestResponseEntityExceptionHandler  {

    @ExceptionHandler(value = { RuntimeException.class })
    protected ResponseEntity<Object> handle(Exception ex, WebRequest request) {
    	Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getMessage());
        body.put("moment", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZ").format(new Date()));
        body.put("class", ex.getClass());
        
        return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);        
    }
}