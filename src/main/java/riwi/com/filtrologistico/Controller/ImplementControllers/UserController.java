package riwi.com.filtrologistico.Controller.ImplementControllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    //@PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @Operation(summary = "Este endpoint es para traer un usuario", description="estes enpoint requiere que envies el id del usuario")
    @ApiResponse(responseCode = "400", //arreglar
            description = "Este error sale cuando el id es invalido o el usuario no existe",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class)
                    )
            })
    public ResponseEntity<UserResponse> ById(@PathVariable String id) {
        UserResponse response =userService.readById(id);
        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping
    //@PreAuthorize(("hasRole('ADMIN')"))
    @Operation(summary = "Este endpoint es para buscar crear un usuario", description="este enpoint requiere que envies el la informacion para poder crear un usuario")
    @ApiResponse(responseCode = "400", //arreglar
            description = "Este error sale cuando falta un atributo o el tipo es invalido",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class)
                    )
            })
    public ResponseEntity<String> create(@RequestBody @Valid UserRequest entity) {
        userService.create(entity);
        return ResponseEntity.ok("User successfully created");
    }

    @Override
    @DeleteMapping("/delete/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Este endpoint es para eliminar un usuario", description="estes enpoint requiere que envies el id del usuario")
    @ApiResponse(responseCode = "400", //arreglar
            description = "Este error sale cuando el id es invalido o el usuario no existe",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class)
                    )
            })
    public ResponseEntity<String> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.ok("User successfully deleted");
    }

    @Override
    @PatchMapping("/path/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Este endpoint es para actualizar un dato del usuario", description="estos enpoint requiere que envies el id del usuario y la información especifica a actualizar ")
    @ApiResponse(responseCode = "400", //arreglar
            description = "Este error sale cuando el id es invalido o el usuario no existe",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class)
                    )
            })
    public ResponseEntity<String> path(@RequestBody  @Valid UserRequest request,@PathVariable String id) {
        userService.path(request,id);
        return ResponseEntity.ok("User successfully updated");
    }

    @Override
    @PutMapping("/update/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Este endpoint es para actualizar completamente un usuario", description="estes enpoint requiere que envies el id del usuario y la informacion completa que necesita un usuario")
    @ApiResponse(responseCode = "400", //arreglar
            description = "Este error sale cuando el id es invalido o el usuario no existe",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class)
                    )
            })
    public ResponseEntity<String> put(UserRequest entity, String id) {
        userService.update(entity, id);
        return ResponseEntity.ok("User successfully updated");
    }

    @Override
    @GetMapping("/readAll")
    //@PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @Operation(summary = "Este endpoint es para traer todos los usuario que existen", description="este endpoint no requiere nada")
    @ApiResponse(responseCode = "400", //arreglar
            description = "Este error sale cuando el id es invalido o el usuario no existe",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class)
                    )
            })
    public ResponseEntity<List<UserResponse>> readAll() {
        return  ResponseEntity.ok(userService.readAll());
    }

}
