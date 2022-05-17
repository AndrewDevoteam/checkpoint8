package devoteam.checkpoint_eight.repository;

import devoteam.checkpoint_eight.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    List<Vehicle> searchByModel(String model);
}
