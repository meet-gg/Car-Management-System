package net.car_management.Car.Management.System.Repository;

import net.car_management.Car.Management.System.DTO.CarDTO;
import net.car_management.Car.Management.System.Models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Year;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT c FROM Car c WHERE " +
            "(:name IS NULL OR LOWER(c.carName) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:model IS NULL OR LOWER(c.carModel) LIKE LOWER(CONCAT('%', :model, '%'))) AND " +
            "(:year IS NULL OR c.carYear = :year) AND " +
            "(:color IS NULL OR LOWER(c.carColor) LIKE LOWER(CONCAT('%', :color, '%'))) AND " +
            "(:fuelType IS NULL OR LOWER(c.carFuelType) LIKE LOWER(CONCAT('%', :fuelType, '%')))")
    List<Car> searchCars(
            @Param("name") String name,
            @Param("model") String model,
            @Param("year") Year year,
            @Param("color") String color,
            @Param("fuelType") String fuelType);
}
