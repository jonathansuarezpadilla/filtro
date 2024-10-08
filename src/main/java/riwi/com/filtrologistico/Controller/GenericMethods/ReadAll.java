package riwi.com.filtrologistico.Controller.GenericMethods;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReadAll<EntityResponse> {
    public ResponseEntity<List<EntityResponse>> readAll();
}
