package riwi.com.filtrologistico.dtos.response;


import jakarta.persistence.Entity;
import lombok.*;
import riwi.com.filtrologistico.Utils.enumUser;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private String id;
    private String nombre;
    private String apellido;
    private String email;
    private String tel;
    private enumUser rol;//(enum)
}
