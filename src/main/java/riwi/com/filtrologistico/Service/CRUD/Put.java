package riwi.com.filtrologistico.Service.CRUD;

public interface Put<EntityRequest,ID> {
    public void update(EntityRequest entityRequest,ID id);
}
