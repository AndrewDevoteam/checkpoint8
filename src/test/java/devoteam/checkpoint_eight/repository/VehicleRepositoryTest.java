package devoteam.checkpoint_eight.repository;

import devoteam.checkpoint_eight.domain.Automaker;
import devoteam.checkpoint_eight.domain.Vehicle;
import devoteam.checkpoint_eight.domain.VehicleType;
import devoteam.checkpoint_eight.util.VehicleCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

@DataJpaTest
@DisplayName("Automaker Repository Test")
public class VehicleRepositoryTest {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private AutomakerRepository automakerRepository;
//    private final VehicleCreator vehicleCreator = new VehicleCreator();

    public Vehicle createVehicleToBeSaved() {

        Automaker automaker = Automaker.builder().id(1).name("Ferrari").build();
        automakerRepository.save(automaker);
        VehicleType vehicleType = new VehicleType(1,"CAR");

        return Vehicle.builder()
                .model("Enzo")
                .color("Red")
                .year("2020")
                .automaker(automaker)
                .vehicleType(vehicleType)
                .build();
    }

    @Test
    @DisplayName("Save creates Vehicle when successfull")
    public void saveVehicles() {

        Vehicle vehicle = createVehicleToBeSaved();
        Vehicle savedVehicle = this.vehicleRepository.save(vehicle);

        Assertions.assertThat(savedVehicle.getId()).isNotNull();
        Assertions.assertThat(savedVehicle.getModel()).isNotNull();
        Assertions.assertThat(savedVehicle.getModel()).isEqualTo(vehicle.getModel());
    }

    @Test
    @DisplayName("Save updates Vehicle when successfull")
    public void save_UpdateVehicle_WhenSuccessfull() {
        Vehicle vehicle = createVehicleToBeSaved();
        Vehicle savedVehicle = this.vehicleRepository.save(vehicle);

        savedVehicle.setModel(("That time i got a new car"));

        Vehicle updatedVehicle = this.vehicleRepository.save(savedVehicle);

        Assertions.assertThat(savedVehicle.getId()).isNotNull();
        Assertions.assertThat(savedVehicle.getModel()).isNotNull();
        Assertions.assertThat(savedVehicle.getModel()).isEqualTo(updatedVehicle.getModel());
    }

    @Test
    @DisplayName("Removes Vehicle when successfull")
    public void delete_RemoveVehicle_WhenSuccessfull() {
        Vehicle vehicle = createVehicleToBeSaved();
        Vehicle savedVehicle = this.vehicleRepository.save(vehicle);

        this.vehicleRepository.delete(vehicle);
        Optional<Vehicle> animeOptional = this.vehicleRepository.findById(savedVehicle.getId());

        savedVehicle.setModel(("That time i got reincarnated as a slime"));

        Assertions.assertThat(animeOptional.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Find By name returns Vehicle when successfull")
    public void findByName_ReturnVehicle_WhenSuccessfull() {
        Vehicle vehicle = createVehicleToBeSaved();
        Vehicle savedVehicle = this.vehicleRepository.save(vehicle);

        String name = savedVehicle.getModel();

        Optional<Vehicle> vehicleOptional = this.vehicleRepository.searchByModel(name);

        Assertions.assertThat(vehicleOptional).isNotEmpty();
        Assertions.assertThat(vehicleOptional).contains(savedVehicle);
    }

    @Test
    @DisplayName("Find By name returns empty list when no vehicle is found")
    public void findByName_ReturnEmptyList_WhenVehicleNotFound() {

        String name = "Fake-Name";

        Optional<Vehicle> vehicleOptional = this.vehicleRepository.searchByModel(name);

        Assertions.assertThat(vehicleOptional).isEmpty();
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when name is empty")
    public void save_ThrowConstraintViolationException_WhenNameIsEmpty() {
        Vehicle vehicle = new Vehicle();

        Assertions.assertThatThrownBy(() -> vehicleRepository.save(vehicle))
                .isInstanceOf(ConstraintViolationException.class);
    }


}
