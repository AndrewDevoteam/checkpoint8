package devoteam.checkpoint_eight.service;


import devoteam.checkpoint_eight.domain.Automaker;
import devoteam.checkpoint_eight.domain.Vehicle;
import devoteam.checkpoint_eight.repository.AutomakerRepository;
import devoteam.checkpoint_eight.repository.VehicleRepository;
import devoteam.checkpoint_eight.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AutomakerService {

    private final AutomakerRepository automakerRepository;
    private final Utils utils;

    public List<Automaker> listAll(){
        return automakerRepository.findAll();
    }

    public Automaker findByName(String name){
         return utils.findaAutomakerModelOrThrowNotFound(name, automakerRepository);
    }

    public Automaker findByID(int id){
        return utils.findaAutomakerOrThrowNotFound(id, automakerRepository);
    }

    public Automaker save(Automaker automaker){
        return automakerRepository.save(automaker);
    }

    public void delete(int id){
        automakerRepository.delete(utils.findaAutomakerOrThrowNotFound(id, automakerRepository));
    }

    public void update(Automaker automaker){
        automakerRepository.save(automaker);
    }
}
