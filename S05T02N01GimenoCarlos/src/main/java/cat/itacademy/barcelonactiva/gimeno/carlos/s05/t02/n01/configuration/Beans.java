package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
