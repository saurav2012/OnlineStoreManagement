package com.onlinestore.demo.service;

import java.util.Optional;

import com.onlinestore.demo.model.Product;
import com.onlinestore.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService{

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return product.get();
        }
        //throw new ProductNotFoundException("Product with id "+ id+" not present");
        return null;
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public Product update(Product product){
        Optional<Product> data = productRepository.findById(product.getId());
        if(data.isEmpty()){
            //throw new ProductNotFoundException("Product with id "+ id+" not present. So update cannot be performed");
        }
        return productRepository.save(product);
    }

    public void deleteProductById(Long id){
        Optional<Product> data = productRepository.findById(id);
        if(data.isEmpty()){
            //throw new ProductNotFoundException("Product with id "+ id+" not present. So update cannot be performed");
        }
        productRepository.deleteById(id);;
    }
    
}