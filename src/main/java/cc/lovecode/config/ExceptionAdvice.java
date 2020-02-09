package cc.lovecode.config;

import cc.lovecode.dto.ErrorResult;
import cc.lovecode.exception.APIException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(APIException.class)
    public ResponseEntity handleAPIException(APIException ex) {
        return ResponseEntity.status(ex.getCode().getStatus()).body(new ErrorResult(ex.getCode().toString(), ex.getMessage()));
    }
}
