package riwi.com.filtrologistico.Controller.GenericMethods;

import org.springframework.http.ResponseEntity;
import riwi.com.filtrologistico.Models.Carga;

import java.util.List;

public interface AssingCargaPalet<ID> {
    public ResponseEntity<String> assingCargaPalet(ID id, List<ID> CargasIds);
}
