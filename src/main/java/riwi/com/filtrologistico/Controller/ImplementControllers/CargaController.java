package riwi.com.filtrologistico.Controller.ImplementControllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    //@PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @Operation(summary = "Este endpoint es para traer una carga", description="estes enpoint requiere que envies el id de la carga")
    @ApiResponse(responseCode = "400", //arreglar
            description = "Este error sale cuando el id es invalido o la carga no existe",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class)
                    )
            })
    public ResponseEntity<CargaResponse> ById(@PathVariable String id) {
        CargaResponse cargaResponse= cargaService.readById(id);
        return ResponseEntity.ok(cargaResponse);
    }

    @Override
    @PostMapping
    //@PreAuthorize(("hasRole('ADMIN')"))
    @Operation(summary = "Este endpoint es para buscar crear una carga", description="este enpoint requiere que envies la informacion para poder crear una carga")
    @ApiResponse(responseCode = "400", //arreglar
            description = "Este error sale cuando falta un atributo o el tipo es invalido",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class)
                    )
            })
    public ResponseEntity<String> create(@RequestBody CargaRequest entity) {

        cargaService.create(entity);
        return ResponseEntity.ok("Load successfully created");
    }

    @Override
    @DeleteMapping("/delete/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Este endpoint es para eliminar una carga", description="estes enpoint requiere que envies el id de la carga")
    @ApiResponse(responseCode = "400", //arreglar
            description = "Este error sale cuando el id es invalido o la carga no existe",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class)
                    )
            })
    public ResponseEntity<String> delete(@PathVariable String id) {

        cargaService.delete(id);
        return ResponseEntity.ok("Load successfully deleted");
    }

    @Override
    @PatchMapping("/path/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Este endpoint es para actualizar un dato de la carga", description="estos enpoint requiere que envies el id de la carga y la información especifica a actualizar ")
    @ApiResponse(responseCode = "400", //arreglar
            description = "Este error sale cuando el id es invalido o el usuario no existe",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class)
                    )
            })
    public ResponseEntity<String> path(@RequestBody CargaRequest cargaRequest,@PathVariable String id) {
        cargaService.update(cargaRequest, id);
        return ResponseEntity.ok("Load successfully updated");
    }

    @Override
    @PutMapping("/update/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Este endpoint es para actualizar completamente una carga", description="estes enpoint requiere que envies el id del usuario y la informacion completa que necesita una carga")
    @ApiResponse(responseCode = "400", //arreglar
            description = "Este error sale cuando el id es invalido o carga no existe",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class)
                    )
            })
    public ResponseEntity<String> put(@RequestBody CargaRequest entity,@PathVariable String id) {
        cargaService.update(entity, id);
        return ResponseEntity.ok("Load successfully updated");
    }

    @Override
    @GetMapping("/readAll")
    //@PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @Operation(summary = "Este endpoint es para traer todos las cargas", description="este endpoint no requiere nada")
    @ApiResponse(responseCode = "400", //arreglar
            description = "Este error sale cuando el url es invalido",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class)
                    )
            })
    public ResponseEntity<List<CargaResponse>> readAll() {
        return ResponseEntity.ok(cargaService.readAll());
    }
}
