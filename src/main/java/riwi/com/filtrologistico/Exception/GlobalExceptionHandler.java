package riwi.com.filtrologistico.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.View;
import riwi.com.filtrologistico.Exception.model.ApiError;
import riwi.com.filtrologistico.Exception.model.ErrorDetails;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // agregar mas excepciones: controlar bien

    private final View error;

    public GlobalExceptionHandler(View error) {
        this.error = error;
    }

    @ExceptionHandler(ApiException.class)

    public ResponseEntity<ApiError> handleApiException(ApiException exception) {

        List<ErrorDetails> errorDetailsList = new ArrayList<>();
        errorDetailsList.add(ErrorDetails.builder()
                .attributeName("path")
                .reason("El id introducido no se encuentra en la base de datos")
                .build());

        ApiError apiError = ApiError.builder()
                .message(exception.getMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .errorDetailList(errorDetailsList)
                .build();

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)

    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

        List<ErrorDetails> errorDetailsList = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {

            String attributeName = ((FieldError) error).getField();
            String reason = error.getDefaultMessage();
            errorDetailsList.add(ErrorDetails.builder().attributeName(attributeName).reason(reason).build());
        });

        ApiError apiError = ApiError.builder()
                .message("Invalid input provided. Please check the details and try again.")
                .httpStatus(HttpStatus.BAD_REQUEST)
                .errorDetailList(errorDetailsList)
                .build();

        return  new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

}
