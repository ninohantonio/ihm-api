package com.example.myspringapp;


import com.example.myspringapp.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class MyspringappApplication implements CommandLineRunner {
    @Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(MyspringappApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repositoryRestConfiguration.exposeIdsFor(Sexe.class, Category.class, Animal.class, Alimentation.class, Product.class, Vaccination.class, Vente.class, Achat.class, Reproduction.class, User.class);
//        categoryRepository.save(new Category(null, "Bovin", 25, 20000, null));
//        categoryRepository.save(new Category(null, "Porcin", 2, 20000, null));
//
//        sexeRepository.save(new Sexe(null, "Male", null));
//        sexeRepository.save(new Sexe(null, "Femmele", null));

//        Random rnd = new Random();
//        for(int i=0; i<10; i++){
//            categoryRepository.findAll().forEach(cat->{
//                Product p = new Product();
//
//                String uuid = UUID.randomUUID().toString().replace("-", "");
//                p.setName(uuid.substring(0, 18));
//                p.setCurrentPrice(100+rnd.nextInt(1000));
//                p.setAvailable(rnd.nextBoolean());
//                p.setSelected(rnd.nextBoolean());
//                p.setPromotion(rnd.nextBoolean());
//                p.setCategory(cat);
//                productRepository.save(p);
//
//            });
//        }
    }
}
