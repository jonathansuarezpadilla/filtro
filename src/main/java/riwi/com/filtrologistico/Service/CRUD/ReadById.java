package riwi.com.filtrologistico.Service.CRUD;

public interface ReadById<ID,EntityResponse> {
    public EntityResponse readById(ID id);
}
