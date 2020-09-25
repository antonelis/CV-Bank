package se.iths.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@Slf4j
public class SetupDatabase {

    @Bean
    CommandLineRunner initDatabase(StudentsRepository repository) {
        return args -> {
            if( repository.count() == 0) {
                //New empty database, add some persons
                log.info("Added to database " + repository.save(new Student(1,"Anton","Johansson","Java")));
                log.info("Added to database " + repository.save(new Student(2,"Patrik","Andersson","Web")));
            }
        };
    }

    @Bean
    @LoadBalanced
    RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

//    @Bean
//    Person person1(){
//        return new Person(0L,"Kalle");
//    }
//
//    @Bean
//    Person person2(){
//        return new Person(0L,"Martin");
//    }

}