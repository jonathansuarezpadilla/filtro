package riwi.com.filtrologistico.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import riwi.com.filtrologistico.Utils.enumStatus;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class CargaRequest {

    @NotNull(message = "requiere peso")
    @Pattern(regexp = "\\d+(\\.\\d+)?", message = "El peso debe contener solo números")
    private Float weight;

    @NotNull(message = "requiere dimenciones")

    @Pattern(regexp = "^[a-zA-Z\\\\s.,;:!?()]+$", message = "La descripcion solo puede contener letras y signos de puntuación" )
    private String dimensions;

    @NotNull(message = "requiere estado")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "El estado solo puede contener letras")
    private enumStatus status;
}
