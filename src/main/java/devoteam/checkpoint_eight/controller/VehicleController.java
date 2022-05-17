package devoteam.checkpoint_eight.controller;

import devoteam.checkpoint_eight.domain.Vehicle;
import devoteam.checkpoint_eight.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping
    @Operation(summary = "Generate a list of all available vehicles",
            description = "Execute endpoint to generate a report on all available vehicles")
    public ResponseEntity<List<Vehicle>> listAll() {
        return ResponseEntity.ok(vehicleService.listAll());
    }

    @GetMapping(path = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found Vehicle"),
            @ApiResponse(responseCode = "404", description = "Resource was not found")
    })
    @Operation(summary = "Find a vehicle by its ID",
            description = "Insert a vehicle Id to see the vehicle details")
    public ResponseEntity<Vehicle> findById(@PathVariable int id) {
        return ResponseEntity.ok(vehicleService.findByID(id));
    }

    //search by model endpoint
    @GetMapping(path = "/find")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found Vehicle"),
            @ApiResponse(responseCode = "404", description = "Vehicle was not found")
    })
    @Operation(summary = "Find a vehicle based on its name",
            description = "To see if a vehicle is available type in its name")
    public ResponseEntity<List<Vehicle>> findByName(@RequestParam(value = "name", required = true) String name) {
        return ResponseEntity.ok(vehicleService.findByName(name));
    }

    //endpoint to save a vehicle
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Added Vehicle"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Resource was not found")
    })
    @Operation(summary = "Add a vehicle to the list",
            description = "Type in the vehicle details to add the vehicle")
    public ResponseEntity<Vehicle> save(@RequestBody @Valid Vehicle vehicle) {
        return ResponseEntity.ok(vehicleService.save(vehicle));
    }

    //endpoint to delete a vehicle
    @DeleteMapping(path = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted Vehicle"),
            @ApiResponse(responseCode = "404", description = "Vehicle was not found")
    })
    @Operation(summary = "Delete a vehicle from the list",
            description = "Type in the vehicle Id to delete the vehicle")
    public ResponseEntity<Void> delete(@PathVariable int id) {

        vehicleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //endpoint to update a vehicle
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully updated vehicle"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Vehicle was not found")
    })
    @Operation(summary = "Update a vehicle in the list",
            description = "Type in the vehicle details to update the vehicle")
    public ResponseEntity<Void> update(@RequestBody Vehicle vehicle) {

        vehicleService.update(vehicle);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
