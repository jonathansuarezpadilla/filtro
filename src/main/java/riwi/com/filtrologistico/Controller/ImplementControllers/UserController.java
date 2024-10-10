package riwi.com.filtrologistico.Controller.ImplementControllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import riwi.com.filtrologistico.Controller.InterfacesForEntity.InterfaceUserController;
import riwi.com.filtrologistico.Exception.ApiException;
import riwi.com.filtrologistico.Service.interfaces.IUserService;
import riwi.com.filtrologistico.dtos.request.UserRequest;
import riwi.com.filtrologistico.dtos.response.UserResponse;

import java.util.List;

@Controller
@RequestMapping("usuario")
@Tag(name="Usuario")
public class UserController implements InterfaceUserController {


    @Autowired
    IUserService userService;

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Este endpoint es para traer un usuario",
            description = "Este endpoint requiere que envíes el ID del usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Este error sale cuando el ID es inválido o el usuario no existe",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<UserResponse> ById(@Parameter(description = "ID del usuario") @PathVariable String id) {
        UserResponse response =userService.readById(id);
        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping
    @PreAuthorize(("hasRole('ADMIN')"))
    @Operation(summary = "Este endpoint es para buscar crear un usuario",
            description="este enpoint requiere que envies " +
            "el la informacion para poder crear un usuario")
    @ApiResponse(responseCode = "400", //arreglar
            description = "Este error sale cuando falta un atributo o el tipo es invalido",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class)
                    )
            })
    public ResponseEntity<String> create(@Parameter(description = "Información del usuario a crear")
                                             @RequestBody @Valid UserRequest entity) {
        userService.create(entity);
        return ResponseEntity.ok("User successfully created");
    }

    @Override
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Eliminar un usuario",
            description = "Este endpoint requiere " +
            "que envíes el ID del usuario para eliminarlo")

    public ResponseEntity<String> delete(@Parameter(description = "ID del usuario a eliminar")
                                             @PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.ok("User successfully deleted");
    }

    @Override
    @PatchMapping("/path/{id}")
    @PreAuthorize("hasRole('ADMIN','CONVEYOR')")
    @Operation(summary = "Actualizar un dato del usuario", description = "Este endpoint requiere que envíes el ID del usuario y la información específica a actualizar")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully updated"),
            @ApiResponse(responseCode = "400", description = "Este error sale cuando el ID es inválido o el usuario no existe",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> path(@Parameter(description = "ID del usuario") @RequestBody  @Valid UserRequest request, @Parameter(description = "ID del usuario") @PathVariable String id) {
        userService.path(request,id);
        return ResponseEntity.ok("User successfully updated");
    }


    @Override
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','CONVEYOR')")
    @Operation(summary = "Actualizar completamente un usuario",
            description = "Este endpoint requiere que envíes el " +
            "ID del usuario y la información completa que necesita un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully updated"),
            @ApiResponse(responseCode = "400",
                    description = "Este error sale cuando el ID" +
                    " es inválido o el usuario no existe",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> put(@Parameter(description = "Información completa del usuario a actualizar")
                                          UserRequest entity,
                                      @Parameter(description = "ID del usuario") String id) {
        userService.update(entity, id);
        return ResponseEntity.ok("User successfully updated");
    }

    @Override
    @GetMapping("/readAll")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Traer todos los usuarios existentes", description = "Este endpoint no requiere nada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Este error sale cuando el ID es inválido o el usuario no existe",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<UserResponse>> readAll() {
        return  ResponseEntity.ok(userService.readAll());
    }

}
