package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StundentRepository repository){
        return args -> {
            Student Maria=new Student(
                    "Maria",
                    "Maria.jamil@gmail.com ",
                    LocalDate.of(2000, Month.JANUARY,5)

            );
            Student Alex=new Student(
                    "Alex",
                    "Alex.jkkil@gmail.com ",
                    LocalDate.of(2005, Month.JANUARY,5)
            );

            repository.saveAll(
                    List.of(Maria,Alex)
            );
        };
    }
}
