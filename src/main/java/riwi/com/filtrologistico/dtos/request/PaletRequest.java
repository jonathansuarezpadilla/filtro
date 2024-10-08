package riwi.com.filtrologistico.dtos.request;


import jakarta.validation.constraints.NotNull;
import lombok.*;
import riwi.com.filtrologistico.Utils.enumPalet;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class PaletRequest {

    @NotNull(message = "se requiere cantidad maxima")
    private Float capacityMax;

    @NotNull(message = "se requiere localización")
    private String location;

    @NotNull(message = "se requiere el estado del palet")
    private enumPalet statusPalet;

}
