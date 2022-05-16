package devoteam.checkpoint_eight.service;

import devoteam.checkpoint_eight.domain.Vehicle;
import devoteam.checkpoint_eight.repository.VehicleRepository;
import devoteam.checkpoint_eight.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final Utils utils;

    public List<Vehicle> listAll(){
        return vehicleRepository.findAll();
    }

    public Vehicle findByID(int id){
        return utils.findaVehicleOrThrowNotFound(id, vehicleRepository);
    }

    public Vehicle findByName(String name){
        return utils.findVehicleModelOrThrowNotFound(name, vehicleRepository);
    }


    public Vehicle save(Vehicle vehicle){
        return vehicleRepository.save(vehicle);
    }

    public void delete(int id){
        vehicleRepository.delete(utils.findaVehicleOrThrowNotFound(id, vehicleRepository));
    }

    public void update(Vehicle vehicle){
        vehicleRepository.save(vehicle);
    }
}
