package com.example.multipledatasources;

import com.example.multipledatasources.entities.mysqlrepo.User;
import com.example.multipledatasources.entities.posgrerepo.Product;
import com.example.multipledatasources.service.ProductService;
import com.example.multipledatasources.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class MultipleDatasourcesApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    public static void main(String[] args) {
        SpringApplication.run(MultipleDatasourcesApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        Product product = new Product(null,"Producto2","2222");
        User user = new User(0,"" +
                "juan","54675534");

        productService.save(product);
        userService.save(user);
    }
}
