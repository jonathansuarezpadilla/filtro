package riwi.com.filtrologistico.Controller.GenericMethods;

import org.springframework.http.ResponseEntity;

public interface Create<EntityRequest,Entity> {
    public ResponseEntity<Entity> create(EntityRequest entity);
}
