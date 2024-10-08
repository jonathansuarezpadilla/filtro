package riwi.com.filtrologistico.Models;


import jakarta.persistence.*;
import lombok.*;
import riwi.com.filtrologistico.Utils.enumStatus;


import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class Carga {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Float weight;
    private String dimensions;

    @Enumerated(EnumType.STRING)
    private enumStatus status;


    @ManyToMany(mappedBy = "cargas")
    private List<UserEntity> User_id;


    @ManyToOne
    @JoinColumn(name = "palet_id")
    private Palet palet;
}
