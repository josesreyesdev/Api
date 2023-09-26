package med.jsrdev.api.infra.errors;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import med.jsrdev.api.infra.exceptions.ValidationIntegrity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

// actua como proxy para los controllers, para interceptar las llamadas en caso de exception
@RestControllerAdvice
public class ErrorHandling {

    // Error de registro no existente
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> errorHandling404() {
        return ResponseEntity.notFound().build();
    }

    // Error en validaciones en caso de registrar campos requeridos o no validos
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationErrorData>> errorHandling400(MethodArgumentNotValidException e) {
        var errors = e.getFieldErrors().stream().map(ValidationErrorData::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    public record ValidationErrorData(String field, String error) {
        public ValidationErrorData(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

    // Error en validaciones de integridad
    @ExceptionHandler(ValidationIntegrity.class)
    public ResponseEntity<String> errorHandlerIntegrity403(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    // Error en validaciones de integridad
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> errorHandlerBusinessRules403(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
