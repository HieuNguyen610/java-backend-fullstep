package hieu.javabackendfullstep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class JavaBackendFullstepApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaBackendFullstepApplication.class, args);
    }

}
