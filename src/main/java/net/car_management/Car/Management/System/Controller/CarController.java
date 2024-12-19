package net.car_management.Car.Management.System.Controller;

import jakarta.validation.Valid;
import net.car_management.Car.Management.System.DTO.CarDTO;
import net.car_management.Car.Management.System.Models.Car;
import net.car_management.Car.Management.System.Services.CarServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.List;

@RestController
@Controller
@Validated
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarServices carServices;

    @GetMapping("/getAllCars")
    public ResponseEntity<List<CarDTO>> getAllCars() {
        List<CarDTO> cars = carServices.getAllCars();
        return ResponseEntity.ok(cars);
    }

    @PostMapping("/addCars")
    public ResponseEntity<List<CarDTO>> addCars(@Valid @RequestBody List<@Valid CarDTO> cars) {
        List<CarDTO> addedCars = carServices.addCars(cars);
        return new ResponseEntity<>(addedCars, HttpStatus.CREATED);
    }

    @PutMapping("/updateCarById/{id}")
    public ResponseEntity<CarDTO> updateCarById(
            @PathVariable Long id,
            @Valid @RequestBody CarDTO carDTO) {
        CarDTO updatedCar = carServices.updateCarById(id, carDTO);
        return ResponseEntity.ok(updatedCar);
    }

    @DeleteMapping("/deleteCarById/{id}")
    public ResponseEntity<CarDTO> deleteCarById(@PathVariable Long id) {
        CarDTO deletedCar = carServices.deleteCarById(id);
        return ResponseEntity.ok(deletedCar);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Car>> searchCars(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Year year,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String fuelType
    ) {
        List<Car> cars = carServices.searchCars(name, model, year, color, fuelType);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<Car>> getCarsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Car> cars = carServices.getCarsWithPagination(page, size);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/sorting")
    public ResponseEntity<List<Car>> getCarsWithSorting(
            @RequestParam(defaultValue = "carName") String sortBy,
            @RequestParam(defaultValue = "asc") String order) {
        List<Car> cars = carServices.getCarsWithSorting(sortBy, order);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/pagination-sorting")
    public ResponseEntity<Page<Car>> getCarsWithPaginationAndSorting(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "carName") String sortBy,
            @RequestParam(defaultValue = "asc") String order) {
        Page<Car> cars = carServices.getCarsWithPaginationAndSorting(page, size, sortBy, order);
        return ResponseEntity.ok(cars);
    }



}
