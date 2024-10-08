package riwi.com.filtrologistico.Exception.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@Builder
public class ApiError {
    private HttpStatus httpStatus;
    private String message;
    private List<ErrorDetails> errorDetailList;
}
