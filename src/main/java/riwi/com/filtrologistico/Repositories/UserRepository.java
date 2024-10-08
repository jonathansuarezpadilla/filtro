package riwi.com.filtrologistico.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import riwi.com.filtrologistico.Models.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,String> {

    Optional<UserEntity> findBynombre(String username);

    @Query("select u from  UserEntity u where u.nombre=?1")
    Optional<UserEntity> getName(String username);
}
