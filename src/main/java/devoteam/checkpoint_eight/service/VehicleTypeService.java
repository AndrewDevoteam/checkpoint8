package devoteam.checkpoint_eight.service;


import devoteam.checkpoint_eight.domain.VehicleType;
import devoteam.checkpoint_eight.repository.VehicleTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class VehicleTypeService {

    private final VehicleTypeRepository vehicleTypeRepository;

    public List<VehicleType> listAll(){
        return vehicleTypeRepository.findAll();
    }

    public VehicleType save(VehicleType vehicleType){
        return vehicleTypeRepository.save(vehicleType);
    }

}
