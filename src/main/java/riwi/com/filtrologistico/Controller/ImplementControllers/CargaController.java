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
import riwi.com.filtrologistico.Controller.InterfacesForEntity.InterfaceCargaController;
import riwi.com.filtrologistico.Exception.ApiException;
import riwi.com.filtrologistico.Service.interfaces.ICargaService;
import riwi.com.filtrologistico.dtos.request.CargaRequest;
import riwi.com.filtrologistico.dtos.response.CargaResponse;

import java.util.List;


@Controller
@RequestMapping("carga")
@Tag(name="Carga")
public class CargaController implements InterfaceCargaController {


    @Autowired
    ICargaService cargaService;

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','CONVEYOR')")
    @Operation(summary = "Traer una carga por ID",
            description = "Este endpoint requiere que envíes el ID de la carga")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carga retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Este error sale cuando el ID " +
                    "es inválido o la carga no existe",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<CargaResponse> ById(@Parameter(description = "ID de la carga") @PathVariable String id) {
        CargaResponse cargaResponse= cargaService.readById(id);
        return ResponseEntity.ok(cargaResponse);
    }

    @Override
    @PostMapping
    @PreAuthorize(("hasRole('ADMIN')"))
    @Operation(summary = "Crear una nueva carga",
            description = "Este endpoint requiere que envíes la información necesaria para crear una nueva carga")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carga successfully created"),
            @ApiResponse(responseCode = "400",
                    description = "Este error sale cuando falta un atributo o el tipo es inválido",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> create(@Parameter(description = "Información de la carga a crear")
                                             @RequestBody CargaRequest entity) {

        cargaService.create(entity);
        return ResponseEntity.ok("Load successfully created");
    }

    @Override
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Eliminar una carga", description = "Este endpoint requiere que envíes el ID de la carga")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carga successfully deleted"),
            @ApiResponse(responseCode = "400", description = "Este error sale " +
                    "cuando el ID es inválido o la carga no existe",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> delete(@Parameter(description = "ID de la carga") @PathVariable String id) {

        cargaService.delete(id);
        return ResponseEntity.ok("Load successfully deleted");
    }

    @Override
    @PatchMapping("/path/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Actualizar un dato de la carga",
            description = "Este endpoint requiere que envíes el ID de " +
                    "la carga y la información específica a actualizar")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Load successfully updated"),
            @ApiResponse(responseCode = "400", description = "Este error sale cuando el ID es inválido o la carga no existe",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> path(
            @Parameter(description = "Información a actualizar") @RequestBody CargaRequest cargaRequest,
            @Parameter(description = "ID de la carga") @PathVariable String id) {
        cargaService.update(cargaRequest, id);
        return ResponseEntity.ok("Load successfully updated");
    }

    @Override
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Actualizar completamente una carga",
            description = "Este endpoint requiere que envíes el ID del usuario y" +
                    " la información completa que necesita una carga")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Load successfully updated"),
            @ApiResponse(responseCode = "400", description = "Este error sale cuando el ID es inválido o la carga no existe",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> put(
            @Parameter(description = "Información completa de la carga a actualizar") @RequestBody CargaRequest entity,
            @Parameter(description = "ID de la carga") @PathVariable String id) {

        cargaService.update(entity, id);
        return ResponseEntity.ok("Load successfully updated");
    }

    @Override
    @GetMapping("/readAll")
    @PreAuthorize("hasAnyRole('ADMIN','CONVEYOR')")
    @Operation(summary = "Traer todas las cargas", description = "Este endpoint no requiere nada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cargas retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Este error sale cuando el URL es inválido",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<CargaResponse>> readAll() {
        return ResponseEntity.ok(cargaService.readAll());
    }
}
