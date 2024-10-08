package riwi.com.filtrologistico.Service.CRUD;

public interface Create<EntityRequest,Entity> {

    public Entity create(EntityRequest request);
}
