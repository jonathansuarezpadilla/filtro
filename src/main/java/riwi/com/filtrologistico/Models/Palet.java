package riwi.com.filtrologistico.Models;


import jakarta.persistence.*;
import lombok.*;
import riwi.com.filtrologistico.Utils.enumPalet;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class Palet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Float capacityMax;

    private String location;

    @Enumerated(EnumType.STRING)
    private enumPalet statusPalet;

    @ManyToMany(mappedBy = "Palets")
    private List<UserEntity> users;


    @OneToMany(mappedBy = "palet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Carga> cargas;


}
