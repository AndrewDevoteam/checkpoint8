package devoteam.checkpoint_eight.util;

import devoteam.checkpoint_eight.domain.Automaker;
import devoteam.checkpoint_eight.domain.Vehicle;
import devoteam.checkpoint_eight.domain.VehicleType;
import devoteam.checkpoint_eight.repository.AutomakerRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class VehicleCreator {

    private AutomakerRepository automakerRepository;

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

    public static Vehicle createValidVehicle() {
        return Vehicle.builder().model("Enzo").id(1).build();
    }
    public static Vehicle createValidUpdatedVehicle() {
        return Vehicle.builder().model("Enzo").id(1).build();
    }
}
