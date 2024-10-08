package riwi.com.filtrologistico.dtos.response;


import jakarta.persistence.Entity;
import lombok.*;
import riwi.com.filtrologistico.Utils.enumPalet;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class PaletResponse {

    private String id;
    private Float capacityMax;
    private String location;
    private enumPalet statusPalet;
}
