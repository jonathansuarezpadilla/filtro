package riwi.com.filtrologistico.dtos.response;


import lombok.*;
import riwi.com.filtrologistico.Utils.enumStatus;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class CargaResponse {

    private String id;
    private Float weight;
    private String dimensions;
    private enumStatus status;
}
