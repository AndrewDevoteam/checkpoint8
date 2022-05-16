package devoteam.checkpoint_eight.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotEmpty(message = "The name of the vehicle cannot be empty")
    private String model;
    private String color;
    private String year;

    @NotNull
    @OneToOne
    @JoinColumn(name="automaker_id")
    @JsonBackReference
    private Automaker automaker;

    @OneToOne
    @JoinColumn(name="vehicletype_id")
    private VehicleType vehicleType;

}
