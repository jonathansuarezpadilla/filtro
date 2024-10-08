package riwi.com.filtrologistico.Service.CRUD;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AssingCargaUsuario<ID> {
    public void assign(ID id, List<ID> cargasIds);
}
