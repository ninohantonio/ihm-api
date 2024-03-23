package com.example.myspringapp.dao;

import com.example.myspringapp.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("*")
@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {
//    @RestResource(path = "/selectedproducts")
//    public List<Product> findBySelectedIsTrue();
//    @RestResource(path = "productbykeyword")
//    public List<Product> findByNameContaining(@Param("mc") String mc);
}
