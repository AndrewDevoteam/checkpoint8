package devoteam.checkpoint_eight.controller;

import devoteam.checkpoint_eight.domain.Automaker;
import devoteam.checkpoint_eight.service.AutomakerService;
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
@RequestMapping("automakers")
@RequiredArgsConstructor
public class AutomakerController {

    private final AutomakerService automakerService;

    @GetMapping
    @Operation(summary = "Generate a list of all available automakers",
            description = "Execute endpoint to generate a report on all available automakers")
    public ResponseEntity<List<Automaker>> listAll(){
        return ResponseEntity.ok(automakerService.listAll());
    }

    @GetMapping(path = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found automaker"),
            @ApiResponse(responseCode = "404", description = "Automaker was not found")
    })
    @Operation(summary = "Find a automaker by its ID",
            description = "Insert a automaker Id to see the automaker details")
    public ResponseEntity<Automaker> findById(@PathVariable int id){

        return ResponseEntity.ok(automakerService.findByID(id));
    }

    //search by automaker endpoint
    @GetMapping(path = "/find")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found Automaker"),
            @ApiResponse(responseCode = "404", description = "Automaker was not found")
    })
    @Operation(summary = "Search by automaker name to find the available vehicles.",
            description = "To search for the available vehicles type in an automaker name" )
    public ResponseEntity<Automaker> findByName(@RequestParam(value = "name", required = true) String name){
        return ResponseEntity.ok(automakerService.findByName(name));
    }

    //endpiont to create an automaker
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Added Automaker"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
    })
    @Operation(summary = "Add a automaker to the list",
            description = "Type in the automaker details to add the automaker")
    public ResponseEntity<Automaker> save(@RequestBody @Valid Automaker automaker) {
        return ResponseEntity.ok(automakerService.save(automaker));
    }

//    endpoint to delete a automaker
    @DeleteMapping(path = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted Automaker"),
            @ApiResponse(responseCode = "404", description = "Automaker was not found")
    })
    @Operation(summary = "Delete a automaker from the list",
            description = "Type in the automaker Id to delete the automaker")
    public ResponseEntity<Void> delete(@PathVariable int id){

        automakerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    endpoint to update an automaker
    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully updated Automaker"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Automaker was not found")
    })
    @Operation(summary = "Update an Automaker in the list",
            description = "Type in the automaker details to update the automaker")
    public ResponseEntity<Void> update(@RequestBody Automaker automaker){

        automakerService.update(automaker);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
