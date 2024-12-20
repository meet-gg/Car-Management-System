# Car Management System

## Description
The Car Management System is a RESTful application designed to manage cars and their details, offering features like adding, updating, deleting, searching, pagination, and sorting. The system ensures data integrity through validations and provides a user-friendly interface for developers via APIs.

## Table of Contents
1. [API Endpoints](#api-endpoints)
    - [Get All Cars](#1-get-all-cars)
    - [Add Cars](#2-add-cars)
    - [Update Car by ID](#3-update-car-by-id)
    - [Delete Car by ID](#4-delete-car-by-id)
    - [Search Cars](#5-search-cars)
    - [Get Cars with Pagination](#6-get-cars-with-pagination)
    - [Get Cars with Sorting](#7-get-cars-with-sorting)
    - [Get Cars with Pagination and Sorting](#8-get-cars-with-pagination-and-sorting)
2. [Validations](#validations)
3. [Technologies Used](#technologies-used)
4. [Running Locally](#running-locally)

## API Endpoints

### 1. Get All Cars
- **URL**: `https://car-management-system-production.up.railway.app/api/cars/getAllCars`
- **Method**: `GET`
- **Description**: Fetches a list of all cars in the database.
- **Response**: Returns a list of cars as `CarDTO` objects.

### 2. Add Cars
- **URL**: `https://car-management-system-production.up.railway.app/api/cars/addCars`
- **Method**: `POST`
- **Description**: Adds a list of cars to the database.
- **Request Body**:
    ```json
    [
      {
        "carName": "Tesla Model S",
        "carModel": "Model S",
        "carColor": "White",
        "carPrice": 75000,
        "carYear": 2022,
        "carFuelType": "Electric"
      }
    ]
    ```
- **Response**: Returns the added cars as `CarDTO` objects.
- **Validations**:
  - `carName`: Cannot be blank.
  - `carModel`: Cannot be blank.
  - `carColor`: Cannot be blank.
  - `carPrice`: Must be a positive number.
  - `carYear`: Must be a valid 4-digit year and not exceed the current year.
  - `carFuelType`: Must be one of the following:
    - Petrol
    - Diesel
    - Electric
    - Hybrid
    - CNG
    - LPG

### 3. Update Car by ID
- **URL**: `https://car-management-system-production.up.railway.app/api/cars/updateCarById/{id}`
- **Method**: `PUT`
- **Description**: Updates the details of a car with the given ID.
- **Request Parameters**:
  - `id`: The ID of the car to be updated.
- **Request Body**:
    ```json
    {
      "carName": "Tesla Model X",
      "carModel": "Model X",
      "carColor": "Black",
      "carPrice": 85000,
      "carYear": 2023,
      "carFuelType": "Electric"
    }
    ```
- **Response**: Updated car details as a `CarDTO` object.

### 4. Delete Car by ID
- **URL**: `https://car-management-system-production.up.railway.app/api/cars/deleteCarById/{id}`
- **Method**: `DELETE`
- **Description**: Deletes a car with the given ID from the database.
- **Request Parameters**:
  - `id`: The ID of the car to be deleted.
- **Response**: Returns the details of the deleted car as a `CarDTO` object.

### 5. Search Cars
- **URL**: `https://car-management-system-production.up.railway.app/api/cars/search`
- **Method**: `GET`
- **Description**: Searches for cars based on the provided criteria.
- **Request Parameters (Optional)**:
  - `name`: Name of the car.
  - `model`: Model of the car.
  - `year`: Year of manufacture.
  - `color`: Color of the car.
  - `fuelType`: Fuel type of the car.
- **Response**: Returns a list of cars matching the criteria.

### 6. Get Cars with Pagination
- **URL**: `https://car-management-system-production.up.railway.app/api/cars/pagination`
- **Method**: `GET`
- **Description**: Fetches cars with pagination.
- **Request Parameters**:
  - `page` (default: 0): Page number (starts from 0).
  - `size` (default: 10): Number of records per page.
- **Response**: Returns a paginated list of cars.

### 7. Get Cars with Sorting
- **URL**: `https://car-management-system-production.up.railway.app/api/cars/sorting`
- **Method**: `GET`
- **Description**: Fetches cars sorted by a specified field.
- **Request Parameters**:
  - `sortBy` (default: `carName`): Field to sort by.
  - `order` (default: `asc`): Sorting order.
- **Response**: Returns a sorted list of cars.

### 8. Get Cars with Pagination and Sorting
- **URL**: `https://car-management-system-production.up.railway.app/api/cars/pagination-sorting`
- **Method**: `GET`
- **Description**: Fetches cars with both pagination and sorting.
- **Request Parameters**:
  - `page` (default: 0): Page number.
  - `size` (default: 10): Number of records per page.
  - `sortBy` (default: `carName`): Field to sort by.
  - `order` (default: `asc`): Sorting order.
- **Response**: Returns a paginated and sorted list of cars.

## Validations

### CarDTO Validations:
- `carName`: Cannot be blank.
- `carModel`: Cannot be blank.
- `carColor`: Cannot be blank.
- `carPrice`: Must be a positive number.
- `carYear`: Must be a valid year and not exceed the current year.
- `carFuelType`: Must be one of the following:
  - Petrol
  - Diesel
  - Electric
  - Hybrid
  - CNG
  - LPG

## Technologies Used
- **Spring Boot**: For building the RESTful APIs.
- **Hibernate/JPA**: For database interactions.
- **ModelMapper**: For object mapping between entities and DTOs.
- **Jakarta Validation**: For input validations.
- **Maven**: For dependency management.

## Running Locally

To run the Car Management System locally, follow these steps:

1. **Clone the Repository**: Clone the project from your version control system (e.g., GitHub) to your local machine.

    ```bash
    git clone https://your-repository-link.git
    ```

2. **Navigate to the Project Directory**: Open your terminal/command prompt and navigate to the project directory.

3. **Modify `application.properties`**: Update the `application.properties` file with your local configuration settings (e.g., database URL, credentials):
   
    Example configuration for `application.properties`:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/car_management
    spring.datasource.username=root
    spring.datasource.password=your_password
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.properties.hibernate.format_sql=true
    ```

4. **Build the Project**: Use Maven to build the project.

    ```bash
    mvn clean install
    ```

5. **Run the Project**: After building, you can run the application with the following command:

    ```bash
    mvn spring-boot:run
    ```

6. **Test the API Endpoints with Postman**: Use Postman to test the API endpoints. The URLs will be the same as the ones mentioned earlier, but replace the deployment link with `http://localhost:8080` if you are running it locally.

    Example:
    - **URL**: `http://localhost:8080/api/cars/getAllCars`
    - **Method**: `GET`

    Postman will allow you to interact with the REST API by sending requests and viewing responses in a user-friendly way.
