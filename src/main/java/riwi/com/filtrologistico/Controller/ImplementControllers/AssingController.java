package riwi.com.filtrologistico.Controller.ImplementControllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import riwi.com.filtrologistico.Controller.InterfacesForEntity.InterfaceAssingController;
import riwi.com.filtrologistico.Service.interfaces.ICargaService;
import riwi.com.filtrologistico.Service.interfaces.IPaletService;
import riwi.com.filtrologistico.Service.interfaces.IUserService;

import java.util.List;

@Controller
@RequestMapping("Api/v1")
@Tag(name="Asignación",description = "Endpoints para la asignación de cargas")
public class AssingController implements InterfaceAssingController {

    @Autowired
    ICargaService cargaService;

    @Autowired
    IUserService userService;

    @Autowired
    IPaletService paletService;

    @Override
    @PostMapping("assing/{id}")
    @Operation(summary = "Assign cargas to user", description = "Assigns a list of cargas to a user by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assigned successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> assign(@Parameter(description = "ID of the user") @PathVariable String id,
                                         @Parameter(description = "List of cargas IDs to be assigned")
                                         @RequestBody List<String> cargasIds) {

        userService.assign(id, cargasIds);
        return ResponseEntity.ok().body("Assignado com sucesso");
    }

    @Override
    @PostMapping("assingCargaPalet/{id}")
    @Operation(summary = "Assign cargas to palet", description = "Assigns a list of cargas to a palet by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assigned successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> assingCargaPalet(@Parameter(description = "ID of the palet") @PathVariable String id,
                                                   @Parameter(description = "List of cargas IDs to be assigned")
                                                   @RequestBody List<String> CargasIds) {

        paletService.AssingCargaPalet(id,CargasIds);
        return ResponseEntity.ok().body("Assingado com sucesso");
    }
}
