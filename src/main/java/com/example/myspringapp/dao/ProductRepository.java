package com.example.myspringapp.dao;

import com.example.myspringapp.entities.Product;
import com.example.myspringapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

@CrossOrigin("*")
@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {
//    @RestResource(path = "/selectedproducts")
//    public List<Product> findBySelectedIsTrue();
    Collection<Product> findByUserAndDesignationContaining(User user, String keyword);

    Collection<Product> findByUserAndDesignationContainingIgnoreCase(User user, String search);
    Collection<Product> findByUserAndQuantite(User user, double search);
}
