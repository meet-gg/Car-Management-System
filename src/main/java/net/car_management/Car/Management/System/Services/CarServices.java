package net.car_management.Car.Management.System.Services;

import jakarta.validation.Valid;
import net.car_management.Car.Management.System.DTO.CarDTO;
import net.car_management.Car.Management.System.Exception.ResourceNotFoundException;
import net.car_management.Car.Management.System.Models.Car;
import net.car_management.Car.Management.System.Repository.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServices {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<CarDTO> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return cars.stream().map(car -> modelMapper.map(car, CarDTO.class)).collect(Collectors.toList());
    }
    public List<CarDTO> addCars(List<@Valid CarDTO> cars) {
        List<Car> carEntities = cars.stream()
                .map(carDTO -> modelMapper.map(carDTO, Car.class))
                .collect(Collectors.toList());
        List<Car> savedCars = carRepository.saveAll(carEntities);
        return savedCars.stream()
                .map(car -> modelMapper.map(car, CarDTO.class))
                .collect(Collectors.toList());
    }

    public CarDTO updateCarById(Long id, CarDTO carDTO) {
        Car excar = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with ID: " + id));
        modelMapper.map(carDTO, excar);
        Car updatedCar = carRepository.save(excar);

        return modelMapper.map(updatedCar, CarDTO.class);
    }

    public CarDTO deleteCarById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with ID: " + id));
        CarDTO carDTO = modelMapper.map(car, CarDTO.class);
        carRepository.delete(car);
        return carDTO;
    }

    public List<Car> searchCars(String name, String model, Year year, String color, String fuelType) {
        return carRepository.searchCars(name, model, year, color, fuelType);
    }

    public Page<Car> getCarsWithPagination(int page, int size) {
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Page number must be >= 0 and size must be > 0");
        }
        Pageable pageable = PageRequest.of(page, size);
        return carRepository.findAll(pageable);
    }
    public List<Car> getCarsWithSorting(String sortBy, String order) {
        if (!order.equalsIgnoreCase("asc") && !order.equalsIgnoreCase("desc")) {
            throw new IllegalArgumentException("Order must be either 'asc' or 'desc'");
        }
        Sort sort = Sort.by(Sort.Direction.fromString(order), sortBy);
        return carRepository.findAll(sort);
    }

    public Page<Car> getCarsWithPaginationAndSorting(int page, int size, String sortBy, String order) {
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Page number must be >= 0 and size must be > 0");
        }
        if (!order.equalsIgnoreCase("asc") && !order.equalsIgnoreCase("desc")) {
            throw new IllegalArgumentException("Order must be either 'asc' or 'desc'");
        }
        Sort sort = Sort.by(Sort.Direction.fromString(order), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return carRepository.findAll(pageable);
    }




}
