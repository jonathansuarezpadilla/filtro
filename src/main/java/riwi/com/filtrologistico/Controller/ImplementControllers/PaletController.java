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
    //@PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @Operation(summary = "Este endpoint es para traer un palet", description="estes enpoint requiere que envies el id del palet")
    @ApiResponse(responseCode = "400", //arreglar
            description = "Este error sale cuando el id es invalido o el palet no existe",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class)
                    )
            })
    public ResponseEntity<PaletResponse> ById(@PathVariable String id) {
        PaletResponse palet = paletService.readById(id);
        return ResponseEntity.ok(palet);
    }

    @Override
    @PostMapping
    //@PreAuthorize(("hasRole('ADMIN')"))
    @Operation(summary = "Este endpoint es para buscar crear un palet", description="este enpoint requiere que envies la informacion para poder crear un palet")
    @ApiResponse(responseCode = "400", //arreglar
            description = "Este error sale cuando falta un atributo o el tipo es invalido",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class)
                    )
            })
    public ResponseEntity<String> create(@RequestBody PaletRequest entity) {
        paletService.create(entity);
        return ResponseEntity.ok("Palet created");
    }

    @Override
    @DeleteMapping("/delete/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Este endpoint es para eliminar un palet", description="estes enpoint requiere que envies el id del palet")
    @ApiResponse(responseCode = "400", //arreglar
            description = "Este error sale cuando el id es invalido o la carga no existe",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class)
                    )
            })
    public ResponseEntity<String> delete(@PathVariable String id) {

        paletService.delete(id);
        return ResponseEntity.ok("Palet deleted");
    }

    @Override
    @PatchMapping("/path/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Este endpoint es para actualizar un dato del palet", description="estos enpoint requiere que envies el id del palet y la información especifica a actualizar ")
    @ApiResponse(responseCode = "400", //arreglar
            description = "Este error sale cuando el id es invalido o el palet no existe",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class)
                    )
            })
    public ResponseEntity<String> path(@RequestBody PaletRequest paletRequest,@PathVariable String id) {
        paletService.update(paletRequest, id);
        return ResponseEntity.ok("Palet updated");
    }

    @Override
    @PutMapping("/update/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Este endpoint es para actualizar completamente un palet", description="estes enpoint requiere que envies el id del palet y la informacion completa que necesita un palet")
    @ApiResponse(responseCode = "400", //arreglar
            description = "Este error sale cuando el id es invalido o el palet no existe",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class)
                    )
            })
    public ResponseEntity<String> put(@RequestBody PaletRequest entity,@PathVariable String id) {
        paletService.update(entity, id);
        return ResponseEntity.ok("Palet updated");
    }

    @Override
    @GetMapping("/readAll")
    //@PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @Operation(summary = "Este endpoint es para traer todos los palet", description="este endpoint no requiere nada")
    @ApiResponse(responseCode = "400", //arreglar
            description = "Este error sale cuando el url es invalido",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class)
                    )
            })
    public ResponseEntity<List<PaletResponse>> readAll() {
        return ResponseEntity.ok(paletService.readAll());
    }
}
