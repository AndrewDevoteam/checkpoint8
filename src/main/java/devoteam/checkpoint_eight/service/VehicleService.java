package devoteam.checkpoint_eight.service;

import devoteam.checkpoint_eight.domain.Vehicle;
import devoteam.checkpoint_eight.repository.AutomakerRepository;
import devoteam.checkpoint_eight.repository.VehicleRepository;
import devoteam.checkpoint_eight.repository.VehicleTypeRepository;
import devoteam.checkpoint_eight.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final AutomakerRepository automakerRepository;
    private final VehicleTypeRepository vehicleTypeRepository;
    private final Utils utils;

    public List<Vehicle> listAll(){
        return vehicleRepository.findAll();
    }

    public Vehicle findByID(int id){
        return utils.findaVehicleOrThrowNotFound(id, vehicleRepository);
    }

    public List<Vehicle> findByName(String name){
        utils.findVehicleModelOrThrowNotFound(name, vehicleRepository);
        return vehicleRepository.searchByModel(name);
    }

    public Vehicle save(Vehicle vehicle){
        utils.findaAutomakerOrThrowNotFound(vehicle.getAutomaker().getId(), automakerRepository);
        utils.findVehicleTypeOrThrowNotFound(vehicle.getVehicleType().getId(), vehicleTypeRepository);
        return vehicleRepository.save(vehicle);
    }

    public void delete(int id){
        vehicleRepository.delete(utils.findaVehicleOrThrowNotFound(id, vehicleRepository));
    }

    public void update(Vehicle vehicle){
        utils.findaVehicleOrThrowNotFound(vehicle.getId(), vehicleRepository);
        vehicleRepository.save(vehicle);
    }
}
