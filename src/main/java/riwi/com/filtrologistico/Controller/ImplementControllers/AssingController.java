package riwi.com.filtrologistico.Controller.ImplementControllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import riwi.com.filtrologistico.Controller.InterfacesForEntity.InterfaceAssingController;
import riwi.com.filtrologistico.Service.interfaces.ICargaService;
import riwi.com.filtrologistico.Service.interfaces.IPaletService;
import riwi.com.filtrologistico.Service.interfaces.IUserService;

import java.util.List;

@Controller
@RequestMapping("Api/v1")
@Tag(name="Asignación")
public class AssingController implements InterfaceAssingController {

    @Autowired
    ICargaService cargaService;

    @Autowired
    IUserService userService;

    @Autowired
    IPaletService paletService;

    @Override
    public ResponseEntity<String> assign(String id, List<String> cargasIds) {

        userService.assign(id, cargasIds);
        return ResponseEntity.ok().body("Assignado com sucesso");
    }

    @Override
    public ResponseEntity<String> assingCargaPalet(String id, List<String> CargasIds) {

        paletService.AssingCargaPalet(id,CargasIds);
        return ResponseEntity.ok().body("Assingado com sucesso");
    }
}
