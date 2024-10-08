package riwi.com.filtrologistico.Controller.GenericMethods;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AssignCargaUsuario<ID> {
    public ResponseEntity<String> assign(ID id, List<ID> cargasIds);
}
