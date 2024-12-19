package net.car_management.Car.Management.System.DTO;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.car_management.Car.Management.System.Annonation.ValidYear;

import java.math.BigDecimal;
import java.time.Year;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {
    private Long id;

    @NotBlank(message = "car name cannot be blank")
    private String carName;

    @NotBlank(message = "car model name cannot be blank")
    private String carModel;

    @NotBlank(message = "car color cannot be blank")
    private String carColor;

    @Min(value = 0, message = "Price cannot be negative")
    private BigDecimal carPrice;

    @ValidYear(message = "Invalid car year. It should be a 4-digit number and not exceed the current year")
    @NotNull(message = "Car year is required")
    private Year carYear;

    @NotNull(message = "Fuel type cannot be null")
    @Pattern(
            regexp = "^(Petrol|Diesel|Electric|Hybrid|CNG|LPG|petrol|diesel|electric|hybrid|cng|lpg)$",
            message = "Fuel type must be one of: Petrol,Diesel,Electric,Hybrid,LPG,CNG"
    )
    private String carFuelType;

}
