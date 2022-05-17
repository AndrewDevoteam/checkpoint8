package devoteam.checkpoint_eight.controller;

import devoteam.checkpoint_eight.domain.VehicleType;
import devoteam.checkpoint_eight.service.VehicleTypeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("vehicletypes")
@RequiredArgsConstructor
public class VehicleTypeController {

    private final VehicleTypeService vehicleTypeService;

    @GetMapping
    @Operation(summary = "Generate a list of all available vehicle types",
            description = "Execute endpoint to generate a list of available vehicle types")
    public ResponseEntity<List<VehicleType>> listAll(){
        return ResponseEntity.ok(vehicleTypeService.listAll());
    }

    @PostMapping
    @Operation(summary = "Add a vehicle type to the list",
            description = "Type in the vehicle type to add to the list")
    public ResponseEntity<VehicleType> save(@RequestBody @Valid VehicleType vehicleType) {
        return ResponseEntity.ok(vehicleTypeService.save(vehicleType));
    }

}
