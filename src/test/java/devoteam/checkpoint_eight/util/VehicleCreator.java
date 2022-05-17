package devoteam.checkpoint_eight.util;

import devoteam.checkpoint_eight.domain.Vehicle;
import org.springframework.stereotype.Component;


public class VehicleCreator {

    public static Vehicle createValidVehicle() {
        return Vehicle.builder().model("Enzo").id(1).build();
    }
    public static Vehicle createValidUpdatedVehicle() {
        return Vehicle.builder().model("Enzo").id(1).build();
    }
}
