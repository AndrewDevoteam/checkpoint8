package devoteam.checkpoint_eight.controller;

import devoteam.checkpoint_eight.domain.Automaker;
import devoteam.checkpoint_eight.service.AutomakerService;
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
    public ResponseEntity<List<Automaker>> listAll(){
        return ResponseEntity.ok(automakerService.listAll());
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<Automaker> findById(@PathVariable int id){

        return ResponseEntity.ok(automakerService.findByID(id));
    }

    //search by automaker endpoint
    @GetMapping(path = "/find")
    public ResponseEntity<Automaker> findByName(@RequestParam(value = "name", required = true) String name){
        return ResponseEntity.ok(automakerService.findByName(name));
    }

    //endpiont to create an automaker
    @PostMapping
    public ResponseEntity<Automaker> save(@RequestBody @Valid Automaker automaker) {
        return ResponseEntity.ok(automakerService.save(automaker));
    }

//    endpoint to delete a automaker
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){

        automakerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    endpoint to update an automaker
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Automaker automaker){

        automakerService.update(automaker);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
