package br.com.erickramos.exceptions;

import br.com.erickramos.model.others.ObjetoErro;
import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class ControleExcecoes extends ResponseEntityExceptionHandler {

    private final String SETA = " --> ";

    @ExceptionHandler(AuthenticationException.class)
    public final ResponseEntity<ExceptionResponse> handleAuthenticationExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ExceptionErickRamos.class)
    public ResponseEntity<Object> handleExceptionCustom(ExceptionErickRamos ex) {
        ObjetoErro objetoErro = new ObjetoErro();
        objetoErro.setError(ex.getMessage());
        objetoErro.setCode(HttpStatus.OK.toString());

        return new ResponseEntity<>(objetoErro, HttpStatus.OK);
    }

    @Override
    @ExceptionHandler({Exception.class, RuntimeException.class, Throwable.class})
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        ObjetoErro objetoErro = new ObjetoErro();
        StringBuilder sb = new StringBuilder();

        if(ex instanceof MethodArgumentNotValidException) {
            List<ObjectError> list = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors();
            for (ObjectError objectError : list) {
                sb.append(objectError.getDefaultMessage());
            }
        } else if (ex instanceof HttpMessageNotReadableException) {
            sb.append("JSON mal formatado");
        } else {
            sb.append(ex.getMessage());
        }

        objetoErro.setError(sb.toString());
        objetoErro.setCode(statusCode != null ? statusCode.value() + SETA + statusCode.toString(): HttpStatus.INTERNAL_SERVER_ERROR.value() + SETA + HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        ex.printStackTrace();

        return new ResponseEntity<>(objetoErro, statusCode != null ? statusCode : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({DataIntegrityViolationException.class, ConstraintViolationException.class, PSQLException.class, SQLException.class})
    protected ResponseEntity<Object> handleExceptionDataIntegry(Exception ex) {
        String errorMessage = "";
        ObjetoErro objetoErro = new ObjetoErro();

        if (ex instanceof DataIntegrityViolationException || ex instanceof PSQLException || ex instanceof SQLException || ex instanceof ConstraintViolationException) {
            errorMessage = ex.getCause().getCause().getCause().getMessage();
        } else {
            errorMessage = ex.getMessage();
        }
        ex.printStackTrace();

        objetoErro.setError(errorMessage);
        objetoErro.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());

        return new ResponseEntity<>(objetoErro, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
