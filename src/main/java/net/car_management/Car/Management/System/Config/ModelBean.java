package net.car_management.Car.Management.System.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelBean {
    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }
}
