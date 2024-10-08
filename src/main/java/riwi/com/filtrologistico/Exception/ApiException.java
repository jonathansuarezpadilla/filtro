package riwi.com.filtrologistico.Exception;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import riwi.com.filtrologistico.Exception.model.ErrorDetails;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ApiException extends RuntimeException{

    private HttpStatus status;
    private List<ErrorDetails> errorDetailList= new ArrayList<>();

    public ApiException(HttpStatus status, String message,List<ErrorDetails> errorDetailList) {
        super(message);
        this.status = status;
        this.errorDetailList = errorDetailList;
    }

    public ApiException(String message) {
        super(message);
    }

}
