package riwi.com.filtrologistico.Controller.GenericMethods;

import org.springframework.http.ResponseEntity;

public interface Delete<Entity,ID> {
    public ResponseEntity<Entity> delete(ID id);
}
