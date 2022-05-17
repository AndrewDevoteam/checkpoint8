package devoteam.checkpoint_eight.util;

import devoteam.checkpoint_eight.domain.Automaker;
import devoteam.checkpoint_eight.domain.Vehicle;
import devoteam.checkpoint_eight.domain.VehicleType;
import devoteam.checkpoint_eight.exception.ResourceNotFoundException;
import devoteam.checkpoint_eight.repository.AutomakerRepository;
import devoteam.checkpoint_eight.repository.VehicleRepository;
import devoteam.checkpoint_eight.repository.VehicleTypeRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class Utils {

    public String formatLocalDateTimeToDatabaseStyle(LocalDateTime localDateTime) {
        return DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss").format(localDateTime);
    }

    public Automaker findaAutomakerOrThrowNotFound(int id, AutomakerRepository automakerRepository) {
        return automakerRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Automaker not found"));
    }
    public Automaker findaAutomakerModelOrThrowNotFound(String name, AutomakerRepository automakerRepository) {
        return automakerRepository
                .findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Automaker not found"));

    }
    public Vehicle findaVehicleOrThrowNotFound(int id, VehicleRepository vehicleRepository) {
        return vehicleRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));
    }

    public VehicleType findVehicleTypeOrThrowNotFound(int id, VehicleTypeRepository vehicleTypeRepository){
        return vehicleTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle type was not Found"));
    }

    public Vehicle findVehicleModelOrThrowNotFound(String name, VehicleRepository vehicleRepository) {
        return vehicleRepository
                .searchByModel(name)
                .stream().findAny()
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));
    }


}
