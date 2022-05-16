package devoteam.checkpoint_eight.controller;

import devoteam.checkpoint_eight.domain.Vehicle;
import devoteam.checkpoint_eight.domain.VehicleType;
import devoteam.checkpoint_eight.repository.VehicleTypeRepository;
import devoteam.checkpoint_eight.service.VehicleTypeService;
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
    public ResponseEntity<List<VehicleType>> listAll(){
        return ResponseEntity.ok(vehicleTypeService.listAll());
    }

    @PostMapping
    public ResponseEntity<VehicleType> save(@RequestBody @Valid VehicleType vehicleType) {
        return ResponseEntity.ok(vehicleTypeService.save(vehicleType));
    }

}
