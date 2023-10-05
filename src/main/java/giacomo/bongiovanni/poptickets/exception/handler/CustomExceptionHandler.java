package giacomo.bongiovanni.poptickets.exception.handler;

import giacomo.bongiovanni.poptickets.dto.ErrorMessageDTO;
import giacomo.bongiovanni.poptickets.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(EntityException.class)
    public ResponseEntity<ErrorMessageDTO> userException(EntityException u, WebRequest w){
        Map<String,String> errors = new HashMap<>();
        errors.put(u.getKey(),u.getValue());
        ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO();
        errorMessageDTO.setDate(LocalDateTime.now());
        errorMessageDTO.setMessages(errors);
        errorMessageDTO.setPath(w.getContextPath());
        if(u instanceof EntityNotFoundException) return new ResponseEntity<>(errorMessageDTO, HttpStatus.NOT_FOUND);
        else if(u instanceof EntityDuplicateException)return new ResponseEntity<>(errorMessageDTO, HttpStatus.CONFLICT);
        else return new ResponseEntity<>(errorMessageDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GeneralErrorException.class)
    public ResponseEntity<ErrorMessageDTO> handlerGeneralError(GeneralErrorException g,WebRequest w){
        Map<String,String> errors = new HashMap<>();
        errors.put("id","there was an error with this id = "+g.getId());
        ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO();
        errorMessageDTO.setDate(LocalDateTime.now());
        errorMessageDTO.setMessages(errors);
        errorMessageDTO.setPath(w.getContextPath());
        return new ResponseEntity<>(errorMessageDTO, HttpStatus.BAD_REQUEST);
    }

}
