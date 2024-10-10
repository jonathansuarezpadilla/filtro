package riwi.com.filtrologistico.Controller.ImplementControllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import riwi.com.filtrologistico.Controller.InterfacesForEntity.InterfacePaletController;
import riwi.com.filtrologistico.Exception.ApiException;
import riwi.com.filtrologistico.Service.interfaces.IPaletService;
import riwi.com.filtrologistico.dtos.request.PaletRequest;
import riwi.com.filtrologistico.dtos.response.PaletResponse;

import java.util.List;

@Controller
@RequestMapping("Palet")
@Tag(name="Palet")
public class PaletController implements InterfacePaletController {

    @Autowired
    IPaletService paletService;

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','CONVEYOR')")
    @Operation(summary = "Traer un palet por ID", description = "Este endpoint requiere que envíes el ID del palet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Palet retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Este error sale cuando el ID es inválido o el palet no existe",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<PaletResponse> ById(@Parameter(description = "ID del palet") @PathVariable String id) {
        PaletResponse palet = paletService.readById(id);
        return ResponseEntity.ok(palet);
    }

    @Override
    @PostMapping
    @PreAuthorize(("hasRole('ADMIN')"))
    @Operation(summary = "Crear un nuevo palet", description = "Este endpoint requiere que envíes la información necesaria para crear un nuevo palet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Palet successfully created"),
            @ApiResponse(responseCode = "400", description = "Este error sale cuando falta un atributo o el tipo es inválido",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })

    public ResponseEntity<String> create(
            @Parameter(description = "Información del palet a crear") @RequestBody PaletRequest entity) {
        paletService.create(entity);
        return ResponseEntity.ok("Palet created");
    }

    @Override
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Eliminar un palet", description = "Este endpoint requiere que envíes el ID del palet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Palet successfully deleted"),
            @ApiResponse(responseCode = "400", description = "Este error sale cuando el ID es inválido o el palet no existe",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> delete(@Parameter(description = "ID del palet") @PathVariable String id) {

        paletService.delete(id);
        return ResponseEntity.ok("Palet deleted");
    }

    @Override
    @PatchMapping("/path/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Actualizar un dato del palet", description = "Este endpoint requiere que envíes el ID del palet y la información específica a actualizar")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Palet successfully updated"),
            @ApiResponse(responseCode = "400", description = "Este error sale cuando el ID es inválido o el palet no existe",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> path(
            @Parameter(description = "Información a actualizar") @RequestBody PaletRequest paletRequest,
            @Parameter(description = "ID del palet") @PathVariable String id){

        paletService.update(paletRequest, id);
        return ResponseEntity.ok("Palet updated");
    }

    @Override
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Actualizar completamente un palet",
            description = "Este endpoint requiere que envíes el ID del palet y" +
                    " la información completa que necesita un palet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Palet successfully updated"),
            @ApiResponse(responseCode = "400", description = "Este error sale cuando el ID es inválido o el palet no existe",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> put(
            @Parameter(description = "Información completa del palet a actualizar") @RequestBody PaletRequest entity,
            @Parameter(description = "ID del palet") @PathVariable String id) {

        paletService.update(entity, id);
        return ResponseEntity.ok("Palet updated");
    }

    @Override
    @GetMapping("/readAll")
    @PreAuthorize("hasAnyRole('ADMIN','CONVEYOR')")
    @Operation(summary = "Traer todos los palets", description = "Este endpoint no requiere nada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Palets retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Este error sale cuando el URL es inválido",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<PaletResponse>> readAll() {
        return ResponseEntity.ok(paletService.readAll());
    }
}
