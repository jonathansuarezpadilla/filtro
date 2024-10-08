package riwi.com.filtrologistico.Models;


import jakarta.persistence.*;
import lombok.*;
import riwi.com.filtrologistico.Utils.enumUser;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nombre;
    private String apellido;
    private String email;
    private String tel;
    @Enumerated(EnumType.STRING)
    private enumUser rol;//(enum)
    private String password;


    @ManyToMany
    @JoinTable(
            name="User_Carga",
            joinColumns=@JoinColumn(name="User_id"),
            inverseJoinColumns = @JoinColumn(name="Carga_id")
    )
    private List<Carga> cargas;


    @ManyToMany
    @JoinTable(
            name="User_Palet",
            joinColumns=@JoinColumn(name="User_id"),
            inverseJoinColumns = @JoinColumn(name="Palet_id")
    )
    private List<Palet> Palets;
}
