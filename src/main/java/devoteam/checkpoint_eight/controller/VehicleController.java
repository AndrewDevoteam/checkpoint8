package devoteam.checkpoint_eight.controller;

import devoteam.checkpoint_eight.domain.Automaker;
import devoteam.checkpoint_eight.domain.Vehicle;
import devoteam.checkpoint_eight.repository.AutomakerRepository;
import devoteam.checkpoint_eight.repository.VehicleRepository;
import devoteam.checkpoint_eight.service.AutomakerService;
import devoteam.checkpoint_eight.service.VehicleService;
import devoteam.checkpoint_eight.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<List<Vehicle>> listAll(){
        return ResponseEntity.ok(vehicleService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Vehicle> findById(@PathVariable int id){
        return ResponseEntity.ok(vehicleService.findByID(id));
    }

    //search by model endpoint
    @GetMapping(path = "/find")
    public ResponseEntity<Vehicle> findByName(@RequestParam(value = "name", required = true) String name){
        return ResponseEntity.ok(vehicleService.findByName(name));
    }

    //endpoint to save a vehicle
    @PostMapping
    public ResponseEntity<Vehicle> save(@RequestBody @Valid Vehicle vehicle) {

        return ResponseEntity.ok(vehicleService.save(vehicle));
    }

    //endpoint to delete a vehicle
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){

        vehicleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //endpoint to update a vehicle
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Vehicle vehicle){

        vehicleService.update(vehicle);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
