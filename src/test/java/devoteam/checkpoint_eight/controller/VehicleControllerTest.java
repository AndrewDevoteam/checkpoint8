package devoteam.checkpoint_eight.controller;

import devoteam.checkpoint_eight.domain.Automaker;
import devoteam.checkpoint_eight.domain.Vehicle;
import devoteam.checkpoint_eight.domain.VehicleType;
import devoteam.checkpoint_eight.repository.AutomakerRepository;
import devoteam.checkpoint_eight.repository.VehicleTypeRepository;
import devoteam.checkpoint_eight.service.VehicleService;
import devoteam.checkpoint_eight.util.VehicleCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@DataJpaTest
@ExtendWith(SpringExtension.class)
class VehicleControllerTest {
    @Autowired
    private AutomakerRepository automakerRepository;
    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @InjectMocks
    private VehicleController vehicleController;
    @Mock
    private VehicleService vehicleServiceMock;


    public Vehicle createVehicleToBeSaved() {

        Automaker automaker = Automaker.builder().name("Ferrari").build();
        automakerRepository.save(automaker);
        VehicleType vehicleType = VehicleType.builder().name("CAR").build();
        vehicleTypeRepository.save(vehicleType);

        return Vehicle.builder()
                .model("Enzo")
                .color("Red")
                .year("2020")
                .automaker(automaker)
                .vehicleType(vehicleType)
                .build();
    }

    @BeforeEach
    public void setUp(){
        List<Vehicle> vehicles = List.of(VehicleCreator.createValidVehicle());
        BDDMockito.when(vehicleServiceMock.listAll())
                .thenReturn(vehicles);

        BDDMockito.when(vehicleServiceMock.findByID(ArgumentMatchers.anyInt()))
                .thenReturn(VehicleCreator.createValidVehicle());

        BDDMockito.when(vehicleServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(VehicleCreator.createValidVehicle()));

        BDDMockito.when(vehicleServiceMock.save(createVehicleToBeSaved()))
                .thenReturn(VehicleCreator.createValidVehicle());

        BDDMockito.doNothing().when(vehicleServiceMock).delete(ArgumentMatchers.anyInt());

        BDDMockito.when(vehicleServiceMock.save(VehicleCreator.createValidVehicle()))
                .thenReturn(VehicleCreator.createValidUpdatedVehicle());
    }

    @Test
    @DisplayName("listAll returns a list of vehicles")
    public void listAllVehicles(){
        String expectedName = VehicleCreator.createValidVehicle().getModel();

        List<Vehicle> vehicles = vehicleController.listAll().getBody();

        Assertions.assertThat(vehicles).isNotNull();
        Assertions.assertThat(vehicles).isNotEmpty();
        Assertions.assertThat(vehicles.get(0).getModel()).isEqualTo(expectedName);

    }

    @Test
    @DisplayName("findById returns a vehicle when successfull")
    public void findById_ReturnsVehicle_WhenSuccessfull(){
        Integer expectedId = VehicleCreator.createValidVehicle().getId();

        Vehicle vehicle = vehicleController.findById(1).getBody();

        Assertions.assertThat(vehicle).isNotNull();
        Assertions.assertThat(vehicle.getId()).isNotNull();
        Assertions.assertThat(vehicle.getId()).isEqualTo(expectedId);

    }

    @Test
    @DisplayName("findByName returns a vehicle when successfull")
    public void findByName_ReturnsAVehicle_WhenSuccessfull(){
        String expectedName = VehicleCreator.createValidVehicle().getModel();

        List<Vehicle> vehicleList = vehicleController.findByName("DBZ").getBody();

        Assertions.assertThat(vehicleList).isNotNull();
        Assertions.assertThat(vehicleList).isNotEmpty();
        Assertions.assertThat(vehicleList.get(0).getModel()).isEqualTo(expectedName);

    }

    @Test
    @DisplayName("save creates a vehicle when successfull")
    public void save_CreatesAVehicle_WhenSuccessfull(){
        Integer expectedId = VehicleCreator.createValidVehicle().getId();

        Vehicle vehicleToBeSaved = createVehicleToBeSaved();

        Vehicle vehicle = vehicleController.save(vehicleToBeSaved).getBody();

        Assertions.assertThat(vehicle).isNotNull();
        Assertions.assertThat(vehicle.getId()).isNotNull();
        Assertions.assertThat(vehicle.getId()).isEqualTo(expectedId);

    }

    @Test
    @DisplayName("delete removes a vehicle when successfull")
    public void delete_RemovesAVehicle_WhenSuccessfull(){

        ResponseEntity<Void> responseEntity = vehicleController.delete(1);
        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        Assertions.assertThat(responseEntity.getBody()).isNull();

    }

    @Test
    @DisplayName("update save updated vehicle when successfull")
    public void update_SaveUpdatedVehicle_WhenSuccessfull(){

        ResponseEntity<Void> responseEntity = vehicleController.update(VehicleCreator.createValidVehicle());

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        Assertions.assertThat(responseEntity.getBody()).isNull();

    }
}