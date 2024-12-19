package net.car_management.Car.Management.System.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Year;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "car_name")
    private String carName;
    @Column(name = "car_model")
    private String carModel;
    @Column(name = "car_color")
    private String carColor;
    @Column(name = "car_price")
    private BigDecimal carPrice;
    @Column(name = "car_year")
    private Year carYear;
    @Column(name = "car_fuel_type")
    private String carFuelType;
}
